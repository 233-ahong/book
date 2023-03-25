package com.ah.book.controller;

import java.util.List;
import javax.annotation.Resource;

import com.ah.book.common.constant.Constants;
import com.ah.book.common.core.controller.BaseController;
import com.ah.book.common.core.domain.AjaxResult;
import com.ah.book.common.core.domain.model.LoginUser;
import com.ah.book.common.core.page.TableDataInfo;
import com.ah.book.common.core.redis.RedisCache;
import com.ah.book.common.utils.SecurityUtils;
import com.ah.book.common.utils.uuid.UUID;
import com.ah.book.domain.Borrow;
import com.ah.book.service.IBorrowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ah.book.domain.BookInfo;
import com.ah.book.service.IBookInfoService;


/**
 * 图书信息Controller
 *
 * @author ruoyi
 * @date 2022-09-10
 */
@RestController
@RequestMapping("/book/bookInfo")
public class BookInfoController extends BaseController
{
    @Resource
    private IBookInfoService bookInfoService;

    @Resource
    private IBorrowService borrowService;
    @Resource
    private RedisCache redisService;

    /**
     * 查询图书信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BookInfo bookInfo)
    {
        startPage();
        List<BookInfo> list = bookInfoService.selectBookInfoList(bookInfo);
        return getDataTable(list);
    }


    /**
     * 获取图书信息详细信息
     */
    @GetMapping(value = "/{bookId}")
    public AjaxResult getInfo(@PathVariable("bookId") Integer bookId)
    {
        return AjaxResult.success(bookInfoService.selectBookInfoByBookId(bookId));
    }

    /**
     * 新增图书信息
     */
    @PostMapping
    public AjaxResult add(@RequestBody BookInfo bookInfo)
    {
        bookInfo.setBookCore(UUID.fastUUID().toString());
        bookInfo.setBarCode(UUID.mathRandom(13));
        bookInfo.setBookRemaining(100);
        return toAjax(bookInfoService.insertBookInfo(bookInfo));
    }

    /**
     * 修改图书信息
     */
    @PutMapping
    public AjaxResult edit(@RequestBody BookInfo bookInfo)
    {
        return toAjax(bookInfoService.updateBookInfo(bookInfo));
    }

    /**
     * 删除图书信息
     */
    @DeleteMapping("/{bookIds}")
    public AjaxResult remove(@PathVariable Integer[] bookIds)
    {
        return toAjax(bookInfoService.deleteBookInfoByBookIds(bookIds));
    }

    /**
     * 借阅图书
     * @param id id
     * @param data 借阅时间
     * @return
     */
    @GetMapping("/borrow/{id}/{data}")
    public AjaxResult borrow(@PathVariable("id") int id, @PathVariable("data") Long data) {
        final LoginUser loginUser = SecurityUtils.getLoginUser();
        Borrow borrow =new Borrow();
        borrow.setBookId(id);
        final Borrow borrow1 = borrowService.selectBorrowByBookId(id);
        if (borrow1!=null&& borrow1.getBorrowState()!=0){
            return AjaxResult.error("当前图书已借阅且未归还");
        }
        final BookInfo bookInfo = bookInfoService.selectBookInfoByBookId(id);
        bookInfoService.modifyBookInventory(id);
        borrow.setBookName(bookInfo.getBookName());
        borrow.setBorrowingTime(data);
        borrow.setUserId(loginUser.getUserId());
        borrow.setUserName(loginUser.getUsername());
        final Integer value = redisService.getCacheMapValue(Constants.BORROW_PLACARD_KEY, String.valueOf(id));
        if (null!=value) {
            redisService.setCacheMapValue(Constants.BORROW_PLACARD_KEY,String.valueOf(id),value+1);
        } else {
            redisService.setCacheMapValue(Constants.BORROW_PLACARD_KEY,String.valueOf(id),1);
        }
        return toAjax(borrowService.insertBorrow(borrow));
    }

}
