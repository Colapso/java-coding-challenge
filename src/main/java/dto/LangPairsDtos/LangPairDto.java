package dto.LangPairsDtos;


public class LangPairDto {

    LanguageDto source_language;
    LanguageDto target_language;

    public LangPairDto() {
    }

    public LangPairDto(LanguageDto source_language, LanguageDto target_language) {
        this.source_language = source_language;
        this.target_language = target_language;
    }

    public LanguageDto getSource_language() {
        return source_language;
    }

    public void setSource_language(LanguageDto source_language) {
        this.source_language = source_language;
    }

    public LanguageDto getTarget_language() {
        return target_language;
    }

    public void setTarget_language(LanguageDto target_language) {
        this.target_language = target_language;
    }

    @Override
    public String toString() {
        return "LangPairDto{" +
                "source_language=" + source_language +
                ", target_language=" + target_language +
                '}';
    }
}
