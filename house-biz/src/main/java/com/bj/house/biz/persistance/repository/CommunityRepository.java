package com.bj.house.biz.persistance.repository;

import com.bj.house.common.entity.Community;
import com.bj.house.common.entity.House;
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
public interface CommunityRepository extends JpaRepository<Community,Integer>,JpaSpecificationExecutor<Community>{

    //查询
    @Query(value = "select * from community c where c.id=?1 and c.name=?2",nativeQuery = true)
    List<Community> selectCommunity(Integer id, String name);


}
