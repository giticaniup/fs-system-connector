package com.facishare.open.connector.manager.impl;

import com.facishare.open.connector.exception.AccessTokenException;
import com.facishare.open.connector.manager.AccessTokenManager;
import com.facishare.open.connector.manager.MessageManager;
import com.facishare.open.connector.model.CorpAccessToken;
import com.facishare.open.connector.model.args.TextTemplateArg;
import com.facishare.open.connector.model.results.BaseResult;
import com.facishare.open.connector.utils.OpenAPIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageManager")
public class MessageManagerImpl implements MessageManager {

    @Autowired
    private AccessTokenManager accessTokenManager;

    @Override
    public BaseResult sendTextTemplateMsg(TextTemplateArg textTemplateArg) throws AccessTokenException {
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        textTemplateArg.setCorpAccessToken(token.getCorpAccessToken());
        textTemplateArg.setCorpId(token.getCorpId());
        return OpenAPIUtils.sendTextTemplateMsg(textTemplateArg);
    }
}
