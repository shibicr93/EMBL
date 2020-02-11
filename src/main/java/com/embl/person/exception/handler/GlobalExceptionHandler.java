package com.embl.person.exception.handler;

import com.embl.person.exception.ApplicationException;
import com.embl.person.exception.Error;
import com.embl.person.exception.ErrorConstants;
import com.embl.person.exception.ErrorPropertyHolder;
import com.embl.person.exception.Errors;
import com.embl.person.exception.GatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.PropertyResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import java.lang.annotation.Annotation;
import java.util.Set;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final PropertyResolver propertyResolver;

    @Autowired
    public GlobalExceptionHandler(ErrorPropertyHolder errorPropertyHolder) {
        this.propertyResolver = errorPropertyHolder.getPropertyResolver();
    }

    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity<APIErrorResponse> handleGenericApplicationError(
            ApplicationException exception,
            WebRequest request) {
        HttpStatus httpStatus = getHttpStatus(exception);
        APIErrorResponse apiErrorResponse = getErrorResponse(exception.getErrors());

        return new ResponseEntity<>(apiErrorResponse, httpStatus);
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<APIErrorResponse> handleValidationErrors(javax.validation.ConstraintViolationException ex) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        APIErrorResponse apiErrorResponse = getErrorResponse(ex.getConstraintViolations());

        return new ResponseEntity<>(apiErrorResponse, httpStatus);

    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<APIErrorResponse> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        APIErrorResponse apiErrorResponse = getErrorResponse(ex);

        return new ResponseEntity<>(apiErrorResponse, httpStatus);
    }

    @ExceptionHandler(value = {GatewayException.class})
    public ResponseEntity<APIErrorResponse> handleGenericGatewayError(
            GatewayException exception,
            WebRequest request
    ) {
        APIErrorResponse apiErrorResponse = getErrorResponse(exception);

        return new ResponseEntity<>(apiErrorResponse, SERVICE_UNAVAILABLE);
    }

    private APIErrorResponse getErrorResponse(GatewayException exception) {
        APIErrorResponse apiErrorResponse = new APIErrorResponse();
        APIError apiError = new APIError(exception.getErrorKey(), getProperty(exception.getErrorKey()));

        apiErrorResponse.add(apiError);
        return apiErrorResponse;
    }

    @ExceptionHandler(value = {RuntimeException.class, Exception.class})
    public ResponseEntity<APIErrorResponse> handleGenericRuntimeError(
            Exception exception,
            WebRequest webRequest
    ) {

        Errors errors = new Errors();
        errors.add(new Error(ErrorConstants.INTERNAL_SERVER_ERROR));
        APIErrorResponse apiErrorResponse = getErrorResponse(errors);
        return new ResponseEntity<>(apiErrorResponse, getHttpStatus(exception));
    }

    protected HttpStatus getHttpStatus(Exception e) {
        HttpStatus httpStatus;
        Annotation statusAnnotation = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);

        if (statusAnnotation != null) {
            httpStatus = (HttpStatus) AnnotationUtils.getValue(statusAnnotation);
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return httpStatus;
    }

    private APIErrorResponse getErrorResponse(Errors errors) {
        APIErrorResponse apiErrorResponse = new APIErrorResponse();
        for (Error error : errors) {
            APIError apiError = new APIError(error.getKey(), error.getMessage(propertyResolver));
            apiErrorResponse.add(apiError);
        }
        return apiErrorResponse;
    }

    private APIErrorResponse getErrorResponse(MissingRequestHeaderException ex) {
        APIErrorResponse apiErrorResponse = new APIErrorResponse();
        APIError apiError = new APIError("MISSING_HEADER", ex.getMessage());
        apiErrorResponse.add(apiError);

        return apiErrorResponse;
    }

    private APIErrorResponse getErrorResponse(Set<ConstraintViolation<?>> constraintViolations) {
        APIErrorResponse apiErrorResponse = new APIErrorResponse();
        constraintViolations.stream().forEach(constraintViolation -> {
            String propertyPath = constraintViolation.getPropertyPath().toString();
            String property = propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
            apiErrorResponse.add(new APIError(String.format("INVALID VALUE PASSED FOR %s", property),
                    constraintViolation.getMessage()));
        });


        return apiErrorResponse;
    }

    protected String getProperty(String errorKey) {
        return propertyResolver.getProperty(errorKey);
    }
}
