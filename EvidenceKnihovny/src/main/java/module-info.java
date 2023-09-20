/**
 * Modul aplikace Evidence Knihovny. Vzhledem k malé velikosti aplikace je přítomen kvůli správnému
 * fungování frameworku JavaFX.
 * @author Kryštof Žucha
 * @provides cz.cuni.mff.zucha.evidenceKnihovny základní kód aplikace a kontroléry
 * @provides cz.cuni.mff.zucha.evidenceKnihovny.business kód ukládaných dat, ukládání, načítání, validace
 */
module cz.cuni.mff.zucha.evidenceKnihovny {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires java.xml;
    requires org.apache.commons.io;
    requires jdom2;

    opens cz.cuni.mff.zucha.evidenceKnihovny to javafx.fxml;

    exports cz.cuni.mff.zucha.evidenceKnihovny;
    exports cz.cuni.mff.zucha.evidenceKnihovny.business;
}