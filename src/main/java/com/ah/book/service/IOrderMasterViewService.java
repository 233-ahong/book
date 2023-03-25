package com.ah.book.service;

import java.util.List;
import com.ah.book.domain.OrderMasterView;

/**
 * 订单主Service接口
 *
 * @author 陈祥
 * @date 2022-12-28
 */
public interface IOrderMasterViewService
{
    /**
     * 查询订单主
     *
     * @param orderId 订单主主键
     * @return 订单主
     */
    public List<OrderMasterView> selectOrderMasterViewByUserId(Long orderId);

    /**
     * 查询订单主列表
     *
     * @param orderMasterView 订单主
     * @return 订单主集合
     */
    public List<OrderMasterView> selectOrderMasterViewList(OrderMasterView orderMasterView);

    /**
     * 新增订单主
     *
     * @param orderMasterView 订单主
     * @return 结果
     */
    public int insertOrderMasterView(OrderMasterView orderMasterView);

    /**
     * 修改订单主
     *
     * @param orderMasterView 订单主
     * @return 结果
     */
    public int updateOrderMasterView(OrderMasterView orderMasterView);

    /**
     * 批量删除订单主
     *
     * @param orderIds 需要删除的订单主主键集合
     * @return 结果
     */
    public int deleteOrderMasterViewByOrderIds(Integer[] orderIds);

    /**
     * 删除订单主信息
     *
     * @param orderId 订单主主键
     * @return 结果
     */
    public int deleteOrderMasterViewByOrderId(Integer orderId);
}
