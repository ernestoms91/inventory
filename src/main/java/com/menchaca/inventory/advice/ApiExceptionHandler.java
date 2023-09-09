package com.menchaca.inventory.advice;

import com.menchaca.inventory.exception.ObjectPropertyRepeatedException;
import com.menchaca.inventory.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
}
