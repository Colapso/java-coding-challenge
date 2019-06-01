package dto.LangPairsDtos;

import java.util.List;

public class LangPairObjectsContainerDto {

    List<LangPairContainerDto> objects;

    public LangPairObjectsContainerDto() {
    }

    public LangPairObjectsContainerDto(List<LangPairContainerDto> objects) {
        this.objects = objects;
    }

    public List<LangPairContainerDto> getObjects() {
        return objects;
    }

    public void setObjects(List<LangPairContainerDto> objects) {
        this.objects = objects;
    }
}
