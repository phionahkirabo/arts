package com.rra.arts.arts_backend.exception;

import com.rra.arts.arts_backend.dto.StandardApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardApiResponse<Object>> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {
        StandardApiResponse<Object> response = StandardApiResponse.error(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<StandardApiResponse<Object>> handleDuplicateResourceException(
            DuplicateResourceException ex, WebRequest request) {
        StandardApiResponse<Object> response = StandardApiResponse.error(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardApiResponse<Object>> handleBadRequestException(
            BadRequestException ex, WebRequest request) {
        StandardApiResponse<Object> response = StandardApiResponse.error(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<StandardApiResponse<Object>> handleValidationException(
            ValidationException ex, WebRequest request) {
        StandardApiResponse<Object> response = StandardApiResponse.error(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<StandardApiResponse<Object>> handleUnauthorizedException(
            UnauthorizedException ex, WebRequest request) {
        StandardApiResponse<Object> response = StandardApiResponse.error(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<StandardApiResponse<Object>> handleForbiddenException(
            ForbiddenException ex, WebRequest request) {
        StandardApiResponse<Object> response = StandardApiResponse.error(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(WorkflowException.class)
    public ResponseEntity<StandardApiResponse<Object>> handleWorkflowException(
            WorkflowException ex, WebRequest request) {
        StandardApiResponse<Object> response = StandardApiResponse.error(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardApiResponse<Object>> handleDatabaseException(
            DatabaseException ex, WebRequest request) {
        StandardApiResponse<Object> response = StandardApiResponse.error(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<StandardApiResponse<Object>> handleInvalidRequestException(
            InvalidRequestException ex, WebRequest request) {
        StandardApiResponse<Object> response = StandardApiResponse.error(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<StandardApiResponse<Object>> handleFileStorageException(
            FileStorageException ex, WebRequest request) {
        StandardApiResponse<Object> response = StandardApiResponse.error(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardApiResponse<Map<String, String>>> handleValidationErrors(
            MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        StandardApiResponse<Map<String, String>> response = StandardApiResponse.<Map<String, String>>builder()
                .success(false)
                .message("Validation failed")
                .data(errors)
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardApiResponse<Object>> handleDataIntegrityViolation(
            DataIntegrityViolationException ex, WebRequest request) {
        StandardApiResponse<Object> response = StandardApiResponse.error(
                "Database constraint violation: " + ex.getMostSpecificCause().getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardApiResponse<Object>> handleGlobalException(
            Exception ex, WebRequest request) {
        StandardApiResponse<Object> response = StandardApiResponse.error(
                "An unexpected error occurred: " + ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
