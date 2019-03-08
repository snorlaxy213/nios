package com.springboot.commons;

import java.util.List;

public class PageUtils<T> {

    public static final Integer PAGE_SIZE = 5;

    private Long totalSize;
    private Integer totalPage;
    private Integer currentPage;
    private List<T> contents;

    public PageUtils(Long totalSize, Integer totalPage, Integer currentPage, List<T> contents) {
        this.totalSize = totalSize;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.contents = contents;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getContents() {
        return contents;
    }

    public void setContents(List<T> contents) {
        this.contents = contents;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
