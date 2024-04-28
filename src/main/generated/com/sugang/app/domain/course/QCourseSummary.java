package com.sugang.app.domain.course;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCourseSummary is a Querydsl query type for CourseSummary
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCourseSummary extends BeanPath<CourseSummary> {

    private static final long serialVersionUID = -470584439L;

    public static final QCourseSummary courseSummary = new QCourseSummary("courseSummary");

    public final StringPath content = createString("content");

    public final StringPath title = createString("title");

    public final NumberPath<Integer> week = createNumber("week", Integer.class);

    public QCourseSummary(String variable) {
        super(CourseSummary.class, forVariable(variable));
    }

    public QCourseSummary(Path<? extends CourseSummary> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCourseSummary(PathMetadata metadata) {
        super(CourseSummary.class, metadata);
    }

}

