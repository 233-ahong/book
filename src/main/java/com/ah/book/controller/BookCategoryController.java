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
import com.ah.book.domain.BookCategory;
import com.ah.book.service.IBookCategoryService;

/**
 * 书籍分类Controller
 *
 * @author 陈祥
 * @date 2022-12-11
 */
@RestController
@RequestMapping("/system/category")
public class BookCategoryController extends BaseController
{
    @Resource
    private IBookCategoryService bookCategoryService;

    /**
     * 查询书籍分类列表
     */
    @GetMapping("/list")
    public AjaxResult list(BookCategory bookCategory)
    {
        List<BookCategory> list = bookCategoryService.selectBookCategoryList(bookCategory);
        return AjaxResult.success(list);
    }

    /**
     * 获取书籍分类详细信息
     */
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Integer categoryId)
    {
        return AjaxResult.success(bookCategoryService.selectBookCategoryByCategoryId(categoryId));
    }

    /**
     * 新增书籍分类
     */
    @PostMapping
    public AjaxResult add(@RequestBody BookCategory bookCategory)
    {
        return toAjax(bookCategoryService.insertBookCategory(bookCategory));
    }

    /**
     * 修改书籍分类
     */
    @PutMapping
    public AjaxResult edit(@RequestBody BookCategory bookCategory)
    {
        return toAjax(bookCategoryService.updateBookCategory(bookCategory));
    }

    /**
     * 删除书籍分类
     */
    @DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Integer[] categoryIds)
    {
        return toAjax(bookCategoryService.deleteBookCategoryByCategoryIds(categoryIds));
    }
}
