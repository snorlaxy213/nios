package com.springboot.commons;

import java.util.List;

public class PageUtils {

    public static final Integer PAGE_SIZE = 5;

    private Integer totalSize;
    private Integer currentPage;
    private List<Object> contents;

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<Object> getContents() {
        return contents;
    }

    public void setContents(List<Object> contents) {
        this.contents = contents;
    }
}
