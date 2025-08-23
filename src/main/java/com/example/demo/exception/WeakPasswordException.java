// src/main/java/com/example/demo/exception/WeakPasswordException.java
package com.example.demo.exception;

public class WeakPasswordException extends RuntimeException {
    public WeakPasswordException() {
        super("La contraseña no cumple los requisitos de seguridad.");
    }
}
