package com.facishare.open.connector.model;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * 销售订单实体
 * Created by zhongcy on 2016/7/28.
 */
public class SalesOrderDO implements Serializable{
    private static final long serialVersionUID = 8490022957668456903L;

    private int interId;

    private String billNo;

    private String contractOrderNo;

    private Date updateTime;

    private int status;

    public int getInterId() {
        return interId;
    }

    public void setInterId(int interId) {
        this.interId = interId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getContractOrderNo() {
        return contractOrderNo;
    }

    public void setContractOrderNo(String contractOrderNo) {
        this.contractOrderNo = contractOrderNo;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("interId", interId)
                .add("billNo", billNo)
                .add("contractOrderNo", contractOrderNo)
                .add("updateTime", updateTime)
                .add("status", status)
                .toString();
    }
}
