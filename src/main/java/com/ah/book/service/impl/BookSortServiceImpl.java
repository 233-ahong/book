package com.ah.book.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah.book.domain.BookCategory;
import com.ah.book.domain.Dict;
import com.ah.book.common.utils.DictUtils;
import com.ah.book.common.utils.StringUtils;
import com.ah.book.domain.BookSort;
import com.ah.book.mapper.BookCategoryMapper;
import com.ah.book.mapper.BookSortMapper;
import com.ah.book.service.IBookSortService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 书籍分类Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-05
 */
@Service
public class BookSortServiceImpl implements IBookSortService
{
    @Resource
    private BookSortMapper bookSortMapper;

    @Resource
    private BookCategoryMapper bookCategoryMapper;

    /**
     * 查询书籍分类
     *
     * @param sortId 书籍分类主键
     * @return 书籍分类
     */
    @Override
    public BookSort selectBookSortBySortId(Long sortId)
    {
        return bookSortMapper.selectBookSortBySortId(sortId);
    }

    /**
     * 查询书籍分类列表
     *
     * @param bookSort 书籍分类
     * @return 书籍分类
     */
    @Override
    public List<BookSort> selectBookSortList(BookSort bookSort)
    {
        return bookSortMapper.selectBookSortList(bookSort);
    }

    /**
     * 新增书籍分类
     *
     * @param bookSort 书籍分类
     * @return 结果
     */
    @Override
    public int insertBookSort(BookSort bookSort)
    {
        return bookSortMapper.insertBookSort(bookSort);
    }

    /**
     * 修改书籍分类
     *
     * @param bookSort 书籍分类
     * @return 结果
     */
    @Override
    public int updateBookSort(BookSort bookSort)
    {
        return bookSortMapper.updateBookSort(bookSort);
    }

    /**
     * 批量删除书籍分类
     *
     * @param sortIds 需要删除的书籍分类主键
     * @return 结果
     */
    @Override
    public int deleteBookSortBySortIds(Long[] sortIds)
    {
        return bookSortMapper.deleteBookSortBySortIds(sortIds);
    }

    /**
     * 删除书籍分类信息
     *
     * @param sortId 书籍分类主键
     * @return 结果
     */
    @Override
    public int deleteBookSortBySortId(Long sortId)
    {
        return bookSortMapper.deleteBookSortBySortId(sortId);
    }

    @Override
    public List<Dict> selectBookSortDict() {
        List<Dict> dicts = DictUtils.getDictCache("bookSort");
        if (StringUtils.isNotEmpty(dicts)){
            return dicts;
        }
        List<BookCategory> bookCategories = bookCategoryMapper.selectBookCategoryList(null);
        if (StringUtils.isNotEmpty(bookCategories)){
            List<Dict> dicts1 = new ArrayList<>();
            Dict d = null;
            Map<String,String> map = new HashMap<>();
            for (BookCategory bookSort : bookCategories) {
                map.put("listClass","primary");
                d = new Dict(bookSort.getCategoryCode(),bookSort.getCategoryName(),map);
                dicts1.add(d);
            }
            DictUtils.setDictCache("bookSort",dicts1);
            return dicts1;
        }
        return null;
    }
}

