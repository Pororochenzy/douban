package com.douban.eggshell.service.imp;

import com.douban.eggshell.mapper.RespondMapper;
import com.douban.eggshell.service.RespondService;
import com.douban.eggshell.util.DateUtil;
import com.douban.eggshell.vo.respond.RespVo;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RespondServiceImpl implements RespondService {
    @Autowired
    RespondMapper respondMapper;

    @Override
    public List<RespVo> findResByReviewId(int id) {
        return  respondMapper.listRespond(id);
    }

    @Override
    public boolean addResond(String comment, int userinfo_id, int review_id) {
        Map<String,Object> map = new HashMap<>();

        map.put("comment",comment);
        map.put("createtime",DateUtil.dataToString(new Date()));
        map.put("userinfo_id",userinfo_id);
        map.put("review_id",review_id);

        int num = respondMapper.addResond(map);
        if(num>0){
            return  true;
        }
        return false;
    }
}
