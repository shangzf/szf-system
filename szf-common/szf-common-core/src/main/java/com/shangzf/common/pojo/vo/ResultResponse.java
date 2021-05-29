package com.shangzf.common.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shangzf.common.exception.BaseException;
import com.shangzf.common.pojo.vo.code.CommonCodeEnum;
import com.shangzf.common.pojo.vo.code.IResultCode;
import com.shangzf.common.util.ContextUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;

@Schema(name = "Rest请求的响应结果")
public class ResultResponse<T> implements Serializable {

    private static final long serialVersionUID = -7082945428094757341L;

    @Schema(name = "处理结果code", required = true)
    private String code;

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

    public static <T> ResultResponse<T> successOfData(T data) {
        String message = ContextUtil.getMessage(CommonCodeEnum.SUCCESS.getCode());
        if (StringUtils.isBlank(message)) {
            message = CommonCodeEnum.SUCCESS.getMessage();
        }
        return builder(data).message(message).code(CommonCodeEnum.SUCCESS.getCode()).build();
    }

    public static ResultResponse<?> success() {
        return successOfData(null);
    }

    public static <T> ResultResponse<T> failOfData(T data) {
        return failOfData(CommonCodeEnum.FAIL, data);
    }

    public static <T> ResultResponse<T> failOfData(IResultCode resultCode, T data) {
        String message = ContextUtil.getMessage(resultCode.getCode());
        if (StringUtils.isBlank(message)) {
            message = resultCode.getMessage();
        }
        return builder(data).message(message).code(resultCode.getCode()).build();
    }

    public static ResultResponse<?> fail() {
        return fail(CommonCodeEnum.FAIL);
    }

    public static ResultResponse<?> fail(BaseException baseException, Object... args) {
        return fail(baseException.getResultCode(), args);
    }

    public static ResultResponse<?> fail(IResultCode resultCode, Object... args) {
        String message = ContextUtil.getMessage(resultCode.getCode(), args);
        if (StringUtils.isBlank(message)) {
            message = resultCode.getMessage();
        }
        return builder(null).message(message).code(resultCode.getCode()).build();
    }

    public static <T> ResultResponse<T> error(T data) {
        return failOfData(CommonCodeEnum.SERVER_EXCEPTION,data);
    }

    public static <T> ResultResponseBuilder<T> builder(T data) {
        return new ResultResponseBuilder<>(data);
    }

    public static class ResultResponseBuilder<T> {
        private final ResultResponse<T> resultResponse;

        private ResultResponseBuilder(T data) {
            this.resultResponse = new ResultResponse<>(data);
        }

        public ResultResponseBuilder<T> code(String code) {
            resultResponse.code = code;
            return this;
        }

        public ResultResponseBuilder<T> message(String message) {
            resultResponse.message = message;
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

    public String getCode() {
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
