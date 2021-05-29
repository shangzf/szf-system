package com.shangzf.common.exception;

import com.shangzf.common.vo.response.CommonCodeEnum;
import com.shangzf.common.vo.response.IResultCode;

public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 3915843869054357863L;

    private final IResultCode resultCode;

    public BaseException() {
        this.resultCode = CommonCodeEnum.SERVER_EXCEPTION;
    }

    public BaseException(IResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public BaseException(String message, IResultCode resultCode) {
        super(message);
        this.resultCode = resultCode;
    }

    public BaseException(String message, Throwable cause, IResultCode resultCode) {
        super(message, cause);
        this.resultCode = resultCode;
    }

    public IResultCode getResultCode() {
        return resultCode;
    }

}
