package cn.com.dhc.roomservice.utils;

public class RandomUtils {

    /**
     * 生成6位随机数
     *
     * @return f返回6位随机数字符串
     */
    public static String generateRandom() {
        return String.valueOf((Math.random() * 9 + 1) * 100000);
    }
}
