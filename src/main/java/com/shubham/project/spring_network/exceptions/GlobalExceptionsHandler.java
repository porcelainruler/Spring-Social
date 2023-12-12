package com.shubham.project.spring_network.exceptions;

import com.shubham.project.spring_network.constant.ApiResponseStatus;
import com.shubham.project.spring_network.dto.response.ApiResponse;
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
    @ResponseBody
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
    public ApiResponse<ResponseEntity<Object>> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request){
        ApiResponse<ResponseEntity<Object>> apiResponse = null;

        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
        ExceptionResponse exceptionMessage = new ExceptionResponse(ex.getMessage(), requestUri);

        apiResponse = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), ApiResponseStatus.ACCESS_DENIED.getValue(), "Global exception caught", new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.FORBIDDEN));

        return apiResponse;
    }

    /**
     * Handles authentication error
     * @param ex
     * @return
     */
    @ExceptionHandler({ AuthenticationException.class })
    @ResponseBody
    public ApiResponse<ResponseEntity<Object>> handleAuthenticationException(Exception ex) {
        ApiResponse<ResponseEntity<Object>> apiResponse = null;

        String requestUri = "NA";
        ExceptionResponse exceptionMessage = new ExceptionResponse(ex.getMessage(), requestUri);

        apiResponse = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), ApiResponseStatus.ACCESS_DENIED.getValue(), "Global exception caught", new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.FORBIDDEN));

        return apiResponse;
    }

    //    @ExceptionHandler(BadCredentialsException.class)
    //    @ResponseBody
    //    public ApiResponse<ResponseEntity<Object>> exceptionHandler(Exception ex) {
    //        ApiResponse<ResponseEntity<Object>> apiResponse = null;
    //
    //        String requestUri = "NA";
    //        ExceptionResponse exceptionMessage = new ExceptionResponse(ex.getMessage(), requestUri);
    //
    //        apiResponse = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), apiResponseStatus.ACCESS_DENIED.getValue(), "Global exception caught", new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.FORBIDDEN));
    //
    //        return apiResponse;
    //    }

    /**
     * JWT token expired exception handling
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {ExpiredJwtException.class})
    @ResponseBody
    public ApiResponse<ResponseEntity<Object>> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        ApiResponse<ResponseEntity<Object>> apiResponse = null;

        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
        ExceptionResponse exceptionMessage = new ExceptionResponse(ex.getMessage(), requestUri);

        apiResponse = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), ApiResponseStatus.ACCESS_DENIED.getValue(), "Global exception caught", new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.FORBIDDEN));

        return apiResponse;
    }

    /**
     * handlerOtherExceptions handles any unhandled exceptions.
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ApiResponse<ResponseEntity<Object>> handleOtherExceptions(Exception ex, WebRequest request) {
        ApiResponse<ResponseEntity<Object>> apiResponse = null;

        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
        ExceptionResponse exceptionMessage = new ExceptionResponse(ex.getMessage(), requestUri);

        apiResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ApiResponseStatus.INTERNAL_SERVER_ERROR.getValue(), "Global exception caught", new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR));

        return apiResponse;
    }

}
