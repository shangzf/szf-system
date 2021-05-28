package com.shangzf.common.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shangzf.common.exception.BaseException;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;

@Schema(name = "Rest请求的响应结果，不包含结果数据")
public class ResultResponse<T> implements Serializable {

    private static final long serialVersionUID = -7082945428094757341L;

    @Schema(name = "处理结果code", required = true)
    private int code;

    @Schema(name = "处理结果描述信息")
    private String message;

    @Schema(name = "请求结果生成时间戳")
    private final Instant time;

    @Schema(name = "处理结果数据信息")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private ResultResponse() {
        this.time = ZonedDateTime.now().toInstant();
    }

    private ResultResponse(T data) {
        this.data = data;
        this.time = ZonedDateTime.now().toInstant();
    }

    public static <T> ResultResponse<T> success(T data) {
        return builder(data).resultCode(CommonCodeEnum.SUCCESS).build();
    }

    public static ResultResponse<?> success() {
        return success(null);
    }

    public static <T> ResultResponse<T> fail(T data) {
        return builder(data).resultCode(CommonCodeEnum.FAIL).build();
    }

    public static ResultResponse<?> fail() {
        return builder(null).resultCode(CommonCodeEnum.FAIL).build();
    }

    public static ResultResponse<?> fail(BaseException baseException) {
        return fail(baseException, null);
    }

    public static <T> ResultResponse<T> fail(BaseException baseException, T data) {
        return fail(baseException.getResultCode(), data);
    }

    public static <T> ResultResponse<T> fail(IResultCode resultCode, T data) {
        return builder(data).resultCode(resultCode).build();
    }

    public static ResultResponse<?> fail(IResultCode resultCode) {
        return fail(resultCode, null);
    }

    public static <T> ResultResponse<T> error(T data) {
        return builder(data).resultCode(CommonCodeEnum.SERVER_ERROR).build();
    }

    public static <T> ResultResponseBuilder<T> builder(T data) {
        return new ResultResponseBuilder<>(data);
    }

    public static class ResultResponseBuilder<T> {
        private final ResultResponse<T> resultResponse;

        private ResultResponseBuilder(T data) {
            this.resultResponse = new ResultResponse<>(data);
        }

        public ResultResponseBuilder<T> code(int code) {
            resultResponse.code = code;
            return this;
        }

        public ResultResponseBuilder<T> message(String message) {
            resultResponse.message = message;
            return this;
        }

        public ResultResponseBuilder<T> resultCode(IResultCode resultCode) {
            resultResponse.code = resultCode.getCode();
            resultResponse.message = resultCode.getMessage();
            return this;
        }

        public ResultResponseBuilder<T> data(T data){
            resultResponse.data = data;
            return this;
        }

        public ResultResponse<T> build() {
            return resultResponse;
        }
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Instant getTime() {
        return time;
    }
}
