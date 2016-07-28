package com.facishare.open;

import com.facishare.open.connector.timertask.TimerTask;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 数据测试
 * Created by zhongcy on 2016/7/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class DataBaseTest {

    @Autowired
    private TimerTask timerTask;
    @org.junit.Test
    public void findSalesOrder(){
        timerTask.checkAndSendMsg();
    }
}
