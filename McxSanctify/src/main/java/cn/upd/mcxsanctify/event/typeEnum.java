package cn.upd.mcxsanctify.event;

import org.jetbrains.annotations.Contract;

public enum typeEnum {
    HEAD,CHEST,LEG,FOOT,HELPER,MAINHAND;



    @Contract(pure = true)
    public static int valueOf(typeEnum value) {    //手写的从int到enum的转换函数
        switch (value) {
            case HEAD:
                return 11;
            case CHEST:
                return 20;
            case LEG:
                return 29;
            case FOOT:
                return 38;
            case HELPER:
                return 19;
            case MAINHAND:
                return 21;
            default:
                return 0;
        }
    }

}
