package cz.cuni.mff.zucha.evidenceKnihovny.business;

import java.nio.file.*;
import javafx.collections.*;

import org.apache.commons.io.FilenameUtils;

/**
 * Kořenová třída pro veškerá ukládaná data aplikace. Obsahuje kolekci sekcí knihovny, sama nenese žádnou informaci,
 * vše je hlouběji ve struktuře dat (po kolekcích). Nese informace k IO.
 */
public class Library {
    /**
     * Cesta k adresáři, kam tato knihovna náleží. Vede-li na přímo na soubor, jméno nemusí být použito pro ukládání.
     * Soubor může být použit pro opětovné načtení.
     */
    private Path filePath;
    /**
     * Getter cesty k adresáři.
     * @return cesta k adresáři.
     */
    public Path getFilepath() { return filePath; }
    /**
     * Setter cesty k adresáři.
     * @param filePath cesta k adresáři.
     */
    public void setFilepath(Path filePath) { this.filePath = filePath; }

    /**
     * Jméno souboru s knihovnou. Je defacto nezávislé na jméně souboru ve {@link #filePath} a mělo by být použito pro
     * ukládání.
     */
    private String filename;
    /**
     * Getter jména souboru.
     * @return Jméno souboru jako {@link String}.
     */
    public String getFilename() {
        return filename;
    }
    /**
     * Setter jména souboru.
     * @param filename jméno souboru.
     */
    public void setFilename(String filename) { this.filename = filename; }

    /**
     * Oddělení v této knihovně.
     */
    private ObservableList<Section> sections;
    /**
     * Getter seznamu oddělení.
     * @return Seznam oddělení knihovny.
     */
    public ObservableList<Section> getSections() {
        return sections;
    }

    /**
     * Vytvoří knihovnu beze jména. Cesta neexistuje a jméno souboru je generické "beze jména".
     */
    public Library() {
        this(AppStrings.noNameFile);
    }

    /**
     * Vytvoří knihovnu se zadaným jménem. Cesta neexistuje.
     * @param filename jméno souboru pro knihovnu
     */
    public Library(String filename) {
        this(null, filename);
    }

    /**
     * Vytvoří knihovnu. Je-li zadána {@link #filePath} existující cesta na soubor, a jméno je zadáno prázdné (či null),
     * opíše se do {@link #filename}.
     * @param filePath cesta do adresáře se souborem knihovny či na původní soubor knihovny.
     * @param filename jméno souboru pro knihovnu
     */
    public Library(Path filePath, String filename) {
        this.filePath = filePath;
        this.filename = filename == null ? "" : filename;

        if ((this.filePath != null) && Files.exists(this.filePath)) {
            if (!Files.isDirectory(this.filePath) && this.filename.isEmpty()) {
                this.filename = FilenameUtils.removeExtension(this.filePath.getFileName().toString());
            }
        }

        sections = FXCollections.observableArrayList();
    }
}
