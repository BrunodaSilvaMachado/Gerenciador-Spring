package br.com.adaca.handler;

import br.com.adaca.exception.EmptyException;
import br.com.adaca.util.ErrorDetails;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


//@RestController
@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    public ResourceExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @ExceptionHandler(EmptyException.class)
    @ResponseBody
    public void resourceEmpty(EmptyException ex, WebRequest request) {
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    protected ErrorDetails resourceNotFound(NotFoundException ex, WebRequest request) {
        ErrorDetails msg = new ErrorDetails(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado.",
                ex.getMessage()
        );
        return msg;
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    @ResponseBody
    protected ErrorDetails resourceConflict(ConflictException ex, HttpStatus status, WebRequest request) {
        ErrorDetails msg = new ErrorDetails(
                System.currentTimeMillis(),
                HttpStatus.CONFLICT.value(),
                "Recurso conflitante.",
                ex.getMessage()
        );
        return msg;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Locale local = LocaleContextHolder.getLocale();
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        List<String> mensagens = new ArrayList<>();
        errors.stream().forEach(
                msg -> {
                    String campo = msg.getField().substring(msg.getField().indexOf(".") + 1);
                    String error = messageSource.getMessage(msg, local);
                    mensagens.add("Campo " + campo + ": " + error);
                }
        );
        ErrorDetails msg = new ErrorDetails(
                System.currentTimeMillis(),
                status.value(),
                status.getReasonPhrase(),
                "Augmentos inválidos."
        );
        msg.setErrors(mensagens);
        return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
    }

}
