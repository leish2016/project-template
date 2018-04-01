package com.touzhijia.project.common;

/**
 * 公用常量类
 *
 * @author Administrator
 */
public class Constants {
    public static String success = "ok";

    /**
     * 回调状态枚举
     *
     * @author Administrator
     */
    public enum CallbackStatus {
        init(0, "初始化"), success(1, "成功"), delete(-1, "删除");

        CallbackStatus(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        Integer code;
        String name;


        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

    }
}
