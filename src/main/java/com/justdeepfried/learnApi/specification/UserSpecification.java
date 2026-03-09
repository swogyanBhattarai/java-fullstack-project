package com.justdeepfried.learnApi.specification;

import com.justdeepfried.learnApi.model.UserModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {
    public static Specification<UserModel> getSpecification(String search) {
        return new Specification<UserModel>() {
            @Override
            public @Nullable Predicate toPredicate(Root<UserModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (search != null) {
                    list.add(criteriaBuilder.like(root.get("username"), "%"+search.toLowerCase()+"%"));
                }
                return criteriaBuilder.or(list.toArray(new Predicate[0]));
            }
        };
    }
}
