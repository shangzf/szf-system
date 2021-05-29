package com.shangzf.common.web.handler;

import com.shangzf.common.exception.BaseException;
import com.shangzf.common.util.ContextUtil;
import com.shangzf.common.vo.constant.StringConstant;
import com.shangzf.common.vo.response.CommonCodeEnum;
import com.shangzf.common.vo.response.ResultResponse;
import org.springframework.beans.TypeMismatchException;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 类型不匹配
     */
    @ExceptionHandler(TypeMismatchException.class)
    public ResultResponse<String> methodArgumentTypeMismatchExceptionHandler(TypeMismatchException e) {
        Class<?> requiredType = e.getRequiredType();
        String trace = ContextUtil.getMessage(CommonCodeEnum.TYPE_MISMATCH.getCode(), e.getValue(), Objects
                .nonNull(requiredType) ? ClassUtils.getQualifiedName(requiredType) : StringConstant.EMPTY);
        return ResultResponse.failOfData(trace);
    }

    /**
     * 数据绑定异常
     */
    @ExceptionHandler(BindException.class)
    public ResultResponse<String> bindExceptionHandler(BindException exception) {
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        StringBuilder trace = new StringBuilder();
        for (ObjectError objectError : errorList) {
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                trace.append(fieldError.getField()).append(StringConstant.COLON).append(fieldError.getDefaultMessage())
                     .append(StringConstant.SEMICOLON);
            } else {
                if (Objects.nonNull(objectError.getArguments()) && objectError.getArguments().length == Integer
                        .parseInt(StringConstant.TWO)) {
                    String[] argument = (String[]) objectError.getArguments()[Integer.parseInt(StringConstant.ONE)];
                    trace.append(Arrays.toString(argument)).append(StringConstant.COLON)
                         .append(objectError.getDefaultMessage()).append(StringConstant.SEMICOLON);
                }
            }
        }
        return ResultResponse.failOfData(trace.toString());
    }

    @ExceptionHandler(BaseException.class)
    public ResultResponse<String> baseExceptionHandler(BaseException e) {
        return ResultResponse.failOfData(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultResponse<String> exceptionHandler(Exception e) {
        return ResultResponse.error(e.getMessage());
    }

}
