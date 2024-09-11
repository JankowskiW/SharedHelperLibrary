package com.wj.updatecenter.sharedhelperlibrary;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.List;


public class PaginationHelper {
    private final SortOrderConverter sortOrderConverter;

    public PaginationHelper(SortOrderConverter sortOrderConverter) {
        this.sortOrderConverter = sortOrderConverter;
    }

    public Pageable convertToPageable(int pageNumber, int pageSize, String sort) {
        pageNumber = adjustPageNumber(pageNumber);
        if (!StringUtils.hasText(sort)) {
            return PageRequest.of(pageNumber, pageSize);
        } else {
            List<Sort.Order> sortOrders = sortOrderConverter.convertToSortOrders(sort);
            return PageRequest.of(pageNumber, pageSize, Sort.by(sortOrders));
        }
    }

    private int adjustPageNumber(int pageNumber) {
        return pageNumber > 0 ? pageNumber - 1 : 0;
    }
}
