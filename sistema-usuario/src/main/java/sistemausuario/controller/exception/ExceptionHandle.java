package sistemausuario.controller.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import sistemausuario.response.common.BaseReturn;

@ControllerAdvice
public class ExceptionHandle {

	
    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<Object> handleConstraintViolation(BaseException exception, WebRequest request) {


        BaseReturn<Object> response = new BaseReturn<Object>();
        response.success = false;
        response.description = exception.getMessage();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
