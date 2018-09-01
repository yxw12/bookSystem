package com.yxw.service;

import com.yxw.pojo.BookInfo;
import com.yxw.pojo.BookType;
import com.yxw.pojo.PageDataResult;
import com.yxw.utils.E3Result;

import java.util.List;

/**
 * Created by Yan on 2018/8/30.
 */
public interface BookService {
    PageDataResult findAllBooks(int page, int rows);

    List<BookType> findBookType();

    PageDataResult findfuzzySearch(int page, int rows, String bookType, String bookName, Integer isBorrow);

    E3Result updateBookIofo(BookInfo bookInfo);
}
