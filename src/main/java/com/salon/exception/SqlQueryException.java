package com.salon.exception;

public class SqlQueryException extends RuntimeException {

    public SqlQueryException(String exception){
        super(exception);
    }
}
