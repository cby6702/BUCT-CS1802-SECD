package com.cs1802.museum.service.impl;

import com.cs1802.museum.bean.*;
import com.cs1802.museum.mapper.CollectionMapper;
import com.cs1802.museum.mapper.CommentsMapper;
import com.cs1802.museum.mapper.ExhibitionMapper;
import com.cs1802.museum.mapper.MuseumMapper;
import com.cs1802.museum.service.MuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MuseumServiceImpl<T> implements MuseumService {
    @Autowired
    private MuseumMapper museumMapper;
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private ExhibitionMapper exhibitionMapper;
    @Autowired
    private CommentsMapper commentsMapper;
    /*
    业务：根据博物馆name查询博物馆表
    逻辑：1.根据传入name，查询museums表并返回对应的museums对象
     */
    @Override
    public Museum searchMuseum(String name) {
        return museumMapper.getMuseum(name);
    }

    /*
    业务：根据city查询博物馆表，并根据pageNo返回分页数据
    逻辑：1.将page对象的各个成员对象值填完之后返回page对象即可
     */
    @Override
    public Page<Museum> showPage(String city, int pageNo) {
        //1.建立Museum类型的Page对象
        Page<Museum> page = new Page<>();
        //2.设置每页显示的数据量和当前页码pageNo
        page.setPageSize(Page.PAGE_SIZE);
        page.setPageNo(pageNo);
        //3.设置page的总记录数pageTotalCount
        // 查询符合city字段的总记录数
        int count = museumMapper.museumCountByCity(city);
        page.setPageTotal(count);
        //4.设置page的总页数pageTotal
        if (count > 0){ //当符合的记录数大于0时
            // 总页数 = 总记录数count / 每页大小 （上取整）
            double pageTotal = Math.ceil((count * 1.0 / Page.PAGE_SIZE));
            page.setPageTotal(Integer.valueOf((int) pageTotal));
            //5.设置page的当前页数据item
            // 查询时后缀上limit begin,pageSize
            int begin = (pageNo - 1) * Page.PAGE_SIZE;
            // 查询museum表得到List<Museum>
            List<Museum> items = museumMapper.museumPageByCity(city, begin, Page.PAGE_SIZE);
            page.setItems(items);
        }
        return page;
    }

    @Override
    public Page<Museum> sortMuseum(String city, int sortItem, int sortKind, int pageNo) {
        Page<Museum> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(Page.PAGE_SIZE);
        int count = museumMapper.museumCountByCity(city);
        if (count > 0){
            page.setPageTotalCount(count);
            double pageTotal = Math.ceil((count * 1.0 / Page.PAGE_SIZE));
            page.setPageTotal(Integer.valueOf((int) pageTotal));
            int begin = (pageNo - 1) * Page.PAGE_SIZE;
            List<Museum> list = museumMapper.sort(city, sortItem, sortKind, begin, page.getPageSize());
            System.out.println("pageItem=" + list);
            page.setItems(list);
        }
        return page;
    }


    /*
    业务：根据博物馆id查询博物馆表
    逻辑：1.根据传入mid，查询museums表并返回对应的museums对象
     */
    @Override
    public Museum searchMuseum1(int mid) {
        return museumMapper.getMuseum1(mid);
    }
    /**
     *查询页搜索功能
     * @param searchKind
     * @param searchItem
     * @return
     */
    @Override
    public List<T> search(String searchKind, String searchItem) {
        List<T> list = new ArrayList<>();
        switch (searchKind){
            case "博物馆":
                list = (List<T>) museumMapper.searchByName(searchItem);
                break;
            case "展览":
                list = (List<T>) exhibitionMapper.searchByName(searchItem);
                break;
            case "藏品":
                list = (List<T>) collectionMapper.searchByName(searchItem);
                break;
            default:
                break;
        }
        return list;
    }

    /*
    业务：根据新上传的评分修改museums表中评分
    逻辑：1.根据新上传comments获得mid及新评分信息
         2.计算新的评分
         3.修改museums表中对应评分
     */
    @Override
    public boolean updateScore(Comments comment) {
        //1.获得comment中mid和评分信息
        int mid = comment.getMid();
        int exhibitionstar = comment.getExhibitionstar();
        int environmentstar = comment.getEnvironmentstar();
        int servicestar = comment.getServicestar();
        double general_star =comment.getGeneral_comment();
        //2.计算新的评分
        Museum museum = museumMapper.getMuseum1(mid);
        List<Comments> comments = commentsMapper.getComments(mid);
        int size = comments.size();
        double exhibitionscore = (museum.getExhibition_score()*(size-1)+exhibitionstar) / size;
        double environmentscore = (museum.getEnvironment_score()*(size-1)+environmentstar) / size;
        double servicescore = (museum.getService_score()*(size-1)+servicestar) / size;
        double generalscore = (museum.getGeneral_score()*(size-1)+general_star) / size;
        //3.更新museums表中对应评分
        return museumMapper.updateScore(mid,exhibitionscore,environmentscore,servicescore,generalscore);
    }

    /*
    业务：根据博物馆id查询博物馆表中对应评分并返回
    逻辑：1.根据传入mid，查询museums表并返回对应评分信息
     */
    @Override
    public Score getScore(int mid) {
        Museum museum = museumMapper.getMuseum1(mid);
        Score score = new Score();
        score.setExhibition_score(museum.getExhibition_score());
        score.setEnvironment_score(museum.getEnvironment_score());
        score.setService_score(museum.getService_score());
        score.setGeneral_score(museum.getGeneral_score());
        return  score;
    }

}
