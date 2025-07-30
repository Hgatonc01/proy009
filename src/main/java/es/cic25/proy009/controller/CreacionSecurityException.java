package es.cic25.proy009.controller;

public class CreacionSecurityException extends RuntimeException{
    
    public CreacionSecurityException(){
        super("Intento de creación en la modificación");
    }

    public CreacionSecurityException(String mensaje){
        super(mensaje);
    }

    public CreacionSecurityException(String mensaje, Throwable throwable){
        super(mensaje, throwable);
    }
}