package com.cs1802.museum.service.impl;

import com.cs1802.museum.bean.News;
import com.cs1802.museum.bean.Page;
import com.cs1802.museum.mapper.NewsMapper;
import com.cs1802.museum.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;
    /*
    业务：根据博物馆id查询新闻表
    逻辑：1.根据传入mid，查询news表并返回对应news对象
     */
    @Override
    public List<News> searchNews(int mid) {
        return newsMapper.getNews(mid);
    }

    /**
     * 新闻页，分页显示新闻 没有排序
     * @param pageNo
     * @return
     */
    @Override
    public Page<News> showPage(int pageNo) {
        Page<News> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(Page.PAGE_SIZE);
        int count = newsMapper.getCount();
        if (count > 0){
            page.setPageTotalCount(count);
            double pageTotal = Math.ceil(1.0 *count / Page.PAGE_SIZE);
            page.setPageTotal((int) pageTotal);

            int begin = (pageNo-1) * Page.PAGE_SIZE;
            System.out.println("begin=" + begin);
            List<News> items = newsMapper.getItems(begin, page.getPageSize());
            page.setItems(items);
        }
        return page;
    }
}
