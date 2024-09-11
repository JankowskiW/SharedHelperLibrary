package com.wj.updatecenter.shared;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserManagementPaginationHelperTest {
    @Mock
    private SortOrderConverter sortOrderConverter;
    @InjectMocks
    private PaginationHelper userManagementPaginationHelper;

    @Test
    void shouldReturnPageableWithPageNumber0WhenGivenPageNumberIs0() {
        // given
        int pageNumber = 0;
        int pageSize = 10;
        int expectedPageNumber = 0;

        // when
        Pageable result = userManagementPaginationHelper.convertToPageable(pageNumber, pageSize, null);

        // then
        assertThat(result.getPageNumber())
                .isEqualTo(expectedPageNumber);
    }

    @Test
    void shouldReturnPageableWithPageNumber0WhenGivenPageNumberIsLessThan0() {
        // given
        int pageNumber = -1;
        int pageSize = 10;
        int expectedPageNumber = 0;

        // when
        Pageable result = userManagementPaginationHelper.convertToPageable(pageNumber, pageSize, null);

        // then
        assertThat(result.getPageNumber())
                .isEqualTo(expectedPageNumber);
    }

    @Test
    void shouldReturnPageableWithPageNumber0WhenGivenPageNumberIs1() {
        // given
        int pageNumber = 1;
        int pageSize = 10;
        int expectedPageNumber = 0;

        // when
        Pageable result = userManagementPaginationHelper.convertToPageable(pageNumber, pageSize, null);

        // then
        assertThat(result.getPageNumber())
                .isEqualTo(expectedPageNumber);
    }

    @Test
    void shouldReturnPageableWithPageNumberOneLessThanGivenOneWhenGivenPageNumberIsGreaterThan1() {
        // given
        int pageNumber = 100;
        int pageSize = 10;
        int expectedPageNumber = 99;

        // when
        Pageable result = userManagementPaginationHelper.convertToPageable(pageNumber, pageSize, null);

        // then
        assertThat(result.getPageNumber())
                .isEqualTo(expectedPageNumber);
    }

    @Test
    void shouldReturnPageableWithUnsortedParameterWhenGivenSortStringIsNull() {
        // given
        int pageNumber = 0;
        int pageSize = 10;

        // when
        Pageable result = userManagementPaginationHelper.convertToPageable(pageNumber, pageSize, null);

        // then
        assertThat(result.getSort())
                .isEqualTo(Sort.unsorted());
    }

    @Test
    void shouldReturnPageableWithUnsortedParameterWhenGivenSortStringIsEmpty() {
        // given
        int pageNumber = 0;
        int pageSize = 10;

        // when
        Pageable result = userManagementPaginationHelper.convertToPageable(pageNumber, pageSize, "");

        // then
        assertThat(result.getSort())
                .isEqualTo(Sort.unsorted());
    }

    @Test
    void shouldReturnPageableWithUnsortedParameterWhenGivenSortStringIsBlank() {
        // given
        int pageNumber = 0;
        int pageSize = 10;

        // when
        Pageable result = userManagementPaginationHelper.convertToPageable(pageNumber, pageSize, "    ");

        // then
        assertThat(result.getSort())
                .isEqualTo(Sort.unsorted());
    }

    @Test
    void shouldReturnPageableWithSortedParameterWhenGivenSortStringContainsValidValue() {
        // given
        int pageNumber = 0;
        int pageSize = 10;
        String fieldName = "id";
        String sort = fieldName + ",desc";
        Sort expectedSort = Sort.by(Sort.Order.desc(fieldName));
        Sort.Order order = Sort.Order.desc(fieldName);
        given(sortOrderConverter.convertToSortOrders(anyString())).willReturn(List.of(order));

        // when
        Pageable result = userManagementPaginationHelper.convertToPageable(pageNumber, pageSize, sort);

        //then
        assertThat(result.getSort())
                .isEqualTo(expectedSort);
    }

}