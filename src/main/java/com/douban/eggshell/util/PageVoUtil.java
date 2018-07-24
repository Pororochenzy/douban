package com.douban.eggshell.util;

import com.douban.eggshell.vo.ReviewPageVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 传入你想要分页的List<> ,通过此类，帮你报装成一个新的类,并返回出去
 * int cur_page;//当前页数
 * total_page;//总页数
 * Long sum;//总影评数量
 * List<T> XXX;
 */
public class PageVoUtil {
    public static <T> ReviewPageVO getVoByResults(List<T> Results) {
        PageInfo<T> film_reviewPageInfo = new PageInfo<>(Results);
        int cur_page = film_reviewPageInfo.getPageNum();//当前页数
        int total_page = film_reviewPageInfo.getPages(); //总页数
        long sum = film_reviewPageInfo.getTotal();//总影评数量

        //将带有分页属性 和 数据们 放进一个新的包装类
        ReviewPageVO br = new ReviewPageVO<>();
        br.setCur_page(cur_page);
        br.setSum(sum);
        br.setTotal_page(total_page);
        br.setReviews(Results);

        return br;
    }
}
