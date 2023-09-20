package cz.cuni.mff.zucha.evidenceKnihovny.business;

/**
 * Fakticky statická třída obsahující stringové konstanty pro rozličné třídy z bussiness balíčku
 */
public final class AppStrings {
    private AppStrings() {
        throw new UnsupportedOperationException();
    }

    /**
     * Výchozí jméno souboru ("Untitled")
     */
    public static final String noNameFile = "Beze jména";

    /**
     * Chybová hláška, když nelze nastavit oddělení jako pouze zaměstnanecké
     */
    public static final String employeeOnlyIllegal =
        "Nelze nastavit oddělení jako zaměstnanecké, dokud obsahuje vypůjčitelné položky či obyčejné čtenáře.";
    /**
     * Chybová hláška, když je datum uskutečnění výpůjčky větší, než datum návratu výpůjčky
     */
    public static final String rentDateIllegal = "Datum výpůjčky je větší, než datum návratu.";
    /**
     *
     */
    public static final String rentingFreeIllegal = "Nelze zadat výpůjčku/rezervaci volnému předmětu!";

    /**
     * Slovní popis {@link RentableInstance.DamageLevel} hodnoty 0
     */
    public static final String itemStateBrandNew = "Nový";
    /**
     * Slovní popis {@link RentableInstance.DamageLevel} hodnoty 1
     */
    public static final String itemStateNew = "Zánovní";
    /**
     * Slovní popis {@link RentableInstance.DamageLevel} hodnoty 2
     */
    public static final String itemStateGood = "Dobrý";
    /**
     * Slovní popis {@link RentableInstance.DamageLevel} hodnoty 3
     */
    public static final String itemStateDmgSlight = "Mírně poškozený";
    /**
     * Slovní popis {@link RentableInstance.DamageLevel} hodnoty 4
     */
    public static final String itemStateDmgSerious = "Vážně poškozený";
    /**
     * Slovní popis {@link RentableInstance.DamageLevel} hodnoty 5
     */
    public static final String itemStateDestroyed = "Zničený";
    /**
     * Slovní popis {@link RentableInstance.DamageLevel} hodnoty 6
     */
    public static final String itemStateLost = "Ztracený";

    public static final String itemStateFree = "Volný k zápůjčce";
    public static final String itemStateReserved = "Rezervovaný";
    public static final String itemStateRented = "Zapůjčený";
    public static final String itemStateReturned = "Vrácený";

    public static final String enumValueIllegal =
        "Value %s is not valid in the enum type %s. Enum class source code is inconsistent! " +
        "If you are seeing this as the end user, it means the application is very wrong. Sorry for any inconveniences.";
    public static final String enumValueNonExistent = "Value %s does not represent any valid %s object!";
}
