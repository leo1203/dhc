package cn.com.dhc.roomservice.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 后台校验工具类
 *
 * @author huleikai
 * @create 2019-05-07 11:19
 */
public class VerifyUtils {

    /**
     * 校验是不是合法的手机号
     *
     * @param phone 待校验的手机号字符串
     * @return
     */
    public static boolean isPhone(String phone) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(phone);
        b = m.matches();
        return b;

    }

    /**
     * 比较两个日期字符串大小
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareDate(String date1, String date2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() < dt2.getTime()) {
                return true;
            } else if (dt1.getTime() > dt2.getTime()) {
                return false;
            } else {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * 日期类型转换为字符串
     *
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }


    /**
     * 校验是不是合法的手机号
     *
     * @param phone 待校验的手机号字符串
     * @return
     */

    public static boolean isPhoneNum(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * 校验密码是不是由数字及字母组成，并且长度必须大于6
     *
     * @param password 待校验的密码字符串
     * @return
     */
    public static boolean isPassword(String password) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.print(isNumeric("1"));
        System.out.println(dateToStr(new Date()));
        System.out.println(compareDate("2019-05-09 16:24:27", "2019-05-08 16:24:27"));
    }
}
