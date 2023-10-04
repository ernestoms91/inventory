package com.menchaca.inventory.advice;

import com.menchaca.inventory.exception.InvalidFileTypeException;
import com.menchaca.inventory.exception.ObjectPropertyRepeatedException;
import com.menchaca.inventory.exception.ObjectNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler extends Throwable {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleInvalidArgument(MethodArgumentNotValidException  ex ,HttpServletRequest request){
        Map<String, Object> errorMap = new LinkedHashMap<>();
    Map<String, String> errorsMap = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->{
                errorsMap.put(error.getField(), error.getDefaultMessage());
            });

    errorMap.put("status", HttpStatus.BAD_REQUEST.value());
    errorMap.put("time", LocalDateTime.now().toString());
    errorMap.put("url", request.getServletPath());
    errorMap.put("msg", "Valores incorrectos");
    errorMap.put("errors", errorsMap);


    return errorMap;
    }



    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException  ex ,HttpServletRequest request){
        Map<String, Object> errorMap = new LinkedHashMap<>();
    errorMap.put("status", HttpStatus.BAD_REQUEST.value());
    errorMap.put("time", LocalDateTime.now().toString());
    errorMap.put("url", request.getServletPath());
    errorMap.put("msg", "El valor de " + ex.getName() + ": " + ex.getValue() + " no es correcto, debe ser de tipo: " + ex.getRequiredType());
    return errorMap;
    }


    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleObjectNotFoundException (ObjectNotFoundException ex, HttpServletRequest request){
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("status", HttpStatus.NOT_FOUND.value());
        errorMap.put("time", LocalDateTime.now().toString());
        errorMap.put("url", request.getServletPath());
        errorMap.put("error", "Not Found");
        errorMap.put("msg", ex.getMessage());
    return errorMap;
    }
    @ExceptionHandler(InvalidFileTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleInvalidFileTypeException (InvalidFileTypeException ex, HttpServletRequest request){
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("status", HttpStatus.BAD_REQUEST.value());
        errorMap.put("time", LocalDateTime.now().toString());
        errorMap.put("url", request.getServletPath());
        errorMap.put("error", "Bad request");
        errorMap.put("msg", ex.getMessage());
    return errorMap;
    }

    @ExceptionHandler(ObjectPropertyRepeatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleObjectPropertyRepeatedException(ObjectPropertyRepeatedException ex, HttpServletRequest request){
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("status", HttpStatus.BAD_REQUEST.value());
        errorMap.put("time", LocalDateTime.now().toString());
        errorMap.put("url", request.getServletPath());
        errorMap.put("error", "Bad Request");
        errorMap.put("msg", ex.getMessage());
    return errorMap;
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request){
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("status", HttpStatus.BAD_REQUEST.value());
        errorMap.put("time", LocalDateTime.now().toString());
        errorMap.put("url", request.getServletPath());
        errorMap.put("error", "Bad Request");
        errorMap.put("msg", ex.getMessage());
    return errorMap;
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex, HttpServletRequest request){
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("status", HttpStatus.BAD_REQUEST.value());
        errorMap.put("time", LocalDateTime.now().toString());
        errorMap.put("url", request.getServletPath());
        errorMap.put("error", "Bad Request");
        errorMap.put("msg", ex.getMessage());
    return errorMap;
    }
    @ExceptionHandler(InvalidMediaTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleInvalidMediaTypeException(InvalidMediaTypeException ex, HttpServletRequest request){
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("status", HttpStatus.BAD_REQUEST.value());
        errorMap.put("time", LocalDateTime.now().toString());
        errorMap.put("url", request.getServletPath());
        errorMap.put("error", "Bad Request");
        errorMap.put("msg", ex.getMessage());
    return errorMap;
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> handleAccessDeniedException(BadCredentialsException ex, HttpServletRequest request){
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("status", HttpStatus.FORBIDDEN.value());
        errorMap.put("time", LocalDateTime.now().toString());
        errorMap.put("url", request.getServletPath());
        errorMap.put("error", "Unauthorized Request");
        errorMap.put("msg", ex.getMessage());
    return errorMap;
    }


    @ExceptionHandler(AccountStatusException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object>handleAccountStatusException(AccountStatusException ex, HttpServletRequest request) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("status", HttpStatus.UNAUTHORIZED.value());
        errorMap.put("time", LocalDateTime.now().toString());
       errorMap.put("url", request.getServletPath());
        errorMap.put("error", "Unauthorized Request");
        errorMap.put("msg", ex.getMessage());
        return errorMap;
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object>handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("status", HttpStatus.FORBIDDEN.value());
        errorMap.put("time", LocalDateTime.now().toString());
       errorMap.put("url", request.getServletPath());
        errorMap.put("error", "Unauthorized Request");
        errorMap.put("msg", ex.getMessage());
        return errorMap;
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object>handleExpiredJwtException(ExpiredJwtException ex, HttpServletRequest request) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("status", HttpStatus.FORBIDDEN.value());
        errorMap.put("time", LocalDateTime.now().toString());
       errorMap.put("url", request.getServletPath());
        errorMap.put("error", "Unauthorized Request");
        errorMap.put("msg", ex.getMessage());
        return errorMap;
    }
    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object>handleSignatureException(SignatureException ex, HttpServletRequest request) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("status", HttpStatus.FORBIDDEN.value());
        errorMap.put("time", LocalDateTime.now().toString());
       errorMap.put("url", request.getServletPath());
        errorMap.put("error", "Unauthorized Request");
        errorMap.put("msg", ex.getMessage());
        return errorMap;
    }

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object>handleJwtException(JwtException ex, HttpServletRequest request) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("status", HttpStatus.FORBIDDEN.value());
        errorMap.put("time", LocalDateTime.now().toString());
       errorMap.put("url", request.getServletPath());
        errorMap.put("error", "Unauthorized Request");
        errorMap.put("msg", ex.getMessage());
        return errorMap;
    }
//    @ExceptionHandler(ResponseStatusException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public Map<String, Object>handleJwtException(ResponseStatusException  ex, HttpServletRequest request) {
//        Map<String, Object> errorMap = new LinkedHashMap<>();
//        errorMap.put("status", HttpStatus.FORBIDDEN.value());
//        errorMap.put("time", LocalDateTime.now().toString());
//       errorMap.put("url", request.getServletPath());
//        errorMap.put("error", "Unauthorized Request");
//        errorMap.put("msg", ex.getMessage());
//        return errorMap;
//    }



}
