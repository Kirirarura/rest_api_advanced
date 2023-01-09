package com.epam.esm.exceptions;

import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.InvalidEntityException;
import com.epam.esm.exception.InvalidIdException;
import com.epam.esm.exception.NoSuchEntityException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


import static org.springframework.http.HttpStatus.*;

/**
 * An Exception Handler, responsible for displaying error code and messages.
 */
@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(DaoException.class)
    public final ResponseEntity<Object> handleDaoExceptions(DaoException ex) {
        String code = ex.getMessage();
        StringBuilder details = new StringBuilder(Translator.toLocale(ex.getLocalizedMessage()));
        ErrorResponse errorResponse = new ErrorResponse(code, details);
        return new ResponseEntity<>(errorResponse, NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public final ResponseEntity<Object> handleDuplicateEntityException(DuplicateEntityException ex){
        StringBuilder details = new StringBuilder(Translator.toLocale(ex.getLocalizedMessage()));
        String code = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(code, details);
        return new ResponseEntity<>(errorResponse, CONFLICT);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public final ResponseEntity<Object> handleInvalidEntityException(InvalidEntityException ex){
        StringBuilder details = new StringBuilder(Translator.toLocale(ex.getLocalizedMessage()));
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST.toString(), details);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(InvalidIdException.class)
    public final ResponseEntity<Object> handleInvalidIdException(InvalidIdException ex){
        StringBuilder details = new StringBuilder(Translator.toLocale(ex.getLocalizedMessage()));
        if (ex.getId() != null){
            details.append(", id: ").append(ex.getId());
        }
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST.toString(), details);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }


    @ExceptionHandler(NoSuchEntityException.class)
    public final ResponseEntity<Object> handleNoSuchEntityException(NoSuchEntityException ex){
        StringBuilder details = new StringBuilder(Translator.toLocale(ex.getLocalizedMessage()));
        if (ex.getId() != null){
            details.append(", id: ").append(ex.getId());
        }
        String code = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(code, details);
        return new ResponseEntity<>(errorResponse, NOT_FOUND);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public final ResponseEntity<Object> handleBadRequestException() {
        StringBuilder details = new StringBuilder(Translator.toLocale("exception.noHandler"));
        ErrorResponse errorResponse = new ErrorResponse(NOT_FOUND.toString(), details);
        return new ResponseEntity<>(errorResponse, NOT_FOUND);
    }
}
