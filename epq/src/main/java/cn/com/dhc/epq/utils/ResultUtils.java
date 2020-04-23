package cn.com.dhc.epq.utils;

import cn.com.dhc.epq.bean.vo.ResultBean;
import cn.com.dhc.epq.common.Constants;

@SuppressWarnings("rawtypes")
public class ResultUtils {

    
	@SuppressWarnings("unchecked")
	public static ResultBean success(Object obj) {
        ResultBean bean = new ResultBean("成功", Constants.SUCCESS);
        bean.setData(obj);
        return bean;
    }

    public static ResultBean success() {
        ResultBean bean = new ResultBean("成功", Constants.SUCCESS);
        return bean;
    }

    public static ResultBean failure(String msg, int code) {
        return new ResultBean(msg, code);
    }

    public static ResultBean failure(int code) {
        return new ResultBean(Constants.VALUES.get(code), code);
    }

    public static ResultBean failure(String msg) {
        return new ResultBean(msg, Constants.FAILURE);
    }
    
    public static ResultBean failure() {
        return new ResultBean("失败", Constants.FAILURE);
    }
}
