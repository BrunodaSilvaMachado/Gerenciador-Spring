package br.com.adaca.handler;

import br.com.adaca.util.ErrorDetails;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFound(NotFoundException e, WebRequest request) {
        ErrorDetails error = new ErrorDetails(
                System.currentTimeMillis(), 404L, "Entidade não encontrada", "https://errors.adaca.com.br/404"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ConflictException.class)
    protected ResponseEntity<Object> resourceConflict(ConflictException e, WebRequest request) {
        ErrorDetails error = new ErrorDetails(
                System.currentTimeMillis(), 404L, "Entidade já persistida", "https://errors.adaca.com.br/409"
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

}
