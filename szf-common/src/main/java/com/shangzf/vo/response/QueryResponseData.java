package com.shangzf.vo.response;

public class QueryResponseData<T> implements IResponseData {

    private static final long serialVersionUID = -7082945428094757341L;
    private int code;
    private String message;
    private T data;

    private QueryResponseData() {
    }

    private QueryResponseData(T data) {
        this.data = data;
    }

    public static <T> QueryResponseData<T> success(T data) {
        return builder(data).resultCode(CommonCodeEnum.SUCCESS).build();
    }

    public static <T> QueryResponseData<T> fail(T data) {
        return builder(data).resultCode(CommonCodeEnum.FAIL).build();
    }

    public static <T> QueryResponseData<T> fail(IResultCode resultCode, T data) {
        return builder(data).resultCode(resultCode).build();
    }

    public static <T> QueryResponseData<T> error(T data) {
        return builder(data).resultCode(CommonCodeEnum.SERVER_ERROR).build();
    }

    public static <T> QueryResponseDataBuilder<T> builder(T data) {
        return new QueryResponseDataBuilder<>(data);
    }

    public ResponseData convertToResponseData() {
        return ResponseData.builder().code(this.code).message(this.message).build();
    }

    public static class QueryResponseDataBuilder<T> {
        private final QueryResponseData<T> queryResponseData;

        private QueryResponseDataBuilder(T data) {
            this.queryResponseData = new QueryResponseData<>(data);
        }

        public QueryResponseDataBuilder<T> code(int code) {
            queryResponseData.code = code;
            return this;
        }

        public QueryResponseDataBuilder<T> message(String message) {
            queryResponseData.message = message;
            return this;
        }

        public QueryResponseDataBuilder<T> resultCode(IResultCode resultCode) {
            queryResponseData.code = resultCode.getCode();
            queryResponseData.message = resultCode.getMessage();
            return this;
        }

        public QueryResponseData<T> build() {
            return queryResponseData;
        }
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }

    public T getData() {
        return data;
    }
}
