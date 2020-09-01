package com.example.controller;

public class ExcelConstant {

    /**APP列表导出文件Title*/
    public static final String DEFAULT_EXPORT_SHEET = "橡木项目";

    /**App列表 导入表头*/
    public static final String[] APP_LIST = new String[]{"应用名称","应用代码","负责人"};

    /**APP列表导出文件名*/
    public static final String APP_EXPORT_FILE_NAME = "application_list.xls";

    /**App列表 导入表头*/
    public static final String[] SERVICE_LIST = new String[]{   "应用代码","服务名称", "服务代码","服务负责人",
                                                                "服务备注","上线时间","应用首页","登录回调地址","环境"};

    /**APP列表导出文件名*/
    public static final String SERVICE_EXPORT_FILE_NAME = "service_list.xls";

}
