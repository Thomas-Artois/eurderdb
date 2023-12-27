package com.switchfully.eurderdb;

import com.switchfully.eurderdb.admin.domain.Admin;
import com.switchfully.eurderdb.exceptions.AdminNotFoundException;
import com.switchfully.eurderdb.exceptions.AdminPasswordIncorrectException;
import com.switchfully.eurderdb.exceptions.CustomerDoesntExistException;
import com.switchfully.eurderdb.exceptions.CustomerPasswordIncorrectException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AdminPasswordIncorrectException.class)
    private void adminPasswordIncorrectException(AdminPasswordIncorrectException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(AdminNotFoundException.class)
    private void adminNotFoundException(AdminNotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(CustomerDoesntExistException.class)
    private void customerDoesntExistException(CustomerDoesntExistException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(CustomerPasswordIncorrectException.class)
    private void customerPasswordIncorrectException(CustomerPasswordIncorrectException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }


}
