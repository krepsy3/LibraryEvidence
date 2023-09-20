package cz.cuni.mff.zucha.evidenceKnihovny;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Hlavní třída aplikace. Obsahuje jedinou základní scénu, kterou při spuštění přiřadí hlavní stage.
 * Obsah scény je načten ze zdrojů z příslušného .fxml souboru.
 */
public class App extends Application {

    /**
     * Hlavní a jediná scéna aplikace.
     */
    private static Scene scene;

    /**
     * Vstupní bod aplikace. Třída neobsahuje main(), je tedy použit implicitní start JavaFX aplikace
     * a veškeré přípravné práce tak provede tato metoda.
     * @param stage okno aplikace, do kterého se budou vykreslovat scény
     */
    @Override
    public void start(Stage stage) {

        scene = null;
        try {
            scene = new Scene(loadFXML("main"), 800, 600);
        }

        catch (IOException ex) {
            Dialogs.fatalErrorAlert().showAndWait();
            System.exit(1);
        }

        stage.setScene(scene);
        stage.setTitle("Evidence knihovny");
        stage.setMinWidth(600);
        stage.setMinHeight(450);
        stage.show();
    }

    /**
     * Načte Parent z fxml souboru ve zdrojích. Pomocná metoda pro vyhledání a naparsování .fxml souboru
     * s obsahem pro scénu.
     * @param fxmlFileName jméno souboru bez přípony
     * @return naparsovaný .fxml soubor jako Parent
     * @throws IOException pokud se daný soubor nenajde ve zdrojích
     */
    private static Parent loadFXML(String fxmlFileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxmlFileName + ".fxml"));
        return fxmlLoader.load();
    }
}