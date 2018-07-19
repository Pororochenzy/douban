package com.douban.eggshell.mapper;

import com.douban.eggshell.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
public interface UserMapper {


    List<User> listAll();
}
