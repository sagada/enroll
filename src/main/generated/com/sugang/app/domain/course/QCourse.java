package com.sugang.app.domain.course;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCourse is a Querydsl query type for Course
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCourse extends EntityPathBase<Course> {

    private static final long serialVersionUID = -1338050211L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCourse course = new QCourse("course");

    public final com.sugang.app.domain.QBaseEntity _super = new com.sugang.app.domain.QBaseEntity(this);

    public final NumberPath<Integer> availStudentCount = createNumber("availStudentCount", Integer.class);

    public final QCourseExamination courseExamination;

    public final QCourseName courseName;

    public final SetPath<CourseSchedule, QCourseSchedule> courseScheduleSet = this.<CourseSchedule, QCourseSchedule>createSet("courseScheduleSet", CourseSchedule.class, QCourseSchedule.class, PathInits.DIRECT2);

    public final EnumPath<CourseStatus> courseStatus = createEnum("courseStatus", CourseStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDateTime = _super.createdDateTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPrerequisiteCourse prerequisiteCourse;

    public final NumberPath<Long> professorId = createNumber("professorId", Long.class);

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public final NumberPath<Long> subjectId = createNumber("subjectId", Long.class);

    public final QSyllabus syllabus;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDateTime = _super.updatedDateTime;

    public QCourse(String variable) {
        this(Course.class, forVariable(variable), INITS);
    }

    public QCourse(Path<? extends Course> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCourse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCourse(PathMetadata metadata, PathInits inits) {
        this(Course.class, metadata, inits);
    }

    public QCourse(Class<? extends Course> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.courseExamination = inits.isInitialized("courseExamination") ? new QCourseExamination(forProperty("courseExamination")) : null;
        this.courseName = inits.isInitialized("courseName") ? new QCourseName(forProperty("courseName")) : null;
        this.prerequisiteCourse = inits.isInitialized("prerequisiteCourse") ? new QPrerequisiteCourse(forProperty("prerequisiteCourse")) : null;
        this.syllabus = inits.isInitialized("syllabus") ? new QSyllabus(forProperty("syllabus")) : null;
    }

}

