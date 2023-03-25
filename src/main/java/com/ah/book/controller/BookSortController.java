package com.ah.book.controller;

import java.util.List;

import com.ah.book.common.core.controller.BaseController;
import com.ah.book.common.core.domain.AjaxResult;
import com.ah.book.common.core.page.TableDataInfo;
import com.ah.book.domain.BookSort;
import com.ah.book.service.IBookSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 书籍分类Controller
 *
 * @author ruoyi
 * @date 2022-12-05
 */
@RestController
@RequestMapping("/system/sort")
public class BookSortController extends BaseController
{
    @Autowired
    private IBookSortService bookSortService;

    /**
     * 查询书籍分类列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BookSort bookSort)
    {
        startPage();
        List<BookSort> list = bookSortService.selectBookSortList(bookSort);
        return getDataTable(list);
    }

    /**
     * 获取分类字典
     * @return
     */
    @PostMapping("/dict")
    public AjaxResult dict(){
        return AjaxResult.success(bookSortService.selectBookSortDict());
    }


    /**
     * 获取书籍分类详细信息
     */
    @GetMapping(value = "/{sortId}")
    public AjaxResult getInfo(@PathVariable("sortId") Long sortId)
    {
        return AjaxResult.success(bookSortService.selectBookSortBySortId(sortId));
    }

    /**
     * 新增书籍分类
     */
    @PostMapping
    public AjaxResult add(@RequestBody BookSort bookSort)
    {
        return toAjax(bookSortService.insertBookSort(bookSort));
    }

    /**
     * 修改书籍分类
     */
    @PutMapping
    public AjaxResult edit(@RequestBody BookSort bookSort)
    {
        return toAjax(bookSortService.updateBookSort(bookSort));
    }

    /**
     * 删除书籍分类
     */
    @DeleteMapping("/{sortIds}")
    public AjaxResult remove(@PathVariable Long[] sortIds)
    {
        return toAjax(bookSortService.deleteBookSortBySortIds(sortIds));
    }
}

