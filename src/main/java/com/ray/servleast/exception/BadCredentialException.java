package com.ray.servleast.exception;

import javax.servlet.ServletException;

/**
 * Created by Ray on 2017/12/31.
 */
public class BadCredentialException extends ServletException {
    public BadCredentialException(String message) {
        super(message);
    }
}
