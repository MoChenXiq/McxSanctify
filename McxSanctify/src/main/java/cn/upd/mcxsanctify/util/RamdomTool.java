package cn.upd.mcxsanctify.util;

import java.util.UUID;

/**
 * 获取随机对象工具类
 */
public class RamdomTool {

    /** 随机获取UUID（常用于唯一识别，如ID生成）
     //在一台机器上生成的数字
     //根据当前时间和电脑网卡，生成一段字符，所以生成的都不一样
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /** 获取x内的随机一位数（包含x） */
    public static int getInt(int x) {
        if (x<=0) {
            return 0;
        }
        return (int)(Math.random() * (x+1));
    }

    /** 获取x到y内的随机一位数（包含x,y） */
    public static int getInt(int x, int y) {
        if (x<=0 || y <= 0 || x > y) {
            return 0;
        }
        return (int)(Math.random() * (y - x + 1)) + x;
    }

    /** 随机获取x位字符串（只包含小写字母，常用于区分不同事物）*/
    public static String getLetterString(int x) {
        if (x <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < x; i++) {
            sb.append((char) (int) (Math.random() * 26 + 97));
        }
        return sb.toString();
    }

    /** 随机获取x位字符串（只包含数字，常用于验证码）*/
    public static String getNumberString(int x) {
        if (x <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < x; i++) {
            sb.append((int) (Math.random() * 10));
        }
        return sb.toString();
    }

}
