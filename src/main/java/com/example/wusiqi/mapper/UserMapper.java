package com.example.wusiqi.mapper;

import com.example.wusiqi.entity.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM t_user")
    List<User> findAll();

    @Select("SELECT * FROM t_user LIMIT #{offset}, #{pageSize}")
    List<User> findPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(*) FROM t_user")
    int countAll();

    @Insert("INSERT INTO t_user(username, email) VALUES(#{username}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Update("UPDATE t_user SET username=#{username}, email=#{email} WHERE id=#{id}")
    int update(User user);

    @Delete("DELETE FROM t_user WHERE id=#{id}")
    int deleteById(Long id);
}