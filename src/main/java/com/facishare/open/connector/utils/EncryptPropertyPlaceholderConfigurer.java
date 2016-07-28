package com.facishare.open.connector.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 重写的placeholder类
 * Created by zhongcy on 2016/3/24.
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(EncryptPropertyPlaceholderConfigurer.class);

    private String[] encryptPropNames = {"database.username", "database.password"};

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {

        //如果在加密属性名单中发现该属性
        if (isEncryptProp(propertyName)) {
            String decryptValue = DESUtils.getDecryptString(propertyValue);
            logger.debug(propertyName+":"+decryptValue);
            return decryptValue;
        } else {
            return propertyValue;
        }

    }

    private boolean isEncryptProp(String propertyName) {
        for (String encryptName : encryptPropNames) {
            if (encryptName.equals(propertyName)) {
                return true;
            }
        }
        return false;
    }
}