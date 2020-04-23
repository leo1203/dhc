//package cn.com.dhc.roomservice.exception;
//
//import cn.com.dhc.roomservice.bean.vo.ResultBean;
//import cn.com.dhc.roomservice.common.Constants;
//import org.apache.shiro.authc.AuthenticationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//@ControllerAdvice
//@ResponseBody
//public class CustomExceptionHandler {
//
//    @ExceptionHandler({CustomException.class,AuthException.class})
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResultBean customException(CustomException e) {
//        ResultBean bean = new ResultBean();
//        bean.setMsg(e.getMessage());
//        bean.setCode(Constants.FAILURE);
//        return bean;
//    }
//}
