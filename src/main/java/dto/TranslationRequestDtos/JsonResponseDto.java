package dto.TranslationRequestDtos;

import dto.TranslationRequestDtos.JsonRequestDto;

import java.math.BigDecimal;

public class JsonResponseDto extends JsonRequestDto {

    BigDecimal order_number;
    BigDecimal price;
    String status;
    String uid;
    String translatedText;

    public JsonResponseDto() {
    }

    public JsonResponseDto(String text, String sourceLanguage, String targuetLanguage, String textFormat) {
        super(text, sourceLanguage, targuetLanguage,textFormat);
    }

    public JsonResponseDto(String text, String sourceLanguage, String targuetLanguage, String textFormat, BigDecimal order_number, BigDecimal price, String status, String uid, String translatedText) {
        super(text, sourceLanguage, targuetLanguage, textFormat);
        this.order_number = order_number;
        this.price = price;
        this.status = status;
        this.uid = uid;
        this.translatedText = translatedText;
    }

    public BigDecimal getOrder_number() {
        return order_number;
    }

    public void setOrder_number(BigDecimal order_number) {
        this.order_number = order_number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }
}
