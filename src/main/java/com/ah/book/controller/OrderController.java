package com.ah.book.controller;

import com.ah.book.common.constant.Constants;
import com.ah.book.common.core.controller.BaseController;
import com.ah.book.common.core.domain.AjaxResult;
import com.ah.book.common.core.page.TableDataInfo;
import com.ah.book.common.core.redis.RedisCache;
import com.ah.book.common.exception.ServiceException;
import com.ah.book.common.utils.DateUtils;
import com.ah.book.common.utils.SecurityUtils;
import com.ah.book.domain.*;
import com.ah.book.domain.vo.OrderVo;
import com.ah.book.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 26471
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Resource
    IOrderCartService orderCartService;

    @Resource
    IBookInfoService bookInfoService;

    @Resource
    IOrderCustomerAddrService orderCustomerAddrService;

    @Resource
    IOrderMasterService orderMasterService;

    @Resource
    IOrderDetailService orderDetailService;

    @Resource
    private RedisCache redisService;

    private BookInfo info;

    @PutMapping("/add")
    public AjaxResult addOrder(@RequestBody OrderVo orderVo){
        System.out.println(orderVo);
        final Long userId = SecurityUtils.getUserId();
        if (orderVo.getCartId()!=null) {
            final OrderCart cart = orderCartService.selectOrderCartByCartId(orderVo.getCartId());
            info = bookInfoService.selectBookInfoByBookId(cart.getBookId());
        } else {
            info = bookInfoService.selectBookInfoByBookId(orderVo.getProductId());
        }
        if (orderVo.getProductAmount()> info.getBookRemaining()) {
            throw new ServiceException("库存不足，请联系商家");
        }
        info.setBookRemaining((int) (info.getBookRemaining()-orderVo.getProductAmount()));
        bookInfoService.updateBookInfo(info);
        final OrderCustomerAddr orderCustomerAddr = orderCustomerAddrService.selectOrderCustomerAddrByCustomerAddrId(orderVo.getCustomerAddrId());
        Long dateTime = Long.valueOf(DateUtils.dateTimeNow());
        OrderMaster orderMaster = new OrderMaster();
        BigDecimal price = new BigDecimal(orderVo.getProductAmount());
        orderMaster.setCustomerId(userId);
        orderMaster.setOrderSn(dateTime);
        orderMaster.setProvince(orderCustomerAddr.getProvince());
        orderMaster.setCity(orderCustomerAddr.getCity());
        orderMaster.setDistrict(orderCustomerAddr.getDistrict());
        orderMaster.setAddress(orderCustomerAddr.getAddress());
        orderMaster.setOrderMoney(info.getPrice().multiply(price));
        orderMaster.setShippingUser(orderVo.getShippingUser());
        orderMasterService.insertOrderMaster(orderMaster);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderMasterService.selectOrderMasterList(orderMaster).get(0).getOrderId());
        orderDetail.setProductCnt(orderVo.getProductAmount());
        orderDetail.setProductPrice(info.getPrice());
        orderDetail.setProductName(info.getBookName());
        orderDetail.setProductId(info.getBookId());
        orderDetailService.insertOrderDetail(orderDetail);
        orderCartService.deleteOrderCartByCartId(orderVo.getCartId());
        final Integer value = redisService.getCacheMapValue(Constants.SELLING_PLACARD_KEY,info.getBookId().toString());
        if (null!=value) {
            redisService.setCacheMapValue(Constants.SELLING_PLACARD_KEY,info.getBookId().toString(),value+orderVo.getProductAmount());
        } else {
            redisService.setCacheMapValue(Constants.SELLING_PLACARD_KEY,info.getBookId().toString(),orderVo.getProductAmount());
        }
        return AjaxResult.success();
    }

    @GetMapping("/getOrderMaster")
    public TableDataInfo list(OrderMaster orderMaster) {
        orderMaster.setCustomerId(SecurityUtils.getUserId());
        startPage();
        List<OrderMaster> list = orderMasterService.selectOrderMasterList(orderMaster);
        return getDataTable(list);
    }
}
