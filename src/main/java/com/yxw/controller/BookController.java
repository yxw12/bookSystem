package com.yxw.controller;

import com.yxw.pojo.BookInfo;
import com.yxw.pojo.BookType;
import com.yxw.pojo.PageDataResult;
import com.yxw.service.BookService;
import com.yxw.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by Yan on 2018/8/30.
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @RequestMapping("/findAllBooks")
    @ResponseBody
    public PageDataResult success(@RequestParam(name = "page",defaultValue = "1") int page,
                                @RequestParam(name = "rows",defaultValue = "4") int rows){
        PageDataResult result=bookService.findAllBooks(page,rows);
        return result;
    }

    @RequestMapping("/findBookType")
    @ResponseBody
    public List<BookType> findBookType(){
        List<BookType> result=bookService.findBookType();
        return result;
    }


    @RequestMapping("/fuzzySearch")
    @ResponseBody
    public PageDataResult findfuzzySearch(@RequestParam(name = "page",defaultValue = "1") int page,
                                  @RequestParam(name = "rows",defaultValue = "10") int rows,
                                          String bookType,String bookName,Integer isBorrow){
     //   System.out.println(bookType+".."+bookName+"..."+isBorrow);
        PageDataResult result=bookService.findfuzzySearch(page,rows,bookType,bookName,isBorrow);
        return result;
    }

    @RequestMapping("user/alterIsBorrow")
    @ResponseBody
    public E3Result alterIsBorrow(Integer isBorrow, Integer bookId){
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookId(bookId);
        //是否借阅1、已借阅2、未借阅
        bookInfo.setIsBorrow(1);
        E3Result result = bookService.updateBookIofo(bookInfo);
        return result;
    }
}
