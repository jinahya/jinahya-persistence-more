package com.github.jinahya.persistence.more;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "bit_face")
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
    @Column(name = "bit_face", nullable = true)
    private BitFace bitFace;

    @Valid
    @Convert(converter = BitFaceAttributeConverter.OfLong.class)
    @Column(name = "bit_face_of_long", nullable = true)
    private BitFace.OfLong bitFaceOfLong;
}
