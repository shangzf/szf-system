package com.shangzf.common.vo.response;

public class ResultResponseData<T> implements IResponseData {

    private static final long serialVersionUID = -7082945428094757341L;
    private int code;
    private String message;
    private T data;

    private ResultResponseData() {
    }

    private ResultResponseData(T data) {
        this.data = data;
    }

    public static <T> ResultResponseData<T> success(T data) {
        return builder(data).resultCode(CommonCodeEnum.SUCCESS).build();
    }

    public static <T> ResultResponseData<T> fail(T data) {
        return builder(data).resultCode(CommonCodeEnum.FAIL).build();
    }

    public static <T> ResultResponseData<T> fail(IResultCode resultCode, T data) {
        return builder(data).resultCode(resultCode).build();
    }

    public static <T> ResultResponseData<T> error(T data) {
        return builder(data).resultCode(CommonCodeEnum.SERVER_ERROR).build();
    }

    public static <T> ResultResponseDataBuilder<T> builder(T data) {
        return new ResultResponseDataBuilder<>(data);
    }

    public ResponseData convertToResponseData() {
        return ResponseData.builder().code(this.code).message(this.message).build();
    }

    public static class ResultResponseDataBuilder<T> {
        private final ResultResponseData<T> resultResponseData;

        private ResultResponseDataBuilder(T data) {
            this.resultResponseData = new ResultResponseData<>(data);
        }

        public ResultResponseDataBuilder<T> code(int code) {
            resultResponseData.code = code;
            return this;
        }

        public ResultResponseDataBuilder<T> message(String message) {
            resultResponseData.message = message;
            return this;
        }

        public ResultResponseDataBuilder<T> resultCode(IResultCode resultCode) {
            resultResponseData.code = resultCode.getCode();
            resultResponseData.message = resultCode.getMessage();
            return this;
        }

        public ResultResponseData<T> build() {
            return resultResponseData;
        }
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
