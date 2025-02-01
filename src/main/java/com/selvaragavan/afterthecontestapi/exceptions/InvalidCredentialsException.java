package com.selvaragavan.afterthecontestapi.exceptions;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvalidCredentialsException extends RuntimeException{
    private String message;
}
