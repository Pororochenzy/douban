package com.douban.eggshell.mapper;

import com.douban.eggshell.vo.respond.RespVo;

import java.util.List;
import java.util.Map;

public interface RespondMapper {
    List<RespVo> listRespond(int id);
    int addResond(Map map);
}
