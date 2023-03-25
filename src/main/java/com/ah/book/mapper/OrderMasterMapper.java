package com.ah.book.mapper;

import java.util.List;
import com.ah.book.domain.OrderMaster;

/**
 * 订单主Mapper接口
 * 
 * @author 陈祥
 * @date 2022-12-11
 */
public interface OrderMasterMapper 
{
    /**
     * 查询订单主
     * 
     * @param orderId 订单主主键
     * @return 订单主
     */
    public OrderMaster selectOrderMasterByOrderId(Integer orderId);

    /**
     * 查询订单主列表
     * 
     * @param orderMaster 订单主
     * @return 订单主集合
     */
    public List<OrderMaster> selectOrderMasterList(OrderMaster orderMaster);

    /**
     * 新增订单主
     * 
     * @param orderMaster 订单主
     * @return 结果
     */
    public int insertOrderMaster(OrderMaster orderMaster);

    /**
     * 修改订单主
     * 
     * @param orderMaster 订单主
     * @return 结果
     */
    public int updateOrderMaster(OrderMaster orderMaster);

    /**
     * 删除订单主
     * 
     * @param orderId 订单主主键
     * @return 结果
     */
    public int deleteOrderMasterByOrderId(Integer orderId);

    /**
     * 批量删除订单主
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderMasterByOrderIds(Integer[] orderIds);
}
