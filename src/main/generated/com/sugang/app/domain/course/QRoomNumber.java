package com.sugang.app.domain.course;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRoomNumber is a Querydsl query type for RoomNumber
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QRoomNumber extends BeanPath<RoomNumber> {

    private static final long serialVersionUID = -1246650298L;

    public static final QRoomNumber roomNumber = new QRoomNumber("roomNumber");

    public final StringPath value = createString("value");

    public QRoomNumber(String variable) {
        super(RoomNumber.class, forVariable(variable));
    }

    public QRoomNumber(Path<? extends RoomNumber> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoomNumber(PathMetadata metadata) {
        super(RoomNumber.class, metadata);
    }

}

