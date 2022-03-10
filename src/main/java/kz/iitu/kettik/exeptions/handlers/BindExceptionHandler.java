package kz.iitu.kettik.exeptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(annotations = RestController.class)
public class BindExceptionHandler
{
    @ExceptionHandler(BindException.class)
    private ResponseEntity<?> handle(BindException exception)
    {
        List<String> errors = exception
                .getFieldErrors()
                .stream()
                .map(fieldError -> String.format("ОШИБКА: %s -> %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.valueOf("text/plain;charset=UTF-8"))
                .body(errors);
    }
}
