package com.sthumbh.exception.handler;

import com.sthumbh.model.CustomResponseModel;
import com.sthumbh.model.MetaDate;
import com.sthumbh.model.ResourceData;
import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    //For Request validation
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String message = "";
        for(ObjectError objectError :ex.getBindingResult().getAllErrors()){
            message = message.concat(objectError.getDefaultMessage());
        }
        MetaDate metaDate = MetaDate.builder().code("400")
                .status("BAD Request")
                .message(message)
                .version("1.0").build();
        ResourceData resourceData = ResourceData.builder().data(null).build();
        CustomResponseModel customResponseModel = CustomResponseModel.builder().metaDate(metaDate).resourceData(resourceData).build();
        return new ResponseEntity<>(customResponseModel, HttpStatus.BAD_REQUEST);
    }


    // For Runtime Exception
    @ExceptionHandler(BloodDetailNotFoundException.class)
    public ResponseEntity<CustomResponseModel> handleBloodDetailNotFoundException(BloodDetailNotFoundException exception, WebRequest webRequest) {
        MetaDate metaDate = MetaDate.builder().code("500")
                .status("Internal Server Error")
                .message(exception.getMessage())
                .version("1.0").build();
        ResourceData resourceData = ResourceData.builder().data(null).build();
        CustomResponseModel customResponseModel = CustomResponseModel.builder().metaDate(metaDate).resourceData(resourceData).build();
        return new ResponseEntity<>(customResponseModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponseModel> handleBloodDetailNotFoundException(Exception exception, WebRequest webRequest) {
        MetaDate metaDate = MetaDate.builder().code("400")
                .status("BAD REQUEST")
                .message(exception.getMessage())
                .version("1.0").build();
        ResourceData resourceData = ResourceData.builder().data(null).build();
        CustomResponseModel customResponseModel = CustomResponseModel.builder().metaDate(metaDate).resourceData(resourceData).build();
        return new ResponseEntity<>(customResponseModel, HttpStatus.BAD_REQUEST);
    }

}
