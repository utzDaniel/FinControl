package br.com.develop.finControl.controller;

import br.com.develop.finControl.exception.RegraExeception;
import br.com.develop.finControl.response.ErrorResponse;
import br.com.develop.finControl.response.ViolacaoResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        var errorResponse = new ErrorResponse();
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTitulo("Operação inválida.");
        errorResponse.setDetalhe("A requsição não respeita o schema.");

        var violacoes = ex.getBindingResult().getFieldErrors().stream()
                .map(ViolacaoResponse::new)
                .collect(Collectors.toList());
        errorResponse.setViolacoes(violacoes);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleHttpMessageConversionException(HttpMessageConversionException ex, HttpServletRequest request) {
        var errorResponse = new ErrorResponse();
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTitulo("Erro ao converter json.");
        errorResponse.setDetalhe(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        var errorResponse = new ErrorResponse();
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setTitulo("Não Encontrado.");
        errorResponse.setDetalhe(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegraExeception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleRegraExeception(RegraExeception ex, HttpServletRequest request) {
        var errorResponse = new ErrorResponse();
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTitulo("Operação inválida.");
        errorResponse.setDetalhe("A requsição não respeita o schema.");
        errorResponse.setDetalhe(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
