package com.facishare.open.connector.dao;

import com.facishare.open.connector.model.SalesOrderDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 检查数据库变化
 * Created by zhongcy on 2016/7/28.
 */
public interface DataBaseChangeDAO {

    /**
     * 查询新建销售订单
     * @return
     */
    @Select("select FInterID interId,FBillNo billNo,FContractOrderNo contractOrderNo,FUpdateTime updateTime,FStatus " +
            "status from  mid_database.dbo.mid_SEOrder where FStatus=0")
    List<SalesOrderDO> getSalesOrderList();

    /**
     * 重置销售订单状态
     */
    @Update("update mid_database.dbo.mid_SEOrder set  FStatus=1 where FInterID=#{interId}")
    void resetSalesOrder(@Param("interId") int interId);


}
