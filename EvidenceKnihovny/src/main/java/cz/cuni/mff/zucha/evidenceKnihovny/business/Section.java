package cz.cuni.mff.zucha.evidenceKnihovny.business;

//javafx specifix imports
import javafx.collections.*;
import org.jdom2.Element;

/**
 * Oddělení knihovny. Obsahuje seznam vypůjčitelných položek, seznam vypůjčitelů a ceny poplatků a pokut.
 */
public class Section extends XMLSerializableObject {

    /**
     * Jméno pro XML Element reprezentující tuto třídu
     */
    public static final String XMLElementName = "section";


    /**
     * Jméno oddělení
     */
    private String name;
    /**
     * Getter jména oddělení
     * @return jméno oddělení
     */
    public String getName() {
        return name;
    }
    /**
     * Setter jména oddělení
     * @param name nové jméno oddělení
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Příznak, je-li oddělení pouze zaměstnanecké
     */
    private boolean employeeExclusive;
    /**
     * Getter čistě zaměstaneckého příznaku
     * @return <code>true</code> je-li oddělení pouze zaměstnanecké, jinak <code>false</code>.
     */
    public boolean isEmployeeExclusive() { return employeeExclusive; }
    /**
     * Setter čistě zaměstnaneckého příznaku
     * @param employeeExclusive nová hodnota příznaku
     * @throws IllegalArgumentException pokud se nastavuje <code>true</code> a oddělení obsahuje nějaké vypůjčitelné
     * položky nebo nezaměstnanecké vypůjčitele
     */
    public void setEmployeeExclusive(boolean employeeExclusive) {
        if (!employeeExclusive || (employeeExclusive &&
                                   rentables.isEmpty() &&
                                   borrowers.stream().allMatch(b -> b instanceof Employee)))
        {
            this.employeeExclusive = employeeExclusive;
            return;
        }

        throw new IllegalArgumentException(AppStrings.employeeOnlyIllegal);
    }

    /**
     * Seznam vypůjčitelů v oddělení
     */
    private ObservableList<Borrower> borrowers;
    /**
     * Getter seznamu vypůjčitelů
     * @return Seznam vypůjčitelů tohoto oddělení
     */
    public ObservableList<Borrower> getBorrowers() {
        return borrowers;
    }

    /**
     * Seznam vypůjčitelných položek oddělení
     */
    private ObservableList<Rentable> rentables;
    /**
     * Getter seznamu vypůjčitelných položek
     * @return Seznam vypůjčitelných položek tohoto oddělení
     */
    public ObservableList<Rentable> getRentables() {
        return rentables;
    }

    /**
     * Poplatek za vypůjčku místního čtenáře
     */
    private int readerFee;
    /**
     * Getter poplatku místního čtenáře
     * @return Poplatek za výpůjčku místního čtenáře
     */
    public int getReaderFee() { return readerFee; }
    /**
     * Setter poplatku místního čtenáře
     * @param readerFee nový poplatek místního čtenáře
     */
    public void setReaderFee(int readerFee) { this.readerFee = readerFee; }

    /**
     * Poplatek za vypůjčku čtenáře z jiného oddělení
     */
    private int foreignReaderFee;
    /**
     * Getter poplatku čtenáře z jiného oddělení
     * @return Poplatek za výpůjčku čtenáře z jiného oddělení
     */
    public int getForeignReaderFee() { return foreignReaderFee; }
    /**
     * Setter poplatku čtenáře z jiného oddělení
     * @param foreignReaderFee nový poplatek čtenáře z jiného oddělení
     */
    public void setForeignReaderFee(int foreignReaderFee) { this.foreignReaderFee = foreignReaderFee; }

    /**
     * Poplatek za vypůjčku místního zaměstnance
     */
    private int employeeFee;
    /**
     * Getter poplatku místního zaměstnance
     * @return Poplatek za výpůjčku místního zaměstnance
     */
    public int getEmployeeFee() { return employeeFee; }
    /**
     * Setter poplatku místního zaměstnance
     * @param employeeFee nový poplatek místního zaměstnance
     */
    public void setEmployeeFee(int employeeFee) { this.employeeFee = employeeFee; }

    /**
     * Poplatek za vypůjčku zaměstnance z jiného oddělení
     */
    private int foreignEmployeeFee;
    /**
     * Getter poplatku zaměstnance z jiného oddělení
     * @return Poplatek za výpůjčku zaměstnance z jiného oddělení
     */
    public int getForeignEmployeeFee() { return foreignEmployeeFee; }
    /**
     * Setter poplatku zaměstnance z jiného oddělení
     * @param foreignEmployeeFee nový poplatek zaměstnance z jiného oddělení
     */
    public void setForeignEmployeeFee(int foreignEmployeeFee) { this.foreignEmployeeFee = foreignEmployeeFee; }

    /**
     * Pokuta za poškození vypůjčené věci
     */
    private int damageFine;
    /**
     * Getter pokuty za poškození vypůjčené věci
     * @return Pokuta za poškození vypůjčené věci
     */
    public int getDamageFine() { return damageFine; }
    /**
     * Setter pokuty za poškození vypůjčené věci
     * @param damageFine Nová pokuta za poškození vypůjčené věci
     */
    public void setDamageFine(int damageFine) { this.damageFine = damageFine; }

    /**
     * Pokuta za zničení/ztrátu vypůjčené věci
     */
    private int destructionFine;
    /**
     * Getter pokuty za zničení/ztrátu vypůjčené věci
     * @return Pokuta za zničení/ztrátu vypůjčené věci
     */
    public int getDestructionFine() { return destructionFine; }
    /**
     * Setter pokuty za zničení/ztrátu vypůjčené věci
     * @param destructionFine Nová pokuta za zničení/ztrátu vypůjčené věci
     */
    public void setDestructionFine(int destructionFine) { this.destructionFine = destructionFine; }

    /**
     * Pokuta za pozdní vrácení
     */
    private int lateReturnFine;
    /**
     * Getter pokuty za pozdní vrácení
     * @return Pokuta za pozdní vrácení
     */
    public int getLateReturnFine() { return lateReturnFine; }
    /**
     * Setter pokuty za pozdní vrácení
     * @param lateReturnFine Nová pokuta za pozdní vrácení
     */
    public void setLateReturnFine(int lateReturnFine) { this.lateReturnFine = lateReturnFine; }


    @Override
    public String getElementName() {
        return XMLElementName;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Element toXMLElement() {
        Element result = super.toXMLElement();
        result.setAttribute("name", name);
        result.setAttribute("employeeOnly", employeeExclusive ? "true" : "false");

        Element borrowersElem = new Element("borrowers");
        result.addContent(borrowersElem);
        borrowers.forEach(b -> borrowersElem.addContent(b.toXMLElement()));

        if (!employeeExclusive) {
            Element rentablesElem = new Element("rentables");
            result.addContent(rentablesElem);
            rentables.forEach(r -> rentablesElem.addContent(r.toXMLElement()));
        }

        return result;
    }
}
