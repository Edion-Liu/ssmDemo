package com.eliteams.quick4j.core.util;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public class CUSTOM_UTIL {
    //默认
    public static final String DEFAULT_ICON="http://oss-cn-shenzhen.aliyuncs.com/woyouzhijia/1_14539517987266";
    //状态异常 值
    public static final String STATUS_NG="NG";
    public static final String STATUS_OK="OK";
    public static final String STATUS_UNAUTH="UNAUTH";  //登录过期
    public static final String PASSWORD_NULL="用户密码不能为空！";  //用户名密码不能为空!
    public static final String PASSWORD_ERROR="用户密码错误！";//PASSWORD_ERROR
    public static final String USER_NULL="用户名不存在！";//用户名不存在!
    public static final String USER_ILLEGAL="用户名不合法！";//用户名不存在!
    public static final String USER_NAME_EXIST="用户名已经存在！";
    public static final String PHONE_EXIST="手机号已经注册过了！";
    public static final String GET_DATDFAIL="请求参数错误！";
    public static final String JOIN_ACTIVITYMAX="活动名额已满！";
    public static final String JOIN_CLOSE="活动报名已经关闭！";
    public static final String WALLET_ERROR_TYPE="未知支出类型！";
    public static final String WALLET_NOTENOUGH="账号金额不足！";
    public static final String APPVESION="当前版本为最新版本！";
    public static final String JOINED="已报名";
    public static final String TOKEN_ERROR="令牌错误！";
    public static final String CAMPSITE_DESCRIPTION_ERROR="描述不能少于10个字。";
    public static final String CAMPSITE_FINDED="此山头已被占，亲请继续努力。";
    public static final String USER_NO_EXIST="用户不存在";
    public static final String UPIMAGE_ERROR="上传图片失败。";
    public static final String LICENSE_AUTH_STATUS="用户商业认证默认值错误。";
    public static final String WOWO_NO_EXIST="窝窝不存在！";
    public static final String RESORT_NO_EXIST="营地不存在！";
    public static final String UPIMAGE_FAIL="图片上传失败！";

    //状态 成功
    public static final  String DELECT_OK="删除成功！";
    public static final  String REPORT_OK="举报成功！";



    //状态
    public static final String BUSINESS_AUDIT="未审核。";
    public static final String BUSINESS_AUDITING="在审核当中。";
    public static final String BUSINESS_AUDITED="已审核。";
    public static final String BUSINESS_AUDIT_FAIL="审核未成功。";


    //状态键
    public static final String STATUS_KEY="status";
    public static final String ERROR_MSG_KEY="error_msg";
    public static final String MSG_KEY="msg";
    public static final String PAGEINFO="pageinfo";
    public static final String SUCCESS_MSG_KEY="success_msg";


    //粉丝管理
    //关注状态
    public static final String FANS_MANAGE="follow_status";
    //不互相关注
    public static final String follow_status_key_none="0";
    //当前user_id 关注你
    public static final String follow_status_key_one="1";
    //你关注当前user_id
    public static final String follow_status_key_oneIOC="2";
    //互相关注
    public static final String follow_status_key_two="3";


    //图片上传标志
    //商户认证
    public static final String BUSINESS_AUTH="auth";

    //加分  设置值
    public static final int ADD_POINTS_INVITEFRIEND=200;   //邀请好友添加积分
    public static final String SETING_RANGE="200";  //设置默认搜索窝窝范围200 km 内
    public static final String SETING_MAX_WOWO="50000";  //设置默认搜索窝窝 最大数目  目前未用到


    //OSS
    public static final String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
    // accessKey请登录https://ak-console.aliyun.com/#/查看
    public static final String accessKeyId = "m6viYswL9lPXhujc";
    public static final String accessKeySecret = "YFT3lwi8jcHN3NOPsZ15ZybEZIpXRh";
    public static final String BucketName= "woyouzhijia";
    public static final String ImageBaseURL= "http://woyouzhijia.oss-cn-shenzhen.aliyuncs.com/";


    //微信支付
    public static final String WECAT_PAY_NOTIFICATION_FAIL="<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[微信支付异步通知异常，请求返回的状态码FAIL]]></return_msg></xml>";
    public static final String WECAT_PAY_SUCCESS="<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    public static final String WECAT_PAY_FAIL_START="<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[";
    public static final String WECAT_PAY_FAIL_END="]]></return_msg></xml>";
}
