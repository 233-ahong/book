package com.ah.book.controller;

import java.util.List;

import com.ah.book.common.core.controller.BaseController;
import com.ah.book.common.core.domain.AjaxResult;
import com.ah.book.common.core.page.TableDataInfo;
import com.ah.book.common.utils.SecurityUtils;
import com.ah.book.service.IBookInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ah.book.domain.Borrow;
import com.ah.book.service.IBorrowService;

import javax.annotation.Resource;

/**
 * 借阅记录详情Controller
 *
 * @author ah
 * @date 2022-09-20
 */
@RestController
@RequestMapping("/book/borrow")
public class BorrowController extends BaseController
{
    @Resource
    private IBorrowService borrowService;

    @Resource
    private IBookInfoService bookInfoService;

    /**
     * 查询借阅记录详情列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Borrow borrow)
    {
        final String userRole = SecurityUtils.getLoginUser().getUser().getUserRole();
        if (!"admin".equals(userRole)) {
            borrow.setUserId(SecurityUtils.getUserId());
        }
        startPage();
        List<Borrow> list = borrowService.selectBorrowList(borrow);
        return getDataTable(list);
    }

    @GetMapping("/userList")
    public TableDataInfo userList(Borrow borrow)
    {
        borrow.setUserId(SecurityUtils.getUserId());
        startPage();
        List<Borrow> list = borrowService.selectBorrowList(borrow);
        return getDataTable(list);
    }

    /**
     * 获取借阅记录详情详细信息
     */
    @GetMapping(value = "/{borrowId}")
    public AjaxResult getInfo(@PathVariable("borrowId") Integer borrowId)
    {
        return AjaxResult.success(borrowService.selectBorrowByBorrowId(borrowId));
    }

    /**
     * 新增借阅记录详情
     */
    @PostMapping
    public AjaxResult add(@RequestBody Borrow borrow)
    {
        return toAjax(borrowService.insertBorrow(borrow));
    }

    /**
     * 修改借阅记录详情
     */
    @PutMapping
    public AjaxResult edit(@RequestBody Borrow borrow)
    {
        return toAjax(borrowService.updateBorrow(borrow));
    }

    /**
     * 删除借阅记录详情
     */
    @DeleteMapping("/{borrowIds}")
    public AjaxResult remove(@PathVariable Integer[] borrowIds)
    {
        return toAjax(borrowService.deleteBorrowByBorrowIds(borrowIds));
    }

    /**
     * 归还图书
     */
    @DeleteMapping("/return/{borrowIds}")
    public AjaxResult returnBook(@PathVariable("borrowIds") Integer[] borrowIds)
    {
        return toAjax(borrowService.returnBookByUser(borrowIds));
    }
    @DeleteMapping("/returnBook/{bookIds}/{borrowIds}")
    public AjaxResult borrowBook(@PathVariable("bookIds")Integer[] bookIds,@PathVariable("borrowIds")Integer[] borrowIds) {
        bookInfoService.returnBookByIds(bookIds);
        return toAjax(borrowService.returnBookByIds(borrowIds));
    }
}
