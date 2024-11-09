package com.wj.updatecenter.shared;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class CommonSpecificationBuilder<T> {
    /**
     * @deprecated This method is scheduled for deletion in future releases. 
     *             Use {@link #eq(String, String)} instead.
     */
    @Deprecated
    public Specification<T> build(String columnName, String value) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(value)) {
                return criteriaBuilder.equal(root.get(columnName), value);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public Specification<T> eq(String columnName, String value) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(value)) {
                return criteriaBuilder.equal(root.get(columnName), value);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public Specification<T> neq(String columnName, String value) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(value)) {
                return criteriaBuilder.notEqual(root.get(columnName), value);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public Specification<T> like(String columnName, String value) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(value)) {
                return criteriaBuilder.like(root.get(columnName), value);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public Specification<T> notLike(String columnName, String value) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(value)) {
                return criteriaBuilder.notLike(root.get(columnName), value);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public Specification<T> eq(String columnName, Object value) {
        return (root, query, criteriaBuilder) -> {
            if (value != null) {
                return criteriaBuilder.equal(root.get(columnName), value);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public Specification<T> neq(String columnName, Object value) {
        return (root, query, criteriaBuilder) -> {
            if (value != null) {
                return criteriaBuilder.equal(root.get(columnName), value);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public <Y extends Comparable<? super Y>> Specification<T> gt(String columnName, Y value) {
        return (root, query, criteriaBuilder) -> {
            if (value != null) {
                try {
                    return criteriaBuilder.greaterThan(root.get(columnName), value);
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException("Cannot compare type " + value.getClass());
                }
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public <Y extends Comparable<? super Y>> Specification<T> gte(String columnName, Y value) {
        return (root, query, criteriaBuilder) -> {
            if (value != null) {
                try {
                    return criteriaBuilder.greaterThanOrEqualTo(root.get(columnName), value);
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException("Cannot compare type " + value.getClass());
                }
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public <Y extends Comparable<? super Y>> Specification<T> lt(String columnName, Y value) {
        return (root, query, criteriaBuilder) -> {
            if (value != null) {
                try {
                    return criteriaBuilder.lessThan(root.get(columnName), value);
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException("Cannot compare type " + value.getClass());
                }
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public <Y extends Comparable<? super Y>> Specification<T> lte(String columnName, Y value) {
        return (root, query, criteriaBuilder) -> {
            if (value != null) {
                try {
                    return criteriaBuilder.lessThanOrEqualTo(root.get(columnName), value);
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException("Cannot compare type " + value.getClass());
                }
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

}
