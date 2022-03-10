package kz.iitu.kettik.exeptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice(annotations = RestController.class)
public class ConstraintViolationExceptionHandler
{
    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<?> handle(ConstraintViolationException exception)
    {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();

        List<String> fields = violations
                .stream()
                .map(violation -> String.format("ОШИБКА: %s value '%s' -> %s",
                        violation.getPropertyPath(),
                        violation.getInvalidValue(),
                        violation.getMessage())
                )
                .collect(Collectors.toList());


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(fields);
    }
}
