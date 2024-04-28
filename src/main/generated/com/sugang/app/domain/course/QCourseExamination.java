package com.sugang.app.domain.course;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCourseExamination is a Querydsl query type for CourseExamination
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCourseExamination extends BeanPath<CourseExamination> {

    private static final long serialVersionUID = 1928623700L;

    public static final QCourseExamination courseExamination = new QCourseExamination("courseExamination");

    public final DateTimePath<java.time.LocalDateTime> finalTermDate = createDateTime("finalTermDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> midTermDate = createDateTime("midTermDate", java.time.LocalDateTime.class);

    public QCourseExamination(String variable) {
        super(CourseExamination.class, forVariable(variable));
    }

    public QCourseExamination(Path<? extends CourseExamination> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCourseExamination(PathMetadata metadata) {
        super(CourseExamination.class, metadata);
    }

}

