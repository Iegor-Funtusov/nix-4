package ua.com.alevel.repo;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.entity.AbstractEntity;
import ua.com.alevel.util.SpecificationUtil;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AbstractSpecificationProcess<E extends AbstractEntity> implements AbstractSpecification<E> {

    @Override
    public Specification<E> generateCriteriaPredicate(WebRequest request, Class<E> entityClass, Long orgId) {
        if (Objects.nonNull(orgId)) {
            return (Specification<E>) (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.addAll(SpecificationUtil.generateSpecificationPredicates(request, entityClass, root, criteriaBuilder));
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
        } else {
            return getDefaultSpecification(request, entityClass);
        }
    }

    @Override
    public Specification<E> generateCriteriaPredicate(WebRequest request, Long orgId) {
        return null;
    }

    private Specification<E> getDefaultSpecification(WebRequest request, Class<E> entityClass) {
        return (Specification<E>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = SpecificationUtil.generateSpecificationPredicates(request, entityClass, root, criteriaBuilder);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
