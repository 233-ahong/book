package com.ah.book.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ah.book.mapper.OrderCustomerAddrMapper;
import com.ah.book.domain.OrderCustomerAddr;
import com.ah.book.service.IOrderCustomerAddrService;

/**
 * 用户地址Service业务层处理
 * 
 * @author 陈祥
 * @date 2022-12-11
 */
@Service
public class OrderCustomerAddrServiceImpl implements IOrderCustomerAddrService 
{
    @Autowired
    private OrderCustomerAddrMapper orderCustomerAddrMapper;

    /**
     * 查询用户地址
     * 
     * @param customerAddrId 用户地址主键
     * @return 用户地址
     */
    @Override
    public OrderCustomerAddr selectOrderCustomerAddrByCustomerAddrId(Integer customerAddrId)
    {
        return orderCustomerAddrMapper.selectOrderCustomerAddrByCustomerAddrId(customerAddrId);
    }

    /**
     * 查询用户地址列表
     * 
     * @param orderCustomerAddr 用户地址
     * @return 用户地址
     */
    @Override
    public List<OrderCustomerAddr> selectOrderCustomerAddrList(OrderCustomerAddr orderCustomerAddr)
    {
        return orderCustomerAddrMapper.selectOrderCustomerAddrList(orderCustomerAddr);
    }

    /**
     * 新增用户地址
     * 
     * @param orderCustomerAddr 用户地址
     * @return 结果
     */
    @Override
    public int insertOrderCustomerAddr(OrderCustomerAddr orderCustomerAddr)
    {
        return orderCustomerAddrMapper.insertOrderCustomerAddr(orderCustomerAddr);
    }

    /**
     * 修改用户地址
     * 
     * @param orderCustomerAddr 用户地址
     * @return 结果
     */
    @Override
    public int updateOrderCustomerAddr(OrderCustomerAddr orderCustomerAddr)
    {
        return orderCustomerAddrMapper.updateOrderCustomerAddr(orderCustomerAddr);
    }

    /**
     * 批量删除用户地址
     * 
     * @param customerAddrIds 需要删除的用户地址主键
     * @return 结果
     */
    @Override
    public int deleteOrderCustomerAddrByCustomerAddrIds(Integer[] customerAddrIds)
    {
        return orderCustomerAddrMapper.deleteOrderCustomerAddrByCustomerAddrIds(customerAddrIds);
    }

    /**
     * 删除用户地址信息
     * 
     * @param customerAddrId 用户地址主键
     * @return 结果
     */
    @Override
    public int deleteOrderCustomerAddrByCustomerAddrId(Integer customerAddrId)
    {
        return orderCustomerAddrMapper.deleteOrderCustomerAddrByCustomerAddrId(customerAddrId);
    }
}
