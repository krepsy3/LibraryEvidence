package cz.cuni.mff.zucha.evidenceKnihovny.business;

import org.jdom2.Element;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Záznam reprezentující informaci o transakci vypůjčitele. Osahuje částku, záporná značí naúčtování peněz na vrub
 * vypůjčitele, kladná značí zaplacení částky vypůjčitelem. Dále obsahuje datum výpůjčky a textový komentář, popisující
 * slovně účel transakce.
 */
public record TransactionRecord(
        UUID identifier,
        int amount,
        LocalDate date,
        String comment
    ) implements XMLSerializable {

    /**
     * Jméno pro XML Element reprezentující tuto třídu
     */
    public static final String XMLElementName = "transaction";

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
        result.setAttribute("amount", ((Integer)amount).toString());
        result.setAttribute("date", date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        result.setAttribute("comment", comment);
        return result;
    }
}
