package com.sugang.app.domain.course;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCourseSchedule is a Querydsl query type for CourseSchedule
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCourseSchedule extends BeanPath<CourseSchedule> {

    private static final long serialVersionUID = -648854764L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCourseSchedule courseSchedule = new QCourseSchedule("courseSchedule");

    public final DateTimePath<java.time.LocalDateTime> end = createDateTime("end", java.time.LocalDateTime.class);

    public final QRoomNumber roomNumber;

    public final DateTimePath<java.time.LocalDateTime> start = createDateTime("start", java.time.LocalDateTime.class);

    public QCourseSchedule(String variable) {
        this(CourseSchedule.class, forVariable(variable), INITS);
    }

    public QCourseSchedule(Path<? extends CourseSchedule> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCourseSchedule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCourseSchedule(PathMetadata metadata, PathInits inits) {
        this(CourseSchedule.class, metadata, inits);
    }

    public QCourseSchedule(Class<? extends CourseSchedule> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.roomNumber = inits.isInitialized("roomNumber") ? new QRoomNumber(forProperty("roomNumber")) : null;
    }

}

