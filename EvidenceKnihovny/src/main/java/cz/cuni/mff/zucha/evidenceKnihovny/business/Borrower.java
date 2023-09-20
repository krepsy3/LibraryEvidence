package cz.cuni.mff.zucha.evidenceKnihovny.business;

import javafx.collections.*;
import org.jdom2.Element;

/**
 *
 */
public class Borrower extends XMLSerializableObject {

    /**
     * Jméno pro XML Element reprezentující tuto třídu
     */
    public static final String XMLElementName = "borrower";

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    protected String contact;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


    protected Section parent;

    public Section getParent() {
        return parent;
    }

    public void setParent(Section parent) {
        this.parent = parent;
    }


    protected ObservableList<RentInfo> runningRents;

    public ObservableList<RentInfo> getRunningRents() {
        return runningRents;
    }


    protected ObservableList<TransactionRecord> transactionHistory;

    public ObservableList<TransactionRecord> getTransactionHistory() {
        return transactionHistory;
    }


    @Override
    public String getElementName() {
        return XMLElementName;
    }

    @Override
    public Element toXMLElement() {
        Element result = super.toXMLElement();

        result.setAttribute("name", name);
        result.setAttribute("contact", contact);

        if (!transactionHistory.isEmpty()) {
            Element transactionsElem = new Element("transactionHistory");
            result.addContent(transactionsElem);
            transactionHistory.forEach(tr -> transactionsElem.addContent(tr.toXMLElement()));
        }

        if (!runningRents.isEmpty()) {
            Element rentsElem = new Element("rentsCache");
            result.addContent(rentsElem);
            runningRents.forEach(ri -> rentsElem.addContent(ri.toXMLElement()));
        }

        return result;
    }


}