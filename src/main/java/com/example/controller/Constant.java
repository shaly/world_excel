package com.example.controller;


/**
 * @Author: shenjian
 * @Desription:
 * @Date: Created in 上午9:08 2017/12/1
 * @Modified By:
 */
public class Constant {
    public static final String DEFAULT_VERSION="0.0.0";
    public static final String TOKEN="token";
    public static final String EMPLOYEE_NO="employeeNo";
    public static final String ASC_CODE="ascCode";
    /** 默认日志打印Logger,配置见logback logger节点 */
    public static final String LOGGER = "oak";
    /** 有效 */
    public static final String VALID = "10011001";
    /** 无效 */
    public static final String INVALID = "10011002";
    /** 审核中 */
    public static final String UNAUDITED = "10021001";
    /** 审核通过 */
    public static final String APPROVED = "10021002";
    /** 审核未通过 */
    public static final String NOT_APPROVED = "10021003";
    /** -------------------------------路由类型---------------------------------*/
    /** 有效账号 */
    public static final String REDIRECT_VALID = "10181001";
    /** 无效账号 */
    public static final String REDIRECT_INVALID = "10181002";
    /** 待审批账号 */
    public static final String REDIRECT_UNAUDITED = "10181003";
    /** 无账号 */
    public static final String REDIRECT_NOT_ACCOUNT = "10181004";
    /** APP */
    public static final String REDIRECT_APP_INDEX = "10181005";
    /** -------------------------------路由类型---------------------------------*/
    public static final String LAY_TABLE_SUCCESS = "1";
    public static final String LAY_TABLE_FAIL = "0";

    public static final Integer IS_DEL = 1;
    public static final Integer NOT_DEL = 0;
    /** -------------------------------员工类型---------------------------------*/
    public static final String SMCV_EMP = "20011001";
    public static final String SUPPLIER_EMP = "20011002";

}
