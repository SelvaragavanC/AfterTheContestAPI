package com.selvaragavan.afterthecontestapi.exceptions;

public class MailException extends RuntimeException{
    private String message;
    public MailException(String message) {this.message = message;}

    @Override
    public String getMessage() {return message;}
}
