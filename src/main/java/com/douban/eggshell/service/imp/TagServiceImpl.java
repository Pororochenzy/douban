package com.douban.eggshell.service.imp;

import com.douban.eggshell.mapper.TagMapper;
import com.douban.eggshell.pojo.Style;
import com.douban.eggshell.pojo.StyleAndArea;
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
//        Map<String, Object> map = new HashMap<>();
        StyleAndArea  saa = new StyleAndArea();

        if (sort.equals("G")) {
//            map.put("sort", "G");
            saa.setSort("G");
        }
        if (sort.equals("T")) {
//            map.put("sort", "T");
            saa.setSort("T");
        }
        if (sort.equals("H")) {
//            map.put("sort", "H");
            saa.setSort("H");
        }
        String[] rangeStr = range.split(",");

        int start = Integer.valueOf(rangeStr[0]);
        int end = Integer.valueOf(rangeStr[1]);

//        map.put("start", start);
//        map.put("end", end);
            saa.setStart(start);
            saa.setEnd(end);

        //电影类型 和电影地区
        // tags类型 第一种情况 style ， 第二种情况 area  ,顺序，先style ，后area
        if (tags != null) {

            if (tags.endsWith(",")) {
                tags = tags.substring(0, tags.indexOf(","));
//                log.info("去除逗号后的tags{}", tags);
            }

//            log.info("打印服务层的tags字符串:{}", tags);

            if (tags.contains(",")) {
                String[] tagStr = tags.split(",");
//                log.info("分割后的style:",tagStr[0]);
//                log.info("分割后的area:",tagStr[1]);

//                map.put("style", tagStr[0]);
//                map.put("area", tagStr[1]);
                saa.setStyle(tagStr[0]);
                saa.setArea(tagStr[1]);

//                log.info("双参后的map是:{}",map);
                return tagMapper.findByType(saa);
            }
            boolean flag = false;

    /*        Style obj = tagMapper.findStyleByName(tags);

            log.info("查询Style对象obj:{}",obj);

            if (obj != null) {
                flag = true;
            }*/
String styleStr="剧情 喜剧动作 爱情 科幻 悬疑 惊悚 恐怖 犯罪 同性 音乐 歌舞 传记 历史 战争 西部 奇幻 冒险 灾难 武侠 情色";
if(styleStr.indexOf(tags)==-1){//如果上面 style类型字符串 没有 该字符串,就证明该字符串是个area
    flag=true;
}
            //如果flag为true的话，证明当前这个tag是个 类型(style),如果false的话 证明是地区
            if (flag) {
//                map.put("area", tags);
                saa.setArea(tags);

            } else {
//                map.put("style", tags);
                saa.setStyle(tags);


            }
//            log.info("单参后的map是:{}",map);
            return tagMapper.findByType(saa);
        }
//        log.info("无参后的map是:{}",map);
        return tagMapper.findByType(saa);
    }
}
/*
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
                boolean flag = false;
                Style obj = tagMapper.findStyleByName(tags);
                if (obj !=null) {
                    flag=true;
                }
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
*/


