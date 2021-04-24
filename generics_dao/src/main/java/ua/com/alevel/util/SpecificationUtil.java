package ua.com.alevel.util;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.controller.DateRangeModel;
import ua.com.alevel.entity.AbstractEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SpecificationUtil {

    public static List<Predicate> generateSpecificationPredicates(
            WebRequest request,
            Class<? extends AbstractEntity> entityClass,
            Root<? extends AbstractEntity> root,
            CriteriaBuilder criteriaBuilder) {
        String containsLikePattern;
        List<Predicate> predicates = new ArrayList<>();
        for (Field field : ClassLoaderUtil.getAllFieldsByEntity(entityClass)) {
            if (Modifier.isPrivate(field.getModifiers())) {
                String parameter = request.getParameter(field.getName());
                if (Objects.nonNull(parameter)) {
                    if (field.getType().isPrimitive()) {
                        if (field.getType().isAssignableFrom(boolean.class)) {
                            predicates.add(criteriaBuilder.equal(root.get(field.getName()), Boolean.parseBoolean(parameter)));
                        }
                    } else {
                        if (field.getType().isAssignableFrom(String.class)) {
                            containsLikePattern = StringPatternUtil.getContainsLikePattern(parameter);
                            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(field.getName())), containsLikePattern));
                        }
                        if (Enum.class.isAssignableFrom(field.getType())) {
                            predicates.add(criteriaBuilder.equal(root.get(field.getName()), Enum.valueOf((Class<Enum>) field.getType(), parameter)));
                        }
                        if (isInnerEntityField(field.getType())) {
                            List<Predicate> innerEntitiesPredicates = new ArrayList<>();
                            for (Field innerField : ClassLoaderUtil.getAllFieldsByEntity(field.getType())) {
                                if (Modifier.isPrivate(innerField.getModifiers())) {
                                    if (field.getType().isAssignableFrom(Objects.class)) {
                                        if (innerField.getType().isAssignableFrom(String.class)) {
                                            innerEntitiesPredicates.add(criteriaBuilder.equal(root.get(field.getName()).get(innerField.getName()), parameter));
                                        }
                                    } else {
                                        if (innerField.getType().isAssignableFrom(String.class)) {
                                            containsLikePattern = StringPatternUtil.getContainsLikePattern(parameter);
                                            innerEntitiesPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(field.getName()).get(innerField.getName())), containsLikePattern));
                                        }
                                    }
                                }
                            }
                            if (CollectionUtils.isNotEmpty(innerEntitiesPredicates)) {
                                predicates.add(criteriaBuilder.or(innerEntitiesPredicates.toArray(new Predicate[0])));
                            }
                        }
                        if (field.getType().isAssignableFrom(Date.class)) {
                            if (StringPatternUtil.dateRegExPattern(parameter)) {
                                DateRangeModel range = WebRequestUtil.generateDateRange(parameter);
                                predicates.add(criteriaBuilder.between(root.get(field.getName()), range.getStart(), range.getEnd()));
                            }
                        }
                        if (Number.class.isAssignableFrom(field.getType())) {
                            predicates.add(criteriaBuilder.equal(root.get(field.getName()), parameter));
                        }
                    }
                }
            }
        }
        return predicates;
    }

    private static boolean isInnerEntityField(Class<?> type) {
        return type.getSuperclass().isAssignableFrom(AbstractEntity.class);
    }
}
