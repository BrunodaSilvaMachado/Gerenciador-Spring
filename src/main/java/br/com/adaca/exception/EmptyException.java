package br.com.adaca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.CONFLICT)
public class EmptyException extends RuntimeException {

    public EmptyException(String message) { super(message); }
    public EmptyException(){ super(); }
}
