package com.ah.book.controller;

import com.ah.book.common.constant.Constants;
import com.ah.book.common.core.controller.BaseController;
import com.ah.book.common.core.domain.AjaxResult;
import com.ah.book.common.core.page.TableDataInfo;
import com.ah.book.common.core.redis.RedisCache;
import com.ah.book.domain.BookInfo;
import com.ah.book.service.IBookInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;


/**
 * @author 26471
 */
@RestController
@RequestMapping("/routine/book")
public class BookController extends BaseController {

    @Resource
    IBookInfoService bookInfoService;

    @Resource
    RedisCache redisCache;

    @GetMapping("/list")
    public TableDataInfo list(BookInfo bookInfo) {
        startPage();
        List<BookInfo> list = bookInfoService.selectBookInfoList(bookInfo);
        return getDataTable(list);
    }

    @GetMapping("/bookInfo/{id}")
    public AjaxResult getBookInfo(@PathVariable Integer id) {
        return AjaxResult.success(bookInfoService.selectBookInfoByBookId(id));
    }

    @GetMapping("/sellingPlacard")
    public AjaxResult getSelling() {
        List<BookInfo> sellingPlacard = redisCache.getCacheList("sellingPlacard");
        if (null!=sellingPlacard && sellingPlacard.size()!=0) {
            return AjaxResult.success(sellingPlacard);
        }
        final Map<String, Integer> map = redisCache.getCacheMap(Constants.SELLING_PLACARD_KEY);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> {
            return o2.getValue().compareTo(o1.getValue());
        });
        Iterator<Map.Entry<String, Integer>> iterator = list.iterator();
        List<BookInfo> bookInfos = new ArrayList<>();
        for (int i =0;i < 10;i++) {
            Map.Entry<String, Integer> entry = iterator.next();
            final String key = entry.getKey();
            bookInfos.add(bookInfoService.selectBookInfoByBookId(Integer.valueOf(key)));
        }
        redisCache.setCacheList("sellingPlacard",bookInfos);
        return AjaxResult.success(bookInfos);
    }

    @GetMapping("/borrowPlacard")
    public AjaxResult getBorrow() {
        final List<BookInfo> borrowPlacard = redisCache.getCacheList("borrowPlacard");
        if (null!=borrowPlacard && borrowPlacard.size()!=0){
            return AjaxResult.success(borrowPlacard);
        }
        final Map<String, Integer> map = redisCache.getCacheMap(Constants.BORROW_PLACARD_KEY);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> {
            // 降序排序
            return o2.getValue().compareTo(o1.getValue());
        });
        Iterator<Map.Entry<String, Integer>> iterator = list.iterator();
        List<BookInfo> bookInfos = new ArrayList<>();
        for (int i =0;i < 10;i++) {
            Map.Entry<String, Integer> entry = iterator.next();
            final String key = entry.getKey();
            bookInfos.add(bookInfoService.selectBookInfoByBookId(Integer.valueOf(key)));
        }
        redisCache.setCacheList("borrowPlacard",bookInfos);
        return AjaxResult.success(bookInfos);
    }

    @GetMapping("/randomBook")
    public AjaxResult getRandomBook() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int count = 0;
        Random r = new Random();
        while(count<5){
            int i = r.nextInt(20) +1;
            if(!list.contains(i)){
                list.add(i);
                count++;
            }
        }
        List<BookInfo> bookInfos = new ArrayList<>();
        for (Integer integer : list) {
            bookInfos.add(bookInfoService.selectBookInfoByBookId(integer));
        }
        return AjaxResult.success(bookInfos);
    }
}
