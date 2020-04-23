package cn.com.dhc.roomservice.exception;

import cn.com.dhc.roomservice.bean.vo.ResultBean;
import cn.com.dhc.roomservice.common.Constants;
import cn.com.dhc.roomservice.utils.ResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.AuthenticationException;

/**
 * @author xupingwei
 * @date 14:06 2018-04-04
 */
@ControllerAdvice
public class MyControllerAdvice {

    /**
     * 全局异常捕捉处理
     *
     * @param ex 全局异常
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultBean errorHandler(Exception ex) {
        ex.printStackTrace();
        if (ex instanceof NullPointerException) {
            return ResultUtils.failure("NullPointerException", Constants.FAILURE);
        }
        return ResultUtils.failure(ex.getMessage(), Constants.FAILURE);
    }

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public ResultBean errorHandler(RuntimeException ex) {
        ex.printStackTrace();
        return ResultUtils.failure(ex.getMessage(), Constants.FAILURE);
    }


    @ResponseBody
    @ExceptionHandler(value = AuthException.class)
    public ResultBean errorHandler(AuthException ex) {
        ex.printStackTrace();
        return ResultUtils.failure(ex.getMessage(), Constants.FAILURE);
    }

    /**
     * 处理自定义异常
     *
     * @param ex 自定义异常
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public ResultBean myErrorHandler(MyException ex) {
        ResultBean result = new ResultBean();
        result.setCode(Constants.FAILURE);
        result.setMsg(ex.getMsg());
        return result;
    }
}
