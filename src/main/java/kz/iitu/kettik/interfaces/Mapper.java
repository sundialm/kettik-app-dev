package kz.iitu.kettik.interfaces;

import java.util.List;

public interface Mapper<Entity extends AbstractEntity, DTO extends AbstractDTO>{
    Entity toEntity(DTO dto);
    List<Entity> toEntityList(List<DTO> dtoList);

    DTO toDTO(Entity entity);
    List<DTO> toDTOList(List<Entity> entityList);
}
