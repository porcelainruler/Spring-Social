package com.shubham.project.spring_network.exceptions;

import com.shubham.project.spring_network.dto.response.ExceptionResponse;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionsHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request){
        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
        ExceptionResponse exceptionMessage = new ExceptionResponse(ex.getMessage(), requestUri);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    /**
     * Handles authentication error
     * @param ex
     * @return
     */
    @ExceptionHandler({ AuthenticationException.class })
    @ResponseBody
    public ResponseEntity<Object> handleAuthenticationException(Exception ex) {
        String requestUri = "NA";
        ExceptionResponse exceptionMessage = new ExceptionResponse(ex.getMessage(), requestUri);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    /**
     * JWT token expired exception handling
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
        ExceptionResponse exceptionMessage = new ExceptionResponse(ex.getMessage(), requestUri);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    /**
     * handlerOtherExceptions handles any unhandled exceptions.
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
        ExceptionResponse exceptionMessage = new ExceptionResponse(ex.getMessage(), requestUri);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
