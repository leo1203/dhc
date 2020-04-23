package cn.com.dhc.roomservice.exception;


/**
 * @author xupingwei
 */
public class BusinessException extends RuntimeException {

    @Override
    public String getMessage() {
        return msg;
    }

    private String msg;

    public BusinessException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
