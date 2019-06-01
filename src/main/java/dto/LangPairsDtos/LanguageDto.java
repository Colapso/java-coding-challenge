package dto.LangPairsDtos;

public class LanguageDto {

    public String name;
    public String shortname;

    public LanguageDto() {
    }

    public LanguageDto(String name, String shortname) {
        this.name = name;
        this.shortname = shortname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    @Override
    public String toString() {
        return "LanguageDto{" +
                "name='" + name + '\'' +
                ", shortname='" + shortname + '\'' +
                '}';
    }
}
