//package com.shubham.project.spring_network.filter;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.shubham.project.spring_network.constant.ApiResponseStatus;
//import com.shubham.project.spring_network.dto.response.ApiResponse;
//import com.shubham.project.spring_network.dto.response.ExceptionResponse;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.hibernate.annotations.Filter;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.ServletWebRequest;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class ExceptionHandlerFilter extends OncePerRequestFilter {
//
//    @Override
//    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        try {
//            filterChain.doFilter(request, response);
//        } catch (RuntimeException e) {
//
//            // custom error response class used across my project
//            ApiResponse<ResponseEntity<Object>> apiResponse = null;
//
//            String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
//            ExceptionResponse exceptionMessage = new ExceptionResponse(e.getMessage(), requestUri);
//
//            apiResponse = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), ApiResponseStatus.ACCESS_DENIED.getValue(), "Global exception caught", new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.FORBIDDEN));
//
//
//            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            response.getWriter().write(convertObjectToJson(apiResponse));
//        }
//    }
//
//    public String convertObjectToJson(Object object) throws JsonProcessingException {
//        if (object == null) {
//            return null;
//        }
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(object);
//    }
//}
