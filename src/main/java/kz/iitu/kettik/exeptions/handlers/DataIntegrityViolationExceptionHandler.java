package kz.iitu.kettik.exeptions.handlers;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class DataIntegrityViolationExceptionHandler
{
    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<?> handle(DataIntegrityViolationException exception)
    {
        String message = NestedExceptionUtils
                .getMostSpecificCause(exception)
                .getLocalizedMessage();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.valueOf("text/plain;charset=UTF-8"))
                .body(message);
    }
}
