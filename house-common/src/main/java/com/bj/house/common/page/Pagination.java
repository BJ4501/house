package com.bj.house.common.page;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 手动解决前端分页计算问题，直接返回分页详细信息
 * Created by BJ on 2018/1/23.
 */
public class Pagination {

    private int pageNum;

    private int pageSize;

    private long totalCount;

    private List<Integer> pages = Lists.newArrayList();

    public Pagination(Integer pageSize, Integer pageNum, Long totalCount){
        this.totalCount = totalCount;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        for(int i = 1; i <= pageNum; i++){
            pages.add(i);
        }
        Long pageCount = totalCount/pageSize + ((totalCount % pageSize == 0)? 0 : 1);
        if (pageCount > pageNum){
            for(int i = pageNum + 1;i < pageCount ; i++){
                pages.add(i);
            }
        }
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }
}
