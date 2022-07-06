package app.util;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public final class ModelSpecification implements Specification<Model> {

    private final ModelQueryParams params;

    public ModelSpecification(ModelQueryParams params) {
        this.params = params;
    }

    @Override
    public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (params.getValue() instanceof String || params.getValue() instanceof LocalDateTime) {
            return stringAdapter(root, query, criteriaBuilder, (String) params.getValue());
        }
        return null;
    }

    private Predicate stringAdapter(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, String value) {
        if (params.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get(params.getColumnName()), value);
        } else if (params.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(root.get(params.getColumnName()), value);
        } else if (params.getOperation().equalsIgnoreCase("=")) {
            return criteriaBuilder.equal(root.get(params.getColumnName()), value);
        } else if (params.getOperation().equalsIgnoreCase("like")) {
            if (root.get(params.getColumnName()).getJavaType() == String.class) {
                return criteriaBuilder.like(root.get(params.getColumnName()), "%" + value + "%");
            } else {
                return criteriaBuilder.equal(root.get(params.getColumnName()), value);
            }
        }
        return null;
    }

    private Predicate localDateAdapter(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, LocalDate value) {
        if (params.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get(params.getColumnName()), value);
        } else if (params.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(root.get(params.getColumnName()), value);
        } else if (params.getOperation().equalsIgnoreCase("=")) {
            return criteriaBuilder.equal(root.get(params.getColumnName()), value);
        } else if (params.getOperation().equalsIgnoreCase(":")) {
            if (root.get(params.getColumnName()).getJavaType() == LocalDate.class) {
                return criteriaBuilder.like(root.get(params.getColumnName()), "%" + value + "%");
            } else {
                return criteriaBuilder.equal(root.get(params.getColumnName()), value);
            }
        }
        return null;
    }

    private Predicate dateAdapter(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, Date value) {
        if (params.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get(params.getColumnName()), value);
        } else if (params.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(root.get(params.getColumnName()), value);
        } else if (params.getOperation().equalsIgnoreCase("=")) {
            return criteriaBuilder.equal(root.get(params.getColumnName()), value);
        } else if (params.getOperation().equalsIgnoreCase(":")) {
            if (root.get(params.getColumnName()).getJavaType() == Date.class) {
                return criteriaBuilder.like(root.get(params.getColumnName()), "%" + value + "%");
            } else {
                return criteriaBuilder.equal(root.get(params.getColumnName()), value);
            }
        }
        return null;
    }

}
