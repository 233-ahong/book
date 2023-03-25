package com.ah.book.controller;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import com.ah.book.common.core.controller.BaseController;
import com.ah.book.common.core.domain.AjaxResult;
import com.ah.book.common.utils.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ah.book.common.core.page.TableDataInfo;
import com.ah.book.domain.OrderMasterView;
import com.ah.book.service.IOrderMasterViewService;

/**
 * 订单主Controller
 *
 * @author 陈祥
 * @date 2022-12-28
 */
@RestController
@RequestMapping("/system/masterView")
public class OrderMasterViewController extends BaseController
{
    @Resource
    private IOrderMasterViewService orderMasterViewService;

    /**
     * 查询订单主列表
     */
    @GetMapping("/list")
    public TableDataInfo list(OrderMasterView orderMasterView)
    {
        startPage();
        List<OrderMasterView> list = orderMasterViewService.selectOrderMasterViewByUserId(SecurityUtils.getUserId());
        return getDataTable(list);
    }


    /**
     * 新增订单主
     */
    @PostMapping
    public AjaxResult add(@RequestBody OrderMasterView orderMasterView)
    {
        return toAjax(orderMasterViewService.insertOrderMasterView(orderMasterView));
    }

    /**
     * 修改订单主
     */
    @PutMapping("/{id}")
    public AjaxResult edit(@PathVariable Integer id)
    {
        OrderMasterView orderMasterView =new OrderMasterView();
        orderMasterView.setOrderId(id);
        orderMasterView.setOrderStatus(2);
        orderMasterView.setReceiveTime(new Date());
        return toAjax(orderMasterViewService.updateOrderMasterView(orderMasterView));
    }

    /**
     * 删除订单主
     */
    @DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Integer[] orderIds)
    {
        return toAjax(orderMasterViewService.deleteOrderMasterViewByOrderIds(orderIds));
    }
}
