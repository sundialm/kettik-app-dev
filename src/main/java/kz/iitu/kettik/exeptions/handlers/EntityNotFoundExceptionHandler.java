package kz.iitu.kettik.exeptions.handlers;

import kz.iitu.kettik.exeptions.EntityNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class EntityNotFoundExceptionHandler
{
    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<?> handle(EntityNotFoundException exception)
    {
        return ResponseEntity
                .unprocessableEntity()
                .contentType(MediaType.valueOf("text/plain;charset=UTF-8"))
                .body(exception.getMessage());
    }
}
