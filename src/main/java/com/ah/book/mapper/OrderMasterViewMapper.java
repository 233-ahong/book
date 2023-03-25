package com.ah.book.mapper;

import java.util.List;
import com.ah.book.domain.OrderMasterView;
import com.ah.book.domain.OrderDetail;

/**
 * 订单主Mapper接口
 *
 * @author 陈祥
 * @date 2022-12-28
 */
public interface OrderMasterViewMapper
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
     * 删除订单主
     *
     * @param orderId 订单主主键
     * @return 结果
     */
    public int deleteOrderMasterViewByOrderId(Integer orderId);

    /**
     * 批量删除订单主
     *
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderMasterViewByOrderIds(Integer[] orderIds);

    /**
     * 批量删除订单详情
     *
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderDetailByOrderIds(Integer[] orderIds);

    /**
     * 批量新增订单详情
     *
     * @param orderDetailList 订单详情列表
     * @return 结果
     */
    public int batchOrderDetail(OrderDetail orderDetailList);


    /**
     * 通过订单主主键删除订单详情信息
     *
     * @param orderId 订单主ID
     * @return 结果
     */
    public int deleteOrderDetailByOrderId(Integer orderId);
}
