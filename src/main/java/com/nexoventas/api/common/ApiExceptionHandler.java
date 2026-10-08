package com.nexoventas.api.common;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.OffsetDateTime;
import java.util.*;
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class) ResponseEntity<Map<String,Object>> notFound(NoSuchElementException e) { return response(HttpStatus.NOT_FOUND,e.getMessage()); }
    @ExceptionHandler({IllegalArgumentException.class,IllegalStateException.class}) ResponseEntity<Map<String,Object>> badRequest(RuntimeException e) { return response(HttpStatus.BAD_REQUEST,e.getMessage()); }
    @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<Map<String,Object>> validation(MethodArgumentNotValidException e) { return response(HttpStatus.BAD_REQUEST,e.getBindingResult().getFieldErrors().stream().map(f -> f.getField()+": "+f.getDefaultMessage()).findFirst().orElse("Solicitud inválida")); }
    private ResponseEntity<Map<String,Object>> response(HttpStatus status, String message) { return ResponseEntity.status(status).body(Map.of("timestamp", OffsetDateTime.now().toString(), "status",status.value(),"message",message)); }
}
