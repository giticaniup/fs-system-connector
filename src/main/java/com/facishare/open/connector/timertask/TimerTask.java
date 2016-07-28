package com.facishare.open.connector.timertask;

import com.facishare.open.connector.dao.DataBaseChangeDAO;
import com.facishare.open.connector.exception.AccessTokenException;
import com.facishare.open.connector.manager.MessageManager;
import com.facishare.open.connector.model.SalesOrderDO;
import com.facishare.open.connector.model.args.TextTemplateArg;
import com.facishare.open.connector.utils.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时任务
 * 扫描中间表数据，用于发送消息到纷享
 * Created by zhongcy on 2016/7/28.
 */
@Component
public class TimerTask {
    private static final Logger logger = LoggerFactory.getLogger(TimerTask.class);

    @Autowired
    private DataBaseChangeDAO dataBaseChangeDAO;

    @Autowired
    private MessageManager messageManager;

    @Scheduled(cron = "0/5 * * * * ?")//每天每隔半小时执行一次
    public void checkAndSendMsg() {
        logger.info("checkAndSendMsg task is running...,time={}",new Date());
        checkSalesOrder();
    }

    /**
     * 检查销售订单增加、审批状态
     */
    @Transactional
    private void checkSalesOrder() throws AccessTokenException {
        List<SalesOrderDO> salesOrderDOList = dataBaseChangeDAO.getSalesOrderList();

        salesOrderDOList.forEach(salesOrderDO -> {
            List<String> toUserList = new ArrayList<>();
            toUserList.add("FSUID_7E2D929B116415DD9319EC09950EEA76");//测试，发给钟乘永
            TextTemplateArg textTemplateArg = new TextTemplateArg();

            //标题
            TextTemplateArg.ContentWarp title = textTemplateArg.new ContentWarp();
            title.setContent("销售订单");
            title.setTime(DateTimeUtil.parseTimeStampToDate(System.currentTimeMillis()));
            title.setColor("#990033");

            //内容
            List<TextTemplateArg.LabelWarp> contentWarpList = new ArrayList<>();
            TextTemplateArg.LabelWarp labelWarp = textTemplateArg.new LabelWarp();

            labelWarp.setLabel("订单号");
            labelWarp.setValue(salesOrderDO.getBillNo());
            contentWarpList.add(labelWarp);

            labelWarp.setLabel("合同订单号");
            labelWarp.setValue(salesOrderDO.getContractOrderNo());
            contentWarpList.add(labelWarp);

            //链接按钮
            TextTemplateArg.ButtonWarp buttonWarp = textTemplateArg.new ButtonWarp();
            buttonWarp.setTitle("点击查看详情");
            buttonWarp.setUrl("www.fxiaoke.com");
            buttonWarp.setColor("#660099");

            TextTemplateArg.TextTemplate textTemplate = textTemplateArg.new TextTemplate();
            textTemplate.setTitle(title);
            textTemplate.setInfos(contentWarpList);
            textTemplate.setButton(buttonWarp);

            textTemplateArg.setToUser(toUserList);
            textTemplateArg.setTextTemplate(textTemplate);
            textTemplateArg.setMsgType("text_template");
            messageManager.sendTextTemplateMsg(textTemplateArg);
            dataBaseChangeDAO.resetSalesOrder(salesOrderDO.getInterId());
        });
    }
}