package com.shangzf.response;

import java.io.Serializable;

/**
 * 响应类型
 *
 * @author zefeng.shang@changhong.com
 * @version 0.0.1
 * @since 0.0.1
 */
public class ResponseDTO<T> implements Serializable {
    private static final long serialVersionUID = -6926884958905570395L;
    private Integer state;
    private String message;
    private T data;

    public ResponseDTO() {
    }

    public ResponseDTO(int state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseDTO<T> response(int state, String message) {
        return response(state, message, null);
    }

    public static <T> ResponseDTO<T> response(int state, String message, T data) {
        return new ResponseDTO<>(state, message, data);
    }

    public static <T> ResponseDTO<T> response(ResponseCode responseCode, T data) {
        return response(responseCode.getCode(), responseCode.getMessage(), data);
    }

    public static <T> ResponseDTO<T> success() {
        return success(null);
    }

    public static <T> ResponseDTO<T> success(T data) {
        return ResponseDTO.response(ResponseCode.SUCCESS, data);
    }

    public static <T> ResponseDTO<T> fail() {
        return fail(null);
    }

    public static <T> ResponseDTO<T> fail(T data) {
        return fail(ResponseCode.FAIL, data);
    }

    public static <T> ResponseDTO<T> fail(ResponseCode responseCode, T data) {
        return ResponseDTO.response(responseCode, data);
    }

    public Integer getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
