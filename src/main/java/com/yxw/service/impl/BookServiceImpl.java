package com.yxw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxw.dao.BookInfoMapper;
import com.yxw.dao.BookTypeMapper;
import com.yxw.pojo.BookInfo;
import com.yxw.pojo.BookType;
import com.yxw.pojo.PageDataResult;
import com.yxw.service.BookService;
import com.yxw.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yan on 2018/8/30.
 */
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookInfoMapper bookInfoMapper;

    @Autowired
    private BookTypeMapper bookTypeMapper;
    @Override
    public PageDataResult findAllBooks(int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page,rows);
        //执行查询
        List<BookInfo> list = bookInfoMapper.selectAllBooks();
        //分页信息
        PageInfo<BookInfo> pageInfo=new PageInfo<BookInfo>(list);
        //创建返回结果对象
        PageDataResult result=new PageDataResult();
        result.setTotal(Integer.parseInt(String.valueOf(pageInfo.getTotal())));
        result.setRows(list);

        return result;
    }

    @Override
    public List<BookType> findBookType() {
        return bookTypeMapper.findBookType();
    }

    @Override
    public PageDataResult findfuzzySearch(int page, int rows, String bookType, String bookName, Integer isBorrow) {
        //设置分页信息
        PageHelper.startPage(page,rows);
        //执行查询
        List<BookInfo> list = bookInfoMapper.selectfuzzySearch(bookType,bookName,isBorrow);
        //分页信息
        PageInfo<BookInfo> pageInfo=new PageInfo<BookInfo>(list);
        System.out.println(list.toString());
        //创建返回结果对象
        PageDataResult result=new PageDataResult();
        result.setTotal(Integer.parseInt(String.valueOf(pageInfo.getTotal())));
        result.setRows(list);
        return result;
    }

    @Override
    public E3Result updateBookIofo(BookInfo bookInfo) {
        bookInfoMapper.updateByPrimaryKeySelective(bookInfo);
        return E3Result.ok();
    }
}
