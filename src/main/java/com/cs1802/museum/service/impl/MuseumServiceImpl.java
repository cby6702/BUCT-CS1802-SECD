package com.cs1802.museum.service.impl;

import com.cs1802.museum.bean.Museum;
import com.cs1802.museum.bean.Page;
import com.cs1802.museum.mapper.MuseumMapper;
import com.cs1802.museum.service.MuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MuseumServiceImpl implements MuseumService {
    @Autowired
    private MuseumMapper museumMapper;
    /*
    业务：根据博物馆id查询博物馆表
    逻辑：1.根据传入mid，查询museum表对应的所有信息并返回
     */
    @Override
    public Museum searchMuseum(int mid) {
        return museumMapper.getMuseum(mid);
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
}
