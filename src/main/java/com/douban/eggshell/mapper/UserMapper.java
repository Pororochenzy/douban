package com.douban.eggshell.mapper;

import com.douban.eggshell.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserMapper {
    @Mapper
    List<User> listAll();
}
