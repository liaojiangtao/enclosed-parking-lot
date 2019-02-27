package com.fangle.parking.exceptions;

public class ServiceException  extends RuntimeException{
    private static final long serialVersionUID = 399464173374670273L;

    public ServiceException(String msg) {
        super(msg);
    }
}
