package com.bj.house.biz.persistance.repository;

import com.bj.house.common.entity.HouseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by BJ on 2018/1/20.
 */
public interface HouseUserRepository extends JpaRepository<HouseUser,Integer>,JpaSpecificationExecutor<HouseUser>{


}
