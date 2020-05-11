package com.pubinfo.memory.common.handler;

import com.pubinfo.memory.common.dto.ResponseReturn;
import com.pubinfo.memory.common.exceptions.BusinessProcessingException;
import com.pubinfo.memory.common.exceptions.LoginAuthorException;
import com.pubinfo.memory.common.exceptions.ParamException;
import com.pubinfo.memory.common.exceptions.RegisterException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseReturn handle(Exception e) {
        if (e instanceof AuthorizationException) {
            LOGGER.error("签名验证不通过 >>>>>> {}", e.getLocalizedMessage());
            return ResponseReturn.unAuthorized();
        } else if (e instanceof MissingServletRequestParameterException) {
            LOGGER.error("请求参数异常 >>>>>> 404 (灬ꈍ ꈍ灬) {}", e.getLocalizedMessage());
            return ResponseReturn.dataIsNUll();
        } else if (e instanceof LoginAuthorException) {
            LOGGER.error("登录异常 >>>>>> {}", e.getLocalizedMessage());
            return ResponseReturn.loginUnAuthorized();
        }else if (e instanceof RegisterException) {
            LOGGER.error("登录异常 >>>>>> {}", e.getLocalizedMessage());
            return ResponseReturn.loginUnAuthorized();
        } else if (e instanceof RuntimeException) {
            e.printStackTrace();
            LOGGER.error("系统异常 >>>>>> {}", e.getLocalizedMessage());
            return ResponseReturn.unknownError();
        } else {
            e.printStackTrace();
            LOGGER.error("系统异常 >>>>>> {}", e.getLocalizedMessage());
            return ResponseReturn.unknownError();
        }
    }


    @ExceptionHandler(value = ParamException.class)
    @ResponseBody
    public ResponseReturn handleParamException(ParamException e) {
        LOGGER.error("请求参数异常 >>>>>> {}", e.getLocalizedMessage());
        return ResponseReturn.paramNull(e.getErrorMessage());
    }

    @ExceptionHandler(value = BusinessProcessingException.class)
    @ResponseBody
    public ResponseReturn businessProcessingException(BusinessProcessingException e) {
        LOGGER.error("业务处理错误 >>>>>> {}", e.getLocalizedMessage());
        return ResponseReturn.businessProcessingError(e.getErrorMessage());
    }
}
