package com.example.demo.exception;

import com.example.demo.DTO.ApiError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {

    /* ---------- 404 ---------- */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest req) {

        return build(HttpStatus.NOT_FOUND, ex.getMessage(), req);
    }

    /* ---------- 400 ---------- */
    @ExceptionHandler({
            BadRequestException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<ApiError> handleBadRequest(
            Exception ex,
            HttpServletRequest req) {

        String msg = ex instanceof MethodArgumentNotValidException m ?
                m.getBindingResult().getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.joining("; "))
                : ex.getMessage();

        return build(HttpStatus.BAD_REQUEST, msg, req);
    }

    /* ---------- 409 ---------- */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleConflict(
            DataIntegrityViolationException ex,
            HttpServletRequest req) {

        return build(HttpStatus.CONFLICT,
                "Integridad de datos incumplida", req);
    }

    /* ---------- 500 (cualquier otra) ---------- */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(
            Exception ex, HttpServletRequest req) {

        return build(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error interno inesperado", req);
    }

    /* ---------- helper ---------- */
    private ResponseEntity<ApiError> build(HttpStatus status,
                                           String message,
                                           HttpServletRequest req) {
        ApiError err = new ApiError();
        err.setStatus(status.value());
        err.setError(status.getReasonPhrase());
        err.setMessage(message);
        err.setPath(req.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
