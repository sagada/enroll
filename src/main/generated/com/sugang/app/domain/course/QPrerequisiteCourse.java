package com.sugang.app.domain.course;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrerequisiteCourse is a Querydsl query type for PrerequisiteCourse
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPrerequisiteCourse extends BeanPath<PrerequisiteCourse> {

    private static final long serialVersionUID = -1895230285L;

    public static final QPrerequisiteCourse prerequisiteCourse = new QPrerequisiteCourse("prerequisiteCourse");

    public final SetPath<Long, NumberPath<Long>> preCourseSeqList = this.<Long, NumberPath<Long>>createSet("preCourseSeqList", Long.class, NumberPath.class, PathInits.DIRECT2);

    public QPrerequisiteCourse(String variable) {
        super(PrerequisiteCourse.class, forVariable(variable));
    }

    public QPrerequisiteCourse(Path<? extends PrerequisiteCourse> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPrerequisiteCourse(PathMetadata metadata) {
        super(PrerequisiteCourse.class, metadata);
    }

}

