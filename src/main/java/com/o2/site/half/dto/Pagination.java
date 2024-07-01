package com.o2.site.half.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Pagination {
    private final int currentPage;
    private final int pagesLength;
    private final int pageSize;
    private final int startPage;
    private final int endPage;
    private final int startElement;
    private final int endElement;
    private final int prevPage;
    private final int nextPage;
    private final boolean prev;
    private final boolean next;
    private final List<Integer> pages;

    public Pagination(int currentPage, int pagesLength, int pageSize) {
        this.currentPage = currentPage;
        this.pagesLength = pagesLength;
        this.pageSize = pageSize;
        this.startPage = ((currentPage - 1) / pageSize) * pageSize + 1; // 1, 11, 21, ...
        this.endPage = startPage + pageSize - 1; // 10, 20, 30, ...
        this.prevPage = startPage - 1;
        this.nextPage = endPage + 1;
        this.prev = startPage > 1;
        this.next = endPage < pagesLength;
        this.startElement = (currentPage - 1) * pageSize; // 0, 10, 20, ...
        this.endElement = currentPage * pageSize; // 10, 20, 30, ...
        this.pages = new ArrayList<>();
        for (int i = startPage; i <= endPage && i <= pagesLength; i++) {
            this.pages.add(i);
        }
    }
}
