package com.sugang.app.domain.department;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDepartmentPhoneNumber is a Querydsl query type for DepartmentPhoneNumber
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QDepartmentPhoneNumber extends BeanPath<DepartmentPhoneNumber> {

    private static final long serialVersionUID = -413744742L;

    public static final QDepartmentPhoneNumber departmentPhoneNumber = new QDepartmentPhoneNumber("departmentPhoneNumber");

    public final StringPath faxNumber = createString("faxNumber");

    public final StringPath number = createString("number");

    public QDepartmentPhoneNumber(String variable) {
        super(DepartmentPhoneNumber.class, forVariable(variable));
    }

    public QDepartmentPhoneNumber(Path<? extends DepartmentPhoneNumber> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDepartmentPhoneNumber(PathMetadata metadata) {
        super(DepartmentPhoneNumber.class, metadata);
    }

}

