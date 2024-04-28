package com.sugang.app.domain.enroll;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExaminationScore is a Querydsl query type for ExaminationScore
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QExaminationScore extends BeanPath<ExaminationScore> {

    private static final long serialVersionUID = 213798584L;

    public static final QExaminationScore examinationScore = new QExaminationScore("examinationScore");

    public final StringPath finalExtraCredit = createString("finalExtraCredit");

    public final NumberPath<Double> finalScore = createNumber("finalScore", Double.class);

    public final NumberPath<Double> finalTotalScore = createNumber("finalTotalScore", Double.class);

    public final StringPath midExtraCredit = createString("midExtraCredit");

    public final NumberPath<Double> midtermScore = createNumber("midtermScore", Double.class);

    public final NumberPath<Double> midTermTotalScore = createNumber("midTermTotalScore", Double.class);

    public QExaminationScore(String variable) {
        super(ExaminationScore.class, forVariable(variable));
    }

    public QExaminationScore(Path<? extends ExaminationScore> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExaminationScore(PathMetadata metadata) {
        super(ExaminationScore.class, metadata);
    }

}

