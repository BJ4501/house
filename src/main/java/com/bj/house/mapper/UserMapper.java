package com.bj.house.mapper;

//import org.apache.ibatis.annotations.Mapper;
import com.bj.house.persistance.entity.User;

        import java.util.List;

/**
 * Created by BJ on 2018/1/12.
 */
//@Mapper
public interface UserMapper {

    public List<User> selectUsers();
}
