package cz.cuni.mff.zucha.evidenceKnihovny.business;

import javafx.collections.*;
import org.jdom2.*;

import java.util.*;

/**
 * Abstraktní třída reprezentující konkrétní vypůjčitelný předmět. Každý Rentable objekt nese kolekci svých
 * vypůjčitelných instancí, které dědí z této třídy. Obsahuje také základní datové typy společné pro všechny
 * vypůjčitelné instance.
 */
public abstract class RentableInstance extends XMLSerializableObject {

    /**
     * Výčtový typ pro reprezentaci stavu poškození předmětu. Reprezentován je celými kladnými čísly, konkrétně v
     * rozsahu &lt;0; 6&gt;, čím vyšší číslo, tím horší poškození. Součástí poškození je i stav "Ztracený", který
     * reprezentuje ztrátu předmětu, což tedy není poškození v pravém slovasmyslu, ale stav sem patří.
     */
    public enum DamageLevel {
        /**
         * Sémanticky zcela nový předmět, nikdy nevypůjčený
         */
        BRAND_NEW(0),
        /**
         * Sémanticky již půjčovaný předmět, který nenese známky používání
         */
        WELL(1),
        /**
         * Sémanticky již půjčovaný předmět, který nese známky používání ale je v dobrém stavu
         */
        GOOD(2),
        /**
         * Sémanticky mírně poškozený předmět
         */
        SLIGHT(3),
        /**
         * Sémanticky vážně poškozený předmět
         */
        SERIOUS(4),
        /**
         * Sémanticky zcela zničený předmět, v nevypůjčitelném stavu
         */
        DESTROYED(5),
        /**
         * Sémanticky ztracený předmět, jehož kondici nelze konstatovat, ale rozhodně v nevypůjčitelném stavu
         */
        LOST(6);

        /**
         * Hodnota reprezentující úroveň poškození.
         */
        public final int dmgLevel;

        /**
         * Maximální možná úroveň poškození, aby bylo možné předmět zapůjčovat.
         */
        public static final int maximalRentableLevel = 4;

        private DamageLevel(int level) {
            dmgLevel = level;
        }

        /**
         * Vrátí slovní popis dané úrovně poškození.
         * @return String se slovním popisem poškození
         */
        @Override
        public String toString() {
            return switch (dmgLevel) {
                case 0 -> AppStrings.itemStateBrandNew;
                case 1 -> AppStrings.itemStateNew;
                case 2 -> AppStrings.itemStateGood;
                case 3 -> AppStrings.itemStateDmgSlight;
                case 4 -> AppStrings.itemStateDmgSerious;
                case 5 -> AppStrings.itemStateDestroyed;
                case 6 -> AppStrings.itemStateLost;
                default -> throw new IllegalStateException(
                    String.format(
                        AppStrings.enumValueIllegal,
                        ((Integer)dmgLevel).toString(),
                        "DamageLevel"));
            };
        }

        /**
         * Vrátí patřičný DamageLevel podle číselné hodnoty.
         * @param value číselná hodnota cílového DamageLevel
         * @return RentType odpovídající číselné hodnotě
         * @throws IllegalArgumentException když není {@code value} v rozsahu hodnot odpovídajícímu platným hodnotám typu
         */
        public static DamageLevel fromInteger(int value) throws IllegalArgumentException {
            return switch (value) {
                case 0 -> BRAND_NEW;
                case 1 -> WELL;
                case 2 -> GOOD;
                case 3 -> SLIGHT;
                case 4 -> SERIOUS;
                case 5 -> DESTROYED;
                case 6 -> LOST;
                default -> throw new IllegalArgumentException(
                    String.format(
                        AppStrings.enumValueNonExistent,
                        ((Integer)value).toString(),
                        "DamageLevel"));
            };
        }

        /**
         * Vrátí, zdali se dá předmět s touto úrovní poškození vypůjčit nějakému čtenáři
         * @return {@code true} pokud lze předmět s tímto poškozením vypůjčit, jinak {@code false}
         */
        public boolean isRentable() {
            return dmgLevel <= maximalRentableLevel;
        }
    }


    /**
     * Stupeň poškození předmětu
     */
    protected DamageLevel damage;
    /**
     * Getter stupně poškození předmětu
     * @return Stupeň poškození předmětu
     */
    public DamageLevel getDamage() {
        return damage;
    }
    /**
     * Setter stupně poškození předmětu
     * @param damage nový stupeň poškození předmětu
     */
    public void setDamage(DamageLevel damage) {
        this.damage = damage;
    }

    /**
     * Aktuální výpůjčka/rezervace
     */
    protected RentInfo currentRent;
    /**
     * Getter aktuální výpůjčky/rezervace
     * @return informace o výpůjčce/rezervaci
     */
    public RentInfo getCurrentRent() {
        return currentRent;
    }
    /**
     * Setter aktuální výpůčky/rezervace.
     * @param value nová výpůjčka/rezervace
     */
    public void setCurrentRent(RentInfo value) {
        currentRent = value;
    }

    /**
     * Historie výpůjček tohoto předmětu. Přidávají se sem automaticky po vrácení.
     */
    protected ObservableList<RentInfo> rentHistory;
    /**
     * Getter historie výpůjček
     * @return kolekce informací o výpůčjkách
     */
    public ObservableList<RentInfo> getRentHistory() {
        return rentHistory;
    }


    /**
     * Vrátí, zdali je možné daný předmět vypůjčit. To posoudí dle úrovně poškození a stavu vypůjčení.
     * @return {@code true} pokud je možné vypůjčit předmět, jinak {@code false}.
     */
    public boolean isRentable() {
        return currentRent.type().isRentable() && damage.isRentable();
    }

    /**
     * Vrátí, zdali je daný předmět fyzicky přítomen na oddělení. To posoudí dle úrovně poškození a stavu vypůjčení.
     * Jiný čtenář si tak předmět může určitě alespoň prohlédnout či prolistovat. Nezaručuje vypůjčitelnost.
     * @return {@code true} pokud je předmět s tímto typem stavu přítomen, jinak {@code false}
     * @see #isRentable() metoda isRentable pro zjištění vypůjčitelnosti
     */
    public boolean isPresent() {
        return currentRent.type().isPresent() && !damage.equals(DamageLevel.LOST);
    }

    @Override
    public Element toXMLElement() {
        Element result = super.toXMLElement();
        result.setAttribute("damage", ((Integer)damage.dmgLevel).toString());
        Element currentRentElem = currentRent.toXMLElement();
        currentRentElem.setName("currentRentInfo");
        result.addContent(currentRentElem);

        if (!rentHistory.isEmpty()) {
            Element rentsElem = new Element("rentHistory");
            result.addContent(rentsElem);
            rentHistory.forEach(ri -> rentsElem.addContent(ri.toXMLElement()));
        }

        return result;
    }
}