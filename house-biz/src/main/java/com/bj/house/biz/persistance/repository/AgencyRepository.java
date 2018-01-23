package com.bj.house.biz.persistance.repository;

import com.bj.house.common.entity.Agency;
import com.bj.house.common.entity.House;
import com.bj.house.common.entity.User;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.OrderBy;
import java.util.List;

/**
 * Created by BJ on 2018/1/20.
 */
public interface AgencyRepository extends JpaRepository<Agency,Integer>,JpaSpecificationExecutor<Agency>{

    //@OrderBy("id desc")
    @Query(value = "select * from user u where u.enable='1' and u.type='2' and u.id=?1 and u.name=?2 order by u.id desc limit ?3,?4 ",nativeQuery = true)
    List<User> queryByName(Long id, String name,Integer a,Integer b);

    @Query(value = "select * from user u where u.enable='1' and u.type='2' and u.id=?1",nativeQuery = true)
    List<User> selectAgent(Long id);

}
