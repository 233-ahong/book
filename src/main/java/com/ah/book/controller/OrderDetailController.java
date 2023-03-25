package com.ah.book.controller;

import java.util.List;
import javax.annotation.Resource;

import com.ah.book.common.core.controller.BaseController;
import com.ah.book.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ah.book.common.core.page.TableDataInfo;
import com.ah.book.domain.OrderDetail;
import com.ah.book.service.IOrderDetailService;

/**
 * 订单详情Controller
 *
 * @author 陈祥
 * @date 2022-12-11
 */
@RestController
@RequestMapping("/system/detail")
public class OrderDetailController extends BaseController
{
    @Resource
    private IOrderDetailService orderDetailService;

    /**
     * 查询订单详情列表
     */
    @GetMapping("/list")
    public TableDataInfo list(OrderDetail orderDetail)
    {
        startPage();
        List<OrderDetail> list = orderDetailService.selectOrderDetailList(orderDetail);
        return getDataTable(list);
    }

    /**
     * 获取订单详情详细信息
     */
    @GetMapping(value = "/{orderDetailId}")
    public AjaxResult getInfo(@PathVariable("orderDetailId") Integer orderDetailId)
    {
        return AjaxResult.success(orderDetailService.selectOrderDetailByOrderDetailId(orderDetailId));
    }

    /**
     * 新增订单详情
     */
    @PostMapping
    public AjaxResult add(@RequestBody OrderDetail orderDetail)
    {
        return toAjax(orderDetailService.insertOrderDetail(orderDetail));
    }

    /**
     * 修改订单详情
     */
    @PutMapping
    public AjaxResult edit(@RequestBody OrderDetail orderDetail)
    {
        return toAjax(orderDetailService.updateOrderDetail(orderDetail));
    }

    /**
     * 删除订单详情
     */
	@DeleteMapping("/{orderDetailIds}")
    public AjaxResult remove(@PathVariable Integer[] orderDetailIds)
    {
        return toAjax(orderDetailService.deleteOrderDetailByOrderDetailIds(orderDetailIds));
    }
}
