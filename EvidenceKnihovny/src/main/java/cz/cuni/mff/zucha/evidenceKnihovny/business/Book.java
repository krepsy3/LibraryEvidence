package cz.cuni.mff.zucha.evidenceKnihovny.business;

import org.jdom2.Element;

public class Book extends Rentable<Book.BookInstance> {

    /**
     * Jméno pro XML Element reprezentující tuto třídu
     */
    public static final String XMLElementName = "book";


    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    private String originalTitle;

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }


    private String translator;

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }


    private String originalLanguage;

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }
    
    
    private String illustrator;

    public String getIllustrator() {
        return illustrator;
    }
    
    public void setIllustrator(String illustrator) {
        this.illustrator = illustrator;
    }


    private String bookcode;

    public String getBookcode() {
        return bookcode;
    }

    public void setBookcode(String bookcode) {
        this.bookcode = bookcode;
    }


    private String publisher;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


    private Integer publishingYear;

    public Integer getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(Integer publishingYear) {
        this.publishingYear = publishingYear;
    }


    private Integer edition;

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }


    @Override
    public String getElementName() {
        return XMLElementName;
    }

    @Override
    public Element toXMLElement() {
        Element result = super.toXMLElement();

        result.setAttribute("author", author);
        result.setAttribute("title", title);
        result.setAttribute("language", language);
        result.setAttribute("translator", translator);
        result.setAttribute("originalTitle", originalTitle);
        result.setAttribute("originalLanguage", originalLanguage);
        result.setAttribute("illustrator", illustrator);
        result.setAttribute("bookcode", bookcode);
        result.setAttribute("publisher", publisher);
        result.setAttribute("publishingYear", publishingYear.toString());
        result.setAttribute("edition", edition.toString());

        return result;
    }


    public class BookInstance extends RentableInstance {

        /**
         * Jméno pro XML Element reprezentující tuto třídu
         */
        public static final String XMLElementName = "bookInstance";


        protected SuperProperty<String> translator = new SuperProperty<>(Book.this::getTranslator);

        public String getTranslator() { return translator.get(); }

        public void setTranslator(String translator) {
            this.translator.set(translator);
        }


        protected SuperProperty<String> originalTitle = new SuperProperty<>(Book.this::getOriginalTitle);

        public String getOriginalTitle() {
            return originalTitle.get();
        }

        public void setOriginalTitle(String originalTitle) {
            this.originalTitle.set(originalTitle);
        }

        protected SuperProperty<String> originalLanguage = new SuperProperty<>(Book.this::getOriginalLanguage);

        public String getOriginalLanguage() {
            return originalLanguage.get();
        }

        public void setOriginalLanguage() {
            this.originalLanguage.get();
        }


        protected SuperProperty<String> illustrator = new SuperProperty<>(Book.this::getIllustrator);

        public String getIllustrator() {
            return illustrator.get();
        }

        public void setIllustrator(String illustrator) {
            this.illustrator.set(illustrator);
        }


        protected SuperProperty<String> bookcode = new SuperProperty<>(Book.this::getBookcode);

        public String getBookcode() {
            return bookcode.get();
        }

        public void setBookcode(String bookcode) {
            this.bookcode.set(bookcode);
        }


        protected SuperProperty<String> publisher = new SuperProperty<>(Book.this::getPublisher);

        public String getPublisher() {
            return this.publisher.get();
        }

        public void setPublisher(String publisher) {
            this.publisher.set(publisher);
        }


        protected SuperProperty<Integer> publishingYear = new SuperProperty<>(Book.this::getPublishingYear);

        public int getPublishingYear() {
            return publishingYear.get();
        }

        public void setPublishingYear(int publishingYear) {
            this.publishingYear.set(publishingYear);
        }


        protected SuperProperty<Integer> edition = new SuperProperty<>(Book.this::getEdition);

        public int getEdition() {
            return edition.get();
        }

        public void setEdition(int edition) {
            this.edition.set(edition);
        }


        @Override
        public String getElementName() {
            return XMLElementName;
        }

        @Override
        public Element toXMLElement() {
            Element result = super.toXMLElement();

            if (translator.isDefined()) {
                result.setAttribute("translator", translator.get());
            }

            if (originalTitle.isDefined()) {
                result.setAttribute("originalTitle", originalTitle.get());
            }

            if (originalLanguage.isDefined()) {
                result.setAttribute("originalLanguage", originalLanguage.get());
            }

            if (illustrator.isDefined()) {
                result.setAttribute("illustrator", illustrator.get());
            }

            if (bookcode.isDefined()) {
                result.setAttribute("bookcode", bookcode.get());
            }

            if (publisher.isDefined()) {
                result.setAttribute("publisher", publisher.get());
            }

            if (publishingYear.isDefined()) {
                result.setAttribute("publishingYear", publishingYear.get().toString());
            }

            if (edition.isDefined()) {
                result.setAttribute("edition", edition.get().toString());
            }

            return result;
        }
    }
}
