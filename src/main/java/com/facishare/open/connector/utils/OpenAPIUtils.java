package com.facishare.open.connector.utils;


import com.facishare.open.connector.exception.BaseException;
import com.facishare.open.connector.model.HttpResponseMessageVO;
import com.facishare.open.connector.model.args.AppTokenArg;
import com.facishare.open.connector.model.args.Arg;
import com.facishare.open.connector.model.args.CorpAccessTokenArg;
import com.facishare.open.connector.model.args.TextTemplateArg;
import com.facishare.open.connector.model.results.AppTokenResult;
import com.facishare.open.connector.model.results.BaseResult;
import com.facishare.open.connector.model.results.CorpAccessTokenResult;
import com.facishare.open.connector.model.results.Result;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 开放平台Api调用的工具类
 * 
 * @author huanghp
 * @date 2015年8月28日
 */
public class OpenAPIUtils {

    private static final Logger LOG = LoggerFactory.getLogger(OpenAPIUtils.class);

    /**
     * 环境为：https://open.fxiaoke.com
     */
    private static final String prefix = "https://open.fxiaoke.com";

    /**
     * 获取AppToken 实现
     * 
     * @param appTokenArg @see AppTokenArg
     * @return
     * @throws Exception
     */
    public static AppTokenResult getAppToken(AppTokenArg appTokenArg) {
        String url = prefix + "/cgi/appAccessToken/get";
        return doPost(url, appTokenArg, AppTokenResult.class);
    }

    /**
     * 获取corpAccessToken 实现
     * 
     * @param corpAccessTokenArg @see CorpAccessTokenArg
     * @return
     * @throws Exception
     */
    public static CorpAccessTokenResult getCorpToken(CorpAccessTokenArg corpAccessTokenArg) {
        String url = prefix + "/cgi/corpAccessToken/get";
        return doPost(url, corpAccessTokenArg, CorpAccessTokenResult.class);
    }

    /**
     * 发送复合消息
     */
    public static BaseResult sendTextTemplateMsg(TextTemplateArg textTemplateArg) {
        String url = prefix + "/cgi/message/send";
        return doPost(url, textTemplateArg, BaseResult.class);
    }



    private static <T extends BaseResult> T doPost(String url, Arg arg, Class<T> clazz) {
        T t = null;
        Result<String> result = doPost(url, arg);
        if (result.getCode() == 0) {
            t = new Gson().fromJson(result.getData(), clazz);
        }

        if (t != null) {
            return t;
        }

        try {
            t = clazz.newInstance();
            t.setErrorCode(result.getCode());
            t.setErrorMessage(result.getMsg());
        } catch (Exception e) {
            LOG.error("doPost error, details:", e);
        }
        return t;
    }

    private static Result<String> doPost(String url, Arg arg) {
        Result<String> result = new Result<String>();

        try {
            HttpResponseMessageVO resp = HttpTookit.sendPostByJson(url, new Gson().toJson(arg));

            if ("200".equals(resp.getHttpCode())) {
                result.setData(resp.getContent());
            } else {
                result.setCode(Constants.interfaceException.INTERFACE_EXCEPTION.code);
                result.setMsg(Constants.interfaceException.INTERFACE_EXCEPTION.msg.concat(",HTTP Status Code:").concat(
                        resp.getHttpCode()));
            }
        } catch (BaseException e) {
            LOG.error("doPost error, details:", e);
            result.setMsg(e.getMessage());
            result.setCode(e.getCode());
        }

        return result;
    }

}
