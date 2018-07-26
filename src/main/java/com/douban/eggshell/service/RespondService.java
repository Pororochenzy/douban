package com.douban.eggshell.service;


import com.douban.eggshell.vo.respond.RespVo;

import java.util.List;

public interface RespondService {

    /**
     * 通过影评id 找到 所有的回应
     * @param id
     * @return
     */
    List<RespVo> findResByReviewId (int id );

    /**
     * 评论当前的影评 ,增加一条回应
     * @param comment
     * @param userinfo_id
     * @param review_id
     * @return
     */
    boolean addResond(String comment,int userinfo_id,int review_id );
}
