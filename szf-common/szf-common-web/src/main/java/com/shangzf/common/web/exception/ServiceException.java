package com.shangzf.common.web.exception;

import com.shangzf.common.web.pojo.code.IResultCode;

/**
 * 业务异常
 */
public class ServiceException extends BaseException {
    private static final long serialVersionUID = -8055523836196157339L;

    public ServiceException(IResultCode resultCode) {
        super(resultCode);
    }
}
