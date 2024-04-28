package com.sugang.app.domain.course;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSyllabus is a Querydsl query type for Syllabus
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QSyllabus extends BeanPath<Syllabus> {

    private static final long serialVersionUID = 465493671L;

    public static final QSyllabus syllabus = new QSyllabus("syllabus");

    public final SetPath<CourseSummary, QCourseSummary> courseSummaries = this.<CourseSummary, QCourseSummary>createSet("courseSummaries", CourseSummary.class, QCourseSummary.class, PathInits.DIRECT2);

    public QSyllabus(String variable) {
        super(Syllabus.class, forVariable(variable));
    }

    public QSyllabus(Path<? extends Syllabus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSyllabus(PathMetadata metadata) {
        super(Syllabus.class, metadata);
    }

}

