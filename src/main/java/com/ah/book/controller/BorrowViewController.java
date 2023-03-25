package com.ah.book.controller;

import com.ah.book.common.core.controller.BaseController;
import com.ah.book.common.core.page.TableDataInfo;
import com.ah.book.common.utils.SecurityUtils;
import com.ah.book.domain.Borrow;
import com.ah.book.domain.BorrowView;
import com.ah.book.service.IBorrowViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/borrowView")
public class BorrowViewController extends BaseController {

    @Resource
    IBorrowViewService borrowViewService;

    @GetMapping("/get")
    public TableDataInfo list(BorrowView borrowView) {
        borrowView.setUserId(SecurityUtils.getUserId());
        startPage();
        final List<BorrowView> list = borrowViewService.selectBorrowViewList(borrowView);
        return getDataTable(list);
    }
}
