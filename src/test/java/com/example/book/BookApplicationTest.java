/*package com.example.book;

import com.ah.book.BookApplication;
import com.ah.book.common.core.domain.AjaxResult;
import com.ah.book.common.core.redis.RedisCache;
import com.ah.book.domain.BookInfo;
import com.ah.book.service.IBookInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

@SpringBootTest(classes = BookApplication.class)
public class BookApplicationTest {
    @Resource
    RedisCache redisCache;
    @Resource
    IBookInfoService bookInfoService;
    @Test
    public void t1() {
        List<BookInfo> sellingPlacard = redisCache.getCacheList("sellingPlacard");
        if (null != sellingPlacard && sellingPlacard.size() != 0) {
            System.out.println(sellingPlacard.size());
        }
        *//*final Map<String, Integer> map = redisCache.getCacheMap("selling_placard:");
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> {
            // 升序排序
            return o2.getValue().compareTo(o1.getValue());
        });
        Iterator<Map.Entry<String, Integer>> iterator = list.iterator();
        List<BookInfo> bookInfos = new ArrayList<>();
        for (int i =0;i < 10;i++) {
            Map.Entry<String, Integer> entry = iterator.next();
            final String key = entry.getKey();
            bookInfos.add(bookInfoService.selectBookInfoByBookId(Integer.valueOf(key)));
        }
        redisCache.setCacheList("selling_placard",bookInfos);
        System.out.println(bookInfos.size());
    }*//*
    }

}*/
