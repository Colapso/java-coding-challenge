package dto;

public class JsonRequestDTO {
    String text;
    String sourceLanguage;
    String targuetLanguage;

    public JsonRequestDTO(String text, String sourceLanguage, String targuetLanguage) {
        this.text = text;
        this.sourceLanguage = sourceLanguage;
        this.targuetLanguage = targuetLanguage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getTarguetLanguage() {
        return targuetLanguage;
    }

    public void setTarguetLanguage(String targuetLanguage) {
        this.targuetLanguage = targuetLanguage;
    }


}
