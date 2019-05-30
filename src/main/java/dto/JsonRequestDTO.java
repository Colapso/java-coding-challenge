package dto;

public class JsonRequestDTO {
    String text;
    String source_language;
    String target_language;
    String text_format;

    public JsonRequestDTO() {
    }



    public JsonRequestDTO(String text, String sourceLanguage, String targuetLanguage, String textFormat) {
        this.text = text;
        this.source_language = sourceLanguage;
        this.target_language = targuetLanguage;
        this.text_format = textFormat;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSourceLanguage() {
        return source_language;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.source_language = sourceLanguage;
    }

    public String getTarguetLanguage() {
        return target_language;
    }

    public void setTarguetLanguage(String targuetLanguage) {
        this.target_language = targuetLanguage;
    }

    public String getTextFormat() {
        return text_format;
    }

    public void setTextFormat(String textFormat) {
        this.text_format = textFormat;
    }

    @Override
    public String toString() {
        return "{\"text\" : \"" + text + "\", \"source_language\" : \"" + source_language + "\", \"target_language\" : \"" +
                target_language+"\", \"text_format\" : \"" + text_format + "\"}";
    }
}
