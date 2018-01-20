package com.bj.house.biz.persistance.repository;

import com.bj.house.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by BJ on 2018/1/20.
 */
public interface UserRepository extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User>{

    int deleteByEmail(String email);

/*
    List<BookInfo> queryByBookName(String bookName);

    //模糊查询
    @Query(value = "select * from book_info i where i.book_name like CONCAT('%',:bookName,'%')",nativeQuery = true)
    List<BookInfo> queryByBookNameLike(@Param("bookName") String bookName);

    BookInfo queryByBookId(String bookId);

    Page<BookInfo> findAll(Pageable pageable);*/
}
