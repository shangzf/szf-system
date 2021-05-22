package com.shangzf.common.vo.response;

public class ResultResponse implements IResultResponse {

    private static final long serialVersionUID = 1319765298324285045L;
    private int code;
    private String message;

    private ResultResponse() {
    }

    public static ResultResponse success() {
        return builder().resultCode(CommonCodeEnum.SUCCESS).build();
    }

    public static ResultResponse fail() {
        return builder().resultCode(CommonCodeEnum.FAIL).build();
    }

    public static ResultResponse fail(IResultCode resultCode) {
        return builder().resultCode(resultCode).build();
    }

    public static ResultResponse error() {
        return builder().resultCode(CommonCodeEnum.SERVER_ERROR).build();
    }

    public <T> ResultResponseData<T> convertToQueryResponseData(T data) {
        return ResultResponseData.builder(data).code(this.getCode()).message(this.message).build();
    }

    public static ResultResponseBuilder builder() {
        return new ResultResponseBuilder();
    }

    public static class ResultResponseBuilder {
        private final ResultResponse resultResponse;

        private ResultResponseBuilder() {
            this.resultResponse = new ResultResponse();
        }

        public ResultResponseBuilder code(int code) {
            resultResponse.code = code;
            return this;
        }

        public ResultResponseBuilder message(String message) {
            resultResponse.message = message;
            return this;
        }

        public ResultResponseBuilder resultCode(IResultCode resultCode) {
            resultResponse.code = resultCode.getCode();
            resultResponse.message = resultCode.getMessage();
            return this;
        }

        public ResultResponse build() {
            return resultResponse;
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
