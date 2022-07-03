package com.cqut.store.service.ex;

public class FailToAddProductException extends ServiceException{
    public FailToAddProductException() {
        super();
    }

    public FailToAddProductException(String message) {
        super(message);
    }

    public FailToAddProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailToAddProductException(Throwable cause) {
        super(cause);
    }

    protected FailToAddProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
