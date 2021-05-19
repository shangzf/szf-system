package com.shangzf.common.vo.response;

public class ResponseData implements IResponseData {

    private static final long serialVersionUID = 1319765298324285045L;
    private int code;
    private String message;

    private ResponseData() {
    }

    public static ResponseData success() {
        return builder().resultCode(CommonCodeEnum.SUCCESS).build();
    }

    public static ResponseData fail() {
        return builder().resultCode(CommonCodeEnum.FAIL).build();
    }

    public static ResponseData fail(IResultCode resultCode) {
        return builder().resultCode(resultCode).build();
    }

    public static ResponseData error() {
        return builder().resultCode(CommonCodeEnum.SERVER_ERROR).build();
    }

    public <T> ResultResponseData<T> convertToQueryResponseData(T data) {
        return ResultResponseData.builder(data).code(this.getCode()).message(this.message).build();
    }

    public static ResponseDataBuilder builder() {
        return new ResponseDataBuilder();
    }

    public static class ResponseDataBuilder {
        private final ResponseData responseData;

        private ResponseDataBuilder() {
            this.responseData = new ResponseData();
        }

        public ResponseDataBuilder code(int code) {
            responseData.code = code;
            return this;
        }

        public ResponseDataBuilder message(String message) {
            responseData.message = message;
            return this;
        }

        public ResponseDataBuilder resultCode(IResultCode resultCode) {
            responseData.code = resultCode.getCode();
            responseData.message = resultCode.getMessage();
            return this;
        }

        public ResponseData build() {
            return responseData;
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

}
