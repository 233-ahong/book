package com.ah.book.controller;

import com.ah.book.common.core.controller.BaseController;
import com.ah.book.common.core.domain.AjaxResult;
import com.ah.book.domain.BookCategory;
import com.ah.book.service.IBookCategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 26471
 */
@RestController
@RequestMapping("/routine/classify")
public class ClassifyController extends BaseController {
    @Resource
    IBookCategoryService bookCategoryService;
    @RequestMapping("/list")
    public AjaxResult getClassify(BookCategory bookCategory) {
        final List<BookCategory> bookCategories = bookCategoryService.selectBookCategoryList(bookCategory);
        return AjaxResult.success(bookCategories);
    }
}
