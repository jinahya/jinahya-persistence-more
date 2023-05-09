package com.github.jinahya.persistence.more;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
class BitMaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @Convert(converter = BitMaskAttributeConverter.class)
    private BitMask bitMask;

    @Valid
    @Convert(converter = BitMaskAttributeConverter.OfLong.class)
    private BitMask.OfLong bitMaskOfLong;

    @Valid
    @Convert(converter = BitMaskAttributeConverter.OfSet.class)
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private final Set<BitMask> bitMaskSet = new HashSet<>();

    @Valid
    @Convert(converter = BitMaskAttributeConverter.OfSet.OfLong.class)
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private final Set<BitMask.OfLong> bitMaskOfLongSet = new HashSet<>();
}
