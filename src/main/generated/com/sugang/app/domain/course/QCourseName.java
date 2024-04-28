package com.sugang.app.domain.course;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCourseName is a Querydsl query type for CourseName
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCourseName extends BeanPath<CourseName> {

    private static final long serialVersionUID = 459141512L;

    public static final QCourseName courseName = new QCourseName("courseName");

    public final StringPath value = createString("value");

    public QCourseName(String variable) {
        super(CourseName.class, forVariable(variable));
    }

    public QCourseName(Path<? extends CourseName> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCourseName(PathMetadata metadata) {
        super(CourseName.class, metadata);
    }

}

