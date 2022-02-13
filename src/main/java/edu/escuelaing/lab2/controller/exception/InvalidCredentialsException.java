package edu.escuelaing.lab2.controller.exception;


import edu.escuelaing.lab2.controller.auth.ErrorCodeEnum;
import edu.escuelaing.lab2.controller.auth.ServerErrorResponseDto;
import org.springframework.http.HttpStatus;

import javax.ws.rs.InternalServerErrorException;

public class InvalidCredentialsException extends InternalServerErrorException {

    public InvalidCredentialsException() {

        super(String.valueOf(new ServerErrorResponseDto("User not found", ErrorCodeEnum.USER_NOT_FOUND, HttpStatus.NOT_FOUND)));

    }
}
