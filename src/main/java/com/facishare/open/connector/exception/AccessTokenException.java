package com.facishare.open.connector.exception;

@SuppressWarnings("serial")
public class AccessTokenException extends BaseException {

    public AccessTokenException(int code, String msg) {
        super(code, msg);
    }

}
