package com.bj.house.mapper;

import com.bj.house.common.model.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * Created by BJ on 2018/1/12.
 */
@Mapper
public interface UserMapper {

    public List<User> selectUsers();
}
