package com.douban.eggshell.exception;


import com.douban.eggshell.dto.Result;
import com.douban.eggshell.enums.UserEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/*系统的dao、service、controller出现都通过throws Exception向上抛出，最后由springmvc前端控制器交由异常处理器进行异常处理*/
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result defaultErrorHandler(  HttpServletRequest req, Exception e)  {
             log.error("有异常出现: {}",e.getMessage());
        return Result.build(UserEnums.UNKNOW_ERROR);
    }

}
