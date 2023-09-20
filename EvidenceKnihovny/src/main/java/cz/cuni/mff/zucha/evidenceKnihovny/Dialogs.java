package cz.cuni.mff.zucha.evidenceKnihovny;

import javafx.scene.control.*;

/**
 * Tovární třída pro jednoduché dialogy. Nabízí několik statických metod pro tvorbu nejrůznějších jednoduchých
 * dialogů či alertů použitých v aplikaci.
 */
public class Dialogs {
    /**
     * Vytvoří jednoduchý alert, který oznamuje fatální chybu spuštění aplikace.
     * @return Alert s chybovou hláškou
     */
    public static Alert fatalErrorAlert() {
        Alert result = new Alert(Alert.AlertType.ERROR);
        result.setHeaderText(null);
        result.setTitle("Fatal Error");
        result.setContentText("Fatální chyba při spuštění. Prosím ověřte korektní instalaci aplikace.");
        return result;
    }

    /**
     * Vytvoří dialog s uživatelskou dokumentací uloženou v html souboru.
     * @param htmlFile jméno souboru s dokumentací bez přípony
     * @return Dialog s html dokumentací
     */
    public static Dialog<Object> helpWindow(String htmlFile) {
        return new Dialog<>();
    }
}
