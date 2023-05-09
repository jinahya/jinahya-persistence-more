package com.github.jinahya.persistence.more;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
class BitFaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @Convert(converter = BitFaceAttributeConverter.class)
    private BitFace bitFace;

    @Valid
    @Convert(converter = BitFaceAttributeConverter.OfLong.class)
    private BitFace.OfLong bitFaceOfLong;
}
