package com.test.demo.common.dto;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageUtils {

    public static  Page getConvertPage(Integer pageNum, Integer pageSize, List list, PageInfo pageInfo) {
        Page page = new Page();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setPageList(list);
        Long total = pageInfo.getTotal();
        page.setTotalRows(total.intValue());
        //总页数
        Integer  totalPageNum =pageInfo.getPages();
        page.setTotalPageNum(totalPageNum);
        //返回后当前页
        page.setPageCurrentNum(page.getPageNum()+1);
        return page;
    }
}
