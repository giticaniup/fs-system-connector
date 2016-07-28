package com.facishare.open;

import com.facishare.open.connector.utils.DESUtils;

/**
 * 单元测试类
 * Created by zhongcy on 2016/7/28.
 */
public class Test {
    @org.junit.Test
    public void test() {
        System.out.println(DESUtils.getEncryptString("sa"));
        System.out.println(DESUtils.getEncryptString("A_123456879"));
        System.out.println(DESUtils.getDecryptString("WEhw1XEVEV8="));
        System.out.println(DESUtils.getDecryptString("gbtR8000KJbGQc8aaEaShg=="));
    }
}
