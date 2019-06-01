package dto.LangPairsDtos;

public class LangPairObjectsContainerDto {

    LangPairContainerDto[] objects;

    public LangPairObjectsContainerDto() {
    }

    public LangPairObjectsContainerDto(LangPairContainerDto[] objects) {
        this.objects = objects;
    }

    public void setObjects(LangPairContainerDto[] objects) {
        this.objects = objects;
    }

    public LangPairContainerDto[] getObjects() {
        return objects;
    }
}
