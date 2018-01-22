package com.bj.house.biz.persistance.repository;

import com.bj.house.common.entity.House;
import com.bj.house.common.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by BJ on 2018/1/20.
 */
public interface HouseRepository extends JpaRepository<House,Integer>,JpaSpecificationExecutor<House>{

    /*int deleteByEmail(String email);

    User findByEmail(String email);

    //模糊查询
    @Query(value = "select * from book_info i where i.book_name like CONCAT('%',:bookName,'%')",nativeQuery = true)
    List<BookInfo> queryByBookNameLike(@Param("bookName") String bookName);

    BookInfo queryByBookId(String bookId);

    Page<BookInfo> findAll(Pageable pageable);*/

    //分页查询
    Page<House> findAll(Pageable pageable);

    //数量查询
    @Query(value = "select COUNT(*) from house ",nativeQuery = true)
    Integer queryHouseCount();

    //模糊查询
    @Query(value = "select * from house i where i.name like CONCAT('%',:houseName,'%')",nativeQuery = true)
    List<House> queryByHouseNameLike(@Param("houseName") String houseName);

}
