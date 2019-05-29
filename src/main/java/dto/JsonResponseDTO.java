package dto;

import java.math.BigDecimal;

public class JsonResponseDTO extends JsonRequestDTO {

    BigDecimal order_number;
    BigDecimal price;
    String status;
    String text_format;
    String uid;

    public JsonResponseDTO(String text, String sourceLanguage, String targuetLanguage, BigDecimal order_number, BigDecimal price, String status, String text_format, String uid) {
        super(text, sourceLanguage, targuetLanguage);
        this.order_number = order_number;
        this.price = price;
        this.status = status;
        this.text_format = text_format;
        this.uid = uid;
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

    public String getText_format() {
        return text_format;
    }

    public void setText_format(String text_format) {
        this.text_format = text_format;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
