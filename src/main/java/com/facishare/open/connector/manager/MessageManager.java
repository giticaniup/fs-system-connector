package com.facishare.open.connector.manager;


import com.facishare.open.connector.exception.AccessTokenException;
import com.facishare.open.connector.model.args.TextTemplateArg;
import com.facishare.open.connector.model.results.BaseResult;

/**
 * 消息管理的接口
 *
 * @author huanghp
 * @date 2015年8月28日
 */
public interface MessageManager {
    /**
     * 发送复合消息
     */
    BaseResult sendTextTemplateMsg(TextTemplateArg textTemplateArg) throws AccessTokenException;
}
