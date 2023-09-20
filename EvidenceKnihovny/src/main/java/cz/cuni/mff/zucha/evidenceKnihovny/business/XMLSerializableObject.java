package cz.cuni.mff.zucha.evidenceKnihovny.business;

import java.util.UUID;
import org.jdom2.Element;

public abstract class XMLSerializableObject implements XMLSerializable {

    public XMLSerializableObject() {
        this(UUID.randomUUID());
    }

    public XMLSerializableObject(UUID identifier) {
        this.identifier = identifier;
    }

    protected UUID identifier;
    @Override
    public final UUID getIdentifier() {
        return identifier;
    }

    @Override
    public abstract String getElementName();

    @Override
    public Element toXMLElement() {
        Element result = new Element(getElementName());
        result.setAttribute("identifier", identifier.toString());
        return result;
    }
}
