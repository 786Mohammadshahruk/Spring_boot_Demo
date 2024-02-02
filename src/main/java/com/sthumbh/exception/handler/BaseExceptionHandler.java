package com.sthumbh.exception.handler;

import com.sthumbh.model.CustomResponseModel;
import com.sthumbh.model.MetaDate;
import com.sthumbh.model.ResourceData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponseModel> handleRuntimeException(Exception exception, WebRequest webRequest) {
        MetaDate metaDate = MetaDate.builder().code("400").status("BAD REQUEST").message("name should not be null or blank").version("1.0").build();
        ResourceData resourceData = ResourceData.builder().data(null).build();
        CustomResponseModel customResponseModel = CustomResponseModel.builder().metaDate(metaDate).resourceData(resourceData).build();
        return new ResponseEntity<>(customResponseModel, HttpStatus.BAD_REQUEST);
    }

}
