package com.notas.exceptions.responses;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author UTP
 */
@ResponseStatus(HttpStatus.OK)
public class Ok extends RuntimeException {

    public Ok(String exception) {
        super(exception);
    }

}
