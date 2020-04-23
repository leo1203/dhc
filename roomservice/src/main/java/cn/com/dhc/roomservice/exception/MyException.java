package cn.com.dhc.roomservice.exception;

/**
 * @author xupingwei
 * @date 14:09 2018-04-04
 */
public class MyException extends RuntimeException {

    private String msg;

    public MyException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
