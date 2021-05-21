package com.shangzf.common.vo.response;

import java.io.Serializable;

/**
 * 响应结果
 */
public interface IResultResponse extends Serializable {

    int getCode();

    String getMessage();
}
