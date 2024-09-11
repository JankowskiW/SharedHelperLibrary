package com.wj.updatecenter.shared;

import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortOrderConverter {
    public List<Sort.Order> convertToSortOrders(String sort) {
        if(!StringUtils.hasText(sort)) {
            return new ArrayList<>();
        }
        String[] sortParameters = sort.split(",");
        if (Arrays.stream(sortParameters)
                .anyMatch(parameter -> !StringUtils.hasText(parameter)) ||
                sortParameters.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid sort parameters: " + sort);
        }
        List<Sort.Order> sortOrders = new ArrayList<>();
        for (int i = 0; i < sortParameters.length; i = i + 2) {
            String fieldName = sortParameters[i].trim();
            String directionName = sortParameters[i + 1].trim().toLowerCase();
            Sort.Direction direction = Sort.Direction.fromString(directionName);
            sortOrders.add(new Sort.Order(direction, fieldName));
        }
        return sortOrders;
    }
}
