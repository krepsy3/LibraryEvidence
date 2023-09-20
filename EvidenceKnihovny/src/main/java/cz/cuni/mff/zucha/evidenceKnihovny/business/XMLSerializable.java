package cz.cuni.mff.zucha.evidenceKnihovny.business;

import java.util.UUID;
import org.jdom2.Element;

 /**
 * Základní třída pro data zapisovaná do XML. Každá taková data musí umět vytvořit Element jazyka XML, který je
 * reprezentuje, tudíž díky němu půjdou zpětně vytvořit. Jedná se tak defacto o mechanismus serializace a deserializace,
 * implementovaný ovšem mimo standardní rozhraní javy.
 */
public interface XMLSerializable {

    /**
     * Getter unikátního identifikátoru, aby se mohla data refencovat mimo stromové hrany
     * @return unikátní identifikátor serializovaného objektu
     */
    UUID getIdentifier();

     /**
      * Getter jména XML Elementu pro reprezentaci tohoto objektu
      * @return jméno pro XML Element
      */
    String getElementName();

    /**
     * Vytvoří XML Element, který reprezentuje serializovaný stav instance.
     * @return Element se serializovaným stavem objektu.
     */
    Element toXMLElement();
}