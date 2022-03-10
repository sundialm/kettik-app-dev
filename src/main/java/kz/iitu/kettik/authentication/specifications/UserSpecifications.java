package kz.iitu.kettik.authentication.specifications;

import kz.iitu.kettik.authentication.entities.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecifications
{
    public static Specification<User> isAuth(String email, String password)
    {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and
                                                (
                                                        equal(root, criteriaBuilder, "email", email),
                                                        equal(root, criteriaBuilder, "password", password),
                                                        equal(root, criteriaBuilder, "enable", true)
                                                );
    }

    private static Predicate equal(Root<?> root, CriteriaBuilder criteriaBuilder, String field, Object value)
    {
        return criteriaBuilder.equal(root.get(field), value);
    }
}
