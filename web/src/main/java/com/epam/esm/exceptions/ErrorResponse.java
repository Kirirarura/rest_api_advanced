package com.epam.esm.exceptions;

/**
 * Class for accessing error codes and messages.
 */
public class ErrorResponse {

    private String errorCode;
    private StringBuilder errorMessage;

    public ErrorResponse(String errorCode, StringBuilder errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public StringBuilder getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(StringBuilder errorMessage) {
        this.errorMessage = errorMessage;
    }
}
