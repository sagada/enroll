package com.sugang.app.domain.subject;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSubjectInfo is a Querydsl query type for SubjectInfo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QSubjectInfo extends BeanPath<SubjectInfo> {

    private static final long serialVersionUID = 28362439L;

    public static final QSubjectInfo subjectInfo = new QSubjectInfo("subjectInfo");

    public final StringPath bookName = createString("bookName");

    public final StringPath introduction = createString("introduction");

    public QSubjectInfo(String variable) {
        super(SubjectInfo.class, forVariable(variable));
    }

    public QSubjectInfo(Path<? extends SubjectInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSubjectInfo(PathMetadata metadata) {
        super(SubjectInfo.class, metadata);
    }

}

