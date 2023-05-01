package com.sugang.app.domain.course.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CourseRepositoryCustomImpl implements CourseRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Autowired
    public CourseRepositoryCustomImpl(EntityManager entityManager)
    {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

}
