package com.wj.updatecenter.shared;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class SortOrderConverterTest {
    @InjectMocks
    private SortOrderConverter sortOrderConverter;

    @Test
    void shouldThrowExceptionWhenSortStringHasOddNumberOfParameters() {
        // given
        String sort = "id,asc,desc";

        // when && then
        assertThatThrownBy(() -> sortOrderConverter.convertToSortOrders(sort))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid sort parameters: " + sort);
    }

    @Test
    void shouldThrowExceptionWhenSortStringHasAnyParameterThatHasNotText() {
        // given
        String sort = "id, ";

        // when && then
        assertThatThrownBy(() -> sortOrderConverter.convertToSortOrders(sort))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid sort parameters: " + sort);
    }

    @Test
    void shouldReturnJustOneElementWhenGivenSortStringHasJustOneSortOrder() {
        // given
        String fieldName = "id";
        String sort = fieldName + ",asc";
        List<Sort.Order> expectedResult = List.of(new Sort.Order(Sort.Direction.ASC, fieldName));

        // when
        List<Sort.Order> result = sortOrderConverter.convertToSortOrders(sort);

        // then
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    @Test
    void shouldReturnMoreThanOneElementWhenGivenSortStringHasMoreThanOneSortOrder() {
        // given
        String fieldName1 = "id";
        String fieldName2 = "name";
        String sort = fieldName1 + ",asc," + fieldName2 + ",desc";
        Sort.Order expectedOrder1 = new Sort.Order(Sort.Direction.ASC, fieldName1);
        Sort.Order expectedOrder2 = new Sort.Order(Sort.Direction.DESC, fieldName2);
        List<Sort.Order> expectedResult = List.of(expectedOrder1, expectedOrder2);

        // when
        List<Sort.Order> result = sortOrderConverter.convertToSortOrders(sort);

        // then
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    @Test
    void shouldReturnEmptyListWhenSortStringIsNull() {
        // given && when
        List<Sort.Order> result = sortOrderConverter.convertToSortOrders(null);

        // then
        assertThat(result)
                .isEmpty();
    }

    @Test
    void shouldReturnEmptyListWhenSortStringIsEmpty() {
        // given && when
        List<Sort.Order> result = sortOrderConverter.convertToSortOrders("");

        // then
        assertThat(result)
                .isEmpty();
    }

    @Test
    void shouldReturnEmptyListWhenSortStringIsBlank() {
        // given && when
        List<Sort.Order> result = sortOrderConverter.convertToSortOrders("   ");

        // then
        assertThat(result)
                .isEmpty();
    }
}