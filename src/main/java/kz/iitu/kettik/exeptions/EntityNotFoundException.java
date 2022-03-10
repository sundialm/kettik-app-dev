package kz.iitu.kettik.exeptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter @Setter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    private String entity;
    private String field;
    private Object value;
    private String message;


    private String resource;
    private Long id;
    private String email;

    public EntityNotFoundException(String entity, String field, Object value) {
        this.entity = entity;

        this.field = field;
        this.value = value;

        this.message = createMessage();
    }

    public EntityNotFoundException(String resource, String email) {
        super();
        this.resource = resource;
        this.email = email;
    }

    private String createMessage() {
        return "ОШИБКА: " + entity + " field " + field + " with value " + value + " not found...";
    }
}
