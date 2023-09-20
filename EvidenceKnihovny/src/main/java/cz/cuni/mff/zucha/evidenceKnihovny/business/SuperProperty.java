package cz.cuni.mff.zucha.evidenceKnihovny.business;

/**
 * Vlastnost, která nemusí mít vlastní hodnotu, a ta se pak hledá na předem definovaném místě. Je-li SuperProperty
 * interně null, pak její prezentovatelná hodnota se hledá v jakémsi nadřazeném (super) místě. Může tedy reprezentovat
 * vlastnost s implicitní hodnotou. Při getování a setování si hlídá, zdali není interní hodnota rovna implicitní
 * hodnotě, a srovná si interní hodnotu na null, poku ano.
 * @param <E> Typ, který superproperty reprezentuje
 */
public final class SuperProperty<E> {

    /**
     * funkcionální interface, který obsahuje metodu na gettování super hodnoty, pokud je interní hodnota null.
     * @param <E> Typ, který má super hodnota
     */
    public interface superValueGetterInterface<E> {
        /**
         * getter super hodnoty.
         * @return super hodnota
         */
        E getSuperValue();
    }


    /**
     * instance funkcionálního interfacu pro gettování super hodnoty
     */
    private final superValueGetterInterface<? extends E> superGetter;


    /**
     * interní hodnota této superproperty
     */
    private E internalValue;

    /**
     * getter superproperty
     * @return hodnota vrácená super getterem je-li {@link #internalValue} null, jinak přímo {@link #internalValue}
     */
    public E get() {
        if (internalValue == null) {
            return superGetter.getSuperValue();
        }

        E result = internalValue;

        if (internalValue.equals(superGetter.getSuperValue())) {
            internalValue = null;
        }

        return result;
    }

    /**
     * setter superproperty
     * @param newValue nová hodnota
     */
    public void set(E newValue) {
        if (newValue.equals(superGetter.getSuperValue())) {
            internalValue = null;
        }

        else {
            internalValue = newValue;
        }
    }


    /**
     * Konstruktor superproperty.
     * @param superGetter metoda pro gettování super hodnoty
     */
    public SuperProperty (superValueGetterInterface<E> superGetter) {
        this.superGetter = superGetter;
    }


    /**
     * Zjistí, zdali je interní hodnota konkrétně definovaná.
     * @return false, pokud je {@link #internalValue} null, jinak true
     */
    public boolean isDefined() {
        return internalValue != null;
    }
}
