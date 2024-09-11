package com.wj.updatecenter.sharedhelperlibrary;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public abstract class CommonSpecificationBuilder<T> {
    public Specification<T> build(String columnName, String value) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(value)) {
                return criteriaBuilder.equal(root.get(columnName), value);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }
}
