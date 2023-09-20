package cz.cuni.mff.zucha.evidenceKnihovny.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.jdom2.Element;

/**
 * Záznam reprezentující data o výpůjčce předmětu. Obsahuje unikátní identfikátor pro serializaci,
 * vypůjčovaný předmět, vypůjčitele a počáteční a koncové datum výpůjčky a typ vypůjčení. Podle typu se řídí
 * interpretace položek vypůjčitele a datumů. <br /> Pro typ {@link RentType#FREE} nehrají roli a mohou být null.<br />
 * Pro typ {@link RentType#RESERVED} je vypůjčitel rezervující a datumy reprezentují datum zadání a vypršení této
 * rezervace.<br />Pro typ {@link RentType#RENTED} má vypůjčitel předmět u sebe a datumy reprezentují datum vypůjčení
 * a lhůtu na vrácení.<br />Pro typ {@link RentType#RETURNED} je stejná interpretace, jako u {@link RentType#RENTED},
 * ale předmět je už navrácen a koncové datum obsahuje faktické datum navrácení; slouží pro účely záznamu výpůjček.
 */
public record RentInfo(
        UUID identifier,
        RentType type,
        RentableInstance item,
        Borrower borrower,
        LocalDate startDate,
        LocalDate endDate
    ) implements XMLSerializable {

    /**
     * Jméno pro XML Element reprezentující tuto třídu
     */
    public static final String XMLElementName = "rentinfo";

    /**
     * Výčtový typ pro reprezentaci typu výpůjčky předmětu.
     */
    public enum RentType {
        /**
         * Předmět je přítomen na oddělení volně k výpůjčce
         */
        FREE(0),
        /**
         * Předmět je přítomen na oddělení rezervovaný pro konkrétního čtenáře
         */
        RESERVED(1),
        /**
         * Předmět není přítomen na oddělení, konkrétní čtenář ho má vypůjčen
         */
        RENTED(2),
        /**
         * Předmět je přítomen na oddělení, konkrétní čtenář ho navrátil (slouží pro archivaci)
         */
        RETURNED(3);

        /**
         * Řídicí proměnná typu
         */
        public int value;
        RentType(int value) {
            this.value = value;
        }

        /**
         * Vrátí slovní popis daného stavu vypůjčenosti.
         * @return String se slovním popisem vypůjčitelnosti
         */
        @Override
        public String toString() {
            return switch (value) {
                case 0 -> AppStrings.itemStateFree;
                case 1 -> AppStrings.itemStateReserved;
                case 2 -> AppStrings.itemStateRented;
                case 3 -> AppStrings.itemStateReturned;
                default -> throw new IllegalStateException(
                    String.format(
                        AppStrings.enumValueIllegal,
                        ((Integer)value).toString(),
                        "RentType"));
            };
        }

        /**
         * Vrátí patřičný RentType podle číselné hodnoty.
         * @param value číselná hodnota cílového RentType
         * @return RentType odpovídající číselné hodnotě
         * @throws IllegalArgumentException když není {@code value} v rozsahu hodnot odpovídajícímu platným hodnotám typu
         */
        public static RentType fromInteger(int value) throws IllegalArgumentException {
            return switch (value) {
                case 0 -> FREE;
                case 1 -> RESERVED;
                case 2 -> RENTED;
                case 3 -> RETURNED;
                default -> throw new IllegalArgumentException(
                    String.format(
                        AppStrings.enumValueNonExistent,
                        ((Integer)value).toString(),
                        "RentType"));
            };
        }

        /**
         * Vrátí, zdali se dá předmět s tímto typem stavu výpůjčky vypůjčit jinému čtenáři
         * @return {@code true} pokud lze předmět s tímto typem stavu vypůjčit, jinak {@code false}
         */
        public boolean isRentable() {
            return (value == FREE.value) || (value == RETURNED.value);
        }

        /**
         * Vrátí, zdali je předmět s tímto typem stavu výpůjčky fyzicky přítomen na oddělení. Jiný čtenář si tak
         * předmět může určitě alespoň prohlédnout či prolistovat. Nezaručuje vypůjčitelnost.
         * @return {@code true} pokud je předmět s tímto typem stavu přítomen, jinak {@code false}
         * @see #isRentable() metoda isRentable pro zjištění vypůjčitelnosti
         */
        public boolean isPresent() {
            return !(value == RENTED.value);
        }
    }

    /**
     * Konstruktor záznamového typu. Od výchozí implementace se liší tím, že obsahuje navíc kontrolu, zdali datum
     * konce výpůjčky nepředchází začátečnímu datu.
     */
    public RentInfo {
        if (startDate.compareTo(endDate) > 0) {
            throw new IllegalArgumentException(AppStrings.rentDateIllegal);
        }
    }

    /**
     * konstruktor záznamového typu pro volný typ výpůjčky. Nemá vypůjčitele ani relevantní datumy.
     * @param identifier unikátní identifikátor
     * @param item vypůjčovaný předmět
     */
    public RentInfo(UUID identifier, RentableInstance item) {
        this(identifier, RentType.FREE, item, null, LocalDate.MIN, LocalDate.MIN);
    }

    @Override
    public UUID getIdentifier() {
        return identifier;
    }

    @Override
    public String getElementName() {
        return XMLElementName;
    }

    @Override
    public Element toXMLElement() {
        Element result = new Element(XMLElementName);
        result.setAttribute("identifier", identifier.toString());
        result.setAttribute("type", Integer.toString(type.value));
        result.setAttribute("itemIdentifier", item.getIdentifier().toString());
        if (type.value > RentType.FREE.value) {
            result.setAttribute("borrowerIdentifier", borrower.getIdentifier().toString());
            result.setAttribute("startDate", startDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
            result.setAttribute(  "endDate",   endDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
        return result;
    }
}
