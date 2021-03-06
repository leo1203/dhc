package cn.com.dhc.epq.bean.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResultBean<T> {

    private String msg;
    private int code;
    private T data;

    public ResultBean() {
    }

    public ResultBean(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
    
    

    public ResultBean(String msg, int code, T data) {
		this.msg = msg;
		this.code = code;
		this.data = data;
	}

	public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
