package com.yxw.dao;

import com.yxw.pojo.BookInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface BookInfoMapper {
    int deleteByPrimaryKey(Integer bookId);

    int insert(BookInfo record);

    int insertSelective(BookInfo record);

    BookInfo selectByPrimaryKey(Integer bookId);

    int updateByPrimaryKeySelective(BookInfo record);

    int updateByPrimaryKey(BookInfo record);

    List<BookInfo> selectAllBooks();

    List<BookInfo> selectfuzzySearch(@Param("bookType") String bookType,@Param("bookName") String bookName, @Param("isBorrow") Integer isBorrow);
}