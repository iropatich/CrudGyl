package com.gyl.CrudGyl.exception;

public class EmailExistenteException extends RuntimeException {
    public EmailExistenteException(String mensaje) {
        super(mensaje);
    }
}
