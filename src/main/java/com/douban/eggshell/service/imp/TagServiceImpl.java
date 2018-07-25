package com.douban.eggshell.service.imp;

import com.douban.eggshell.mapper.TagMapper;
import com.douban.eggshell.pojo.Style;
import com.douban.eggshell.pojo.TypeArea;
import com.douban.eggshell.pojo.TypeStyle;
import com.douban.eggshell.service.TagService;
import com.douban.eggshell.vo.TagGetMovieVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;

    @Override
    public List<TypeArea> listArea() {
        return tagMapper.listArea();
    }

    @Override
    public List<TypeStyle> listStyle() {
        return tagMapper.listStyle();
    }

    /**
     * sort 默认传入null
     * range  默认传入"0,10"
     * tags 默认传入null
     *
     * @param sort  H按热度排序，T按时间排序，G按评分排序，默认H
     * @param range 评分区间，区间为[0,10]，默认0,10
     * @param tags  电影标签，包含电影类型和电影地区各一个，顺序，先style ，后area
     * @return
     */
    @Override
    public List<TagGetMovieVo> findByType(String sort, String range, String tags) {
        Map<String, Object> map = new HashMap<>();

        if (sort != null) {
            //log.info("打印服务层sort的值：{}", sort);

            if (sort.equals("G")) {
                map.put("sort", "G");
            }
            if (sort.equals("T")) {
                map.put("sort", "T");
            }
            if (sort.equals("H")) {
                map.put("sort", "H");
            }
        } else {
            log.info("服务层sort的值为空：{}", sort);
        }

        String[] rangeStr = range.split(",");

        int start = Integer.valueOf(rangeStr[0]);
        int end = Integer.valueOf(rangeStr[1]);

        map.put("start", start);
        map.put("end", end);

        // tags类型 第一种情况 style ， 第二种情况 area  ,顺序，先style ，后area

        if (tags != null) {
            if (tags.contains(",")) {
                String[] tagStr = tags.split(",");
                String style = tagStr[0];
                String area = tagStr[1];
//                    log.info("服务层style打印{}", style);
//                    log.info("服务层area打印{}", area);
                map.put("style", style);
                map.put("area", area);
            } else {
                //进了这里，证明tag 只有一个参数 ,所以要判断这个是类型 还是地区， 因为 顺序 有可能 相反
                boolean flag = findStyleByName(tags);
                //如果flag为true的话，证明当前这个tag是个 类型(style),如果false的话 证明是地区
                if (flag) {
                    String style = tags;
                    map.put("style", style);
                } else {
                    String area = tags;
                    map.put("area", area);
                }


            }
        }


        return tagMapper.findByType(map);
    }

    @Override
    public boolean findStyleByName(String name) {
        Style obj = tagMapper.findStyleByName(name);
        if (obj !=null) {
            return true;
        }
        return false;
    }
}