package dto.LangPairsDtos;

public class LangPairContainerDto {

    LangPairDto lang_pair;

    public LangPairContainerDto() {
    }

    public LangPairContainerDto(LangPairDto lang_pair) {
        this.lang_pair = lang_pair;
    }

    public LangPairDto getLang_pair() {
        return lang_pair;
    }

    public void setLang_pair(LangPairDto lang_pair) {
        this.lang_pair = lang_pair;
    }
}
