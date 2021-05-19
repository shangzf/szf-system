package com.shangzf.vo.response;

import java.io.Serializable;

/**
 * 响应结果
 */
public interface IResponseData extends Serializable {

    int getCode();

    String getMessage();
}
