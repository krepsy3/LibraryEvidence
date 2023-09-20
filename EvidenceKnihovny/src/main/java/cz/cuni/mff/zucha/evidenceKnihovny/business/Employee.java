package cz.cuni.mff.zucha.evidenceKnihovny.business;

import org.jdom2.Element;
import java.util.UUID;

public class Employee extends Borrower {
    /**
     * Jméno pro XML Element reprezentující tuto třídu
     */
    public static final String XMLElementName = "employee";

    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    private int wage;

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }


    @Override
    public String getElementName() {
        return XMLElementName;
    }

    @Override
    public Element toXMLElement() {
        Element result = super.toXMLElement();
        result.setAttribute("position", position);
        result.setAttribute("wage", ((Integer)wage).toString());

        return result;
    }

}
