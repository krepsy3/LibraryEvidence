package cz.cuni.mff.zucha.evidenceKnihovny.business;

import org.jdom2.Element;

public class CD extends Rentable<CD.CDInstance> {

    /**
     * Jméno pro XML Element reprezentující tuto třídu
     */
    public static final String XMLElementName = "mediaDisc";


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


    private String cdcode;

    public String getCDcode() {
        return cdcode;
    }

    public void setCDcode(String cdcode) {
        this.cdcode = cdcode;
    }


    private String recorder;

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
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


    private Integer duration;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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
        result.setAttribute("cdcode", cdcode);
        result.setAttribute("recorder", recorder);
        result.setAttribute("publisher", publisher);
        result.setAttribute("publishingYear", publishingYear.toString());
        result.setAttribute("duration", duration.toString());
        result.setAttribute("edition", edition.toString());

        return result;
    }


    public class CDInstance extends RentableInstance {

        /**
         * Jméno pro XML Element reprezentující tuto třídu
         */
        public static final String XMLElementName = "mediaDiscInstance";


        protected SuperProperty<String> cdcode = new SuperProperty<>(CD.this::getCDcode);

        public String getCDcode() {
            return cdcode.get();
        }

        public void setCDcode(String cdcode) {
            this.cdcode.set(cdcode);
        }


        protected SuperProperty<String> recorder = new SuperProperty<>(CD.this::getPublisher);

        public String getRecoder() {
            return recorder.get();
        }

        public void setRecoder(String recorder) {
            this.recorder.set(recorder);
        }


        protected SuperProperty<String> publisher = new SuperProperty<>(CD.this::getPublisher);

        public String getPublisher() {
            return publisher.get();
        }

        public void setPublisher(String publisher) {
            this.publisher.set(publisher);
        }


        protected SuperProperty<Integer> publishingYear = new SuperProperty<>(CD.this::getPublishingYear);

        public int getPublishingYear() {
            return publishingYear.get();
        }

        public void setPublishingYear(int publishingYear) {
            this.publishingYear.set(publishingYear);
        }


        protected SuperProperty<Integer> duration = new SuperProperty<>(CD.this::getDuration);

        public int getDuraion() {
            return duration.get();
        }

        public void setDuration(int duration) {
            this.duration.set(duration);
        }


        protected SuperProperty<Integer> edition = new SuperProperty<>(CD.this::getEdition);

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

            if (cdcode.isDefined()) {
                result.setAttribute("cdcode", cdcode.get());
            }

            if (recorder.isDefined()) {
                result.setAttribute("recorder", recorder.get());
            }

            if (publisher.isDefined()) {
                result.setAttribute("publisher", publisher.get());
            }

            if (publishingYear.isDefined()) {
                result.setAttribute("publishingYear", publishingYear.get().toString());
            }

            if (duration.isDefined()) {
                result.setAttribute("duration", duration.get().toString());
            }

            if (edition.isDefined()) {
                result.setAttribute("edition", edition.get().toString());
            }

            return result;
        }
    }
}