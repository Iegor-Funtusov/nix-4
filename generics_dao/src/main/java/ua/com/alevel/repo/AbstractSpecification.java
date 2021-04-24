package ua.com.alevel.repo;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.entity.AbstractEntity;

public interface AbstractSpecification<E extends AbstractEntity> {

    Specification<E> generateCriteriaPredicate(WebRequest request, Class<E> entityClass, Long orgId);
    Specification<E> generateCriteriaPredicate(WebRequest request, Long orgId);
}
