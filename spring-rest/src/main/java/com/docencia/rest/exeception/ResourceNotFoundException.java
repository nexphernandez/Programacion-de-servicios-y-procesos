package com.docencia.rest.exeception;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }

    public ResourceNotFoundException(String mensaje,Throwable myException){
        super(mensaje, myException);
    }

}
