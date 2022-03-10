package kz.iitu.kettik.interfaces;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractMapper<Entity extends AbstractEntity, DTO extends AbstractDTO> implements Mapper<Entity, DTO> {
    @Autowired
    private ModelMapper mapper;

    private Class<Entity> entityClass;
    private Class<DTO> dtoClass;

    public AbstractMapper(Class<Entity> entityClass, Class<DTO> dtoClass)
    {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public Entity toEntity(DTO dto)
    {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }

    @Override
    public List<Entity> toEntityList(List<DTO> dtoList)
    {
        return dtoList.isEmpty()
                ? new ArrayList<Entity>()
                : dtoList
                        .stream()
                        .map(this::toEntity)
                        .collect(Collectors.toList());
    }

    @Override
    public DTO toDTO(Entity entity)
    {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }

    @Override
    public List<DTO> toDTOList(List<Entity> entityList)
    {
        return entityList.isEmpty()
                ? new ArrayList<DTO>()
                : entityList
                        .stream()
                        .map(this::toDTO)
                        .collect(Collectors.toList());
    }

    public Converter<Entity, DTO> toDTOConverter()
    {
        return context ->
        {
            Entity source = context.getSource();
            DTO destination = context.getDestination();

            mapSpecificFields(source, destination);

            return context.getDestination();
        };
    }

    public Converter<DTO, Entity> toEntityConverter()
    {
        return context ->
        {
            DTO source = context.getSource();
            Entity destination = context.getDestination();

            mapSpecificFields(source, destination);

            return context.getDestination();
        };
    }

    public void mapSpecificFields(Entity entity, DTO dto) {}
    public void mapSpecificFields(DTO dto, Entity entity) {}
}
