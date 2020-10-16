package com.hpit.hotnews.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Constants {
	public static final String WEBSERVICE_WEBXML_ID = "d5b694b60afd4dbbac5ba9c334021bad";
	public static final String AES_PWD = "123456789";
	
	public static final String APPID = "wx34b9fd040839a4c7";
	public static final String APPSECRET = "23e22ecd64d04d5d47db8d87ac5e6287";
	
	/**微信请求地址 */
    //获取code 接口地址 Get
    public final static String WEB_CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    //微信回调地址(获取code)
    public final static String SCOPE = "snsapi_userinfo";
    public final static String CALLBACK_DOMAIN = "http://5b1e9866.ngrok.io";
    public final static String REDIRECT_URI = CALLBACK_DOMAIN + "/hotnews/user/wechatLogin?position=POSITION";
	/**
     * 拼接获取code的:接口地址
     * @return
     */
    public static String getCodeURL(String uuid,String position) {
    	String redirectUrl = REDIRECT_URI.replace("POSITION",position);
        String codeUrl = WEB_CODE_URL.replace("APPID",APPID).replace("SCOPE",SCOPE).replace("STATE",uuid);
        try {
			codeUrl = codeUrl.replace("REDIRECT_URI",URLEncoder.encode(redirectUrl,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return codeUrl;
    }
    
    public final static String WEB_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    public static String getTokenURL(String code) {
        return WEB_TOKEN_URL.replace("APPID",APPID).replace("SECRET",APPSECRET).replace("CODE",code);
    }
    
    public final static String WEB_USER_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    public static String getUserURL(String token,String openId) {
        return WEB_USER_URL.replace("ACCESS_TOKEN",token).replace("OPENID",openId);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
