package com.example.demo.Exceptions;

import com.example.demo.Response.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExcaptionHandlers {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> nfe(NotFoundException e){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setError(e.getMessage());
        return ResponseEntity.ok(responseDto);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> runTime(RuntimeException e){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setError(e.getMessage());
        return ResponseEntity.ok(responseDto);
    }
}
