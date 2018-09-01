package com.yxw.dao;

import com.yxw.pojo.BookInfo;
import com.yxw.pojo.BookType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(BookType record);

    int insertSelective(BookType record);

    BookType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BookType record);

    int updateByPrimaryKey(BookType record);

    List<BookType> findBookType();
}