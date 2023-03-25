package com.ah.book.service.impl;

import java.util.List;

import com.ah.book.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ah.book.mapper.OrderMasterMapper;
import com.ah.book.domain.OrderMaster;
import com.ah.book.service.IOrderMasterService;

import javax.annotation.Resource;

/**
 * 订单主Service业务层处理
 *
 * @author 陈祥
 * @date 2022-12-11
 */
@Service
public class OrderMasterServiceImpl implements IOrderMasterService
{
    @Resource
    private OrderMasterMapper orderMasterMapper;

    /**
     * 查询订单主
     *
     * @param orderId 订单主主键
     * @return 订单主
     */
    @Override
    public OrderMaster selectOrderMasterByOrderId(Integer orderId)
    {
        return orderMasterMapper.selectOrderMasterByOrderId(orderId);
    }

    /**
     * 查询订单主列表
     *
     * @param orderMaster 订单主
     * @return 订单主
     */
    @Override
    public List<OrderMaster> selectOrderMasterList(OrderMaster orderMaster)
    {
        return orderMasterMapper.selectOrderMasterList(orderMaster);
    }

    /**
     * 新增订单主
     *
     * @param orderMaster 订单主
     * @return 结果
     */
    @Override
    public int insertOrderMaster(OrderMaster orderMaster)
    {
        orderMaster.setCreateTime(DateUtils.getNowDate());
        return orderMasterMapper.insertOrderMaster(orderMaster);
    }

    /**
     * 修改订单主
     *
     * @param orderMaster 订单主
     * @return 结果
     */
    @Override
    public int updateOrderMaster(OrderMaster orderMaster)
    {
        return orderMasterMapper.updateOrderMaster(orderMaster);
    }

    /**
     * 批量删除订单主
     *
     * @param orderIds 需要删除的订单主主键
     * @return 结果
     */
    @Override
    public int deleteOrderMasterByOrderIds(Integer[] orderIds)
    {
        return orderMasterMapper.deleteOrderMasterByOrderIds(orderIds);
    }

    /**
     * 删除订单主信息
     *
     * @param orderId 订单主主键
     * @return 结果
     */
    @Override
    public int deleteOrderMasterByOrderId(Integer orderId)
    {
        return orderMasterMapper.deleteOrderMasterByOrderId(orderId);
    }
}
