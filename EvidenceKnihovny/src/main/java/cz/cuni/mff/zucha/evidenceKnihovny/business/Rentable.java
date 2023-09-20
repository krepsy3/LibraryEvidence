package cz.cuni.mff.zucha.evidenceKnihovny.business;

import javafx.collections.*;
import org.jdom2.Element;


/**
 * Abstraktní třída představující vypůjčitelný objekt. Tento objekt představuje jakousi nadřazenou entitu, která
 * sdružuje konkrétní vypůjčitelné předměty RentableInstance. Veškeré vlastnosti společné pro každou vypůjčitelnou
 * instanci se pak sdružují v této třídě.
 */
public abstract class Rentable<T extends RentableInstance> extends XMLSerializableObject {

    /**
     * Seznam instancí této vypůjčitelné položky.
     */
    protected ObservableList<T> instances;
    /**
     * Getter seznamu instancí.
     * @return seznam instancí položky.
     */
    public ObservableList<T> getInstances() {
        return instances;
    }

    @Override
    public Element toXMLElement() {
        Element result = super.toXMLElement();
        Element instancesElem = new Element("instances");
        result.addContent(instancesElem);
        instances.forEach(i -> instancesElem.addContent(i.toXMLElement()));
        return result;
    }
}
