package com.shangzf.common.exception;

import com.shangzf.common.pojo.vo.code.IResultCode;

/**
 * 业务异常
 */
public class ServiceException extends BaseException {
    private static final long serialVersionUID = -8055523836196157339L;

    public ServiceException(IResultCode resultCode) {
        super(resultCode);
    }
}
