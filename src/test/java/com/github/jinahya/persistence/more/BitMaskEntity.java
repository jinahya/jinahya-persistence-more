package com.github.jinahya.persistence.more;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "bit_mask")
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
    @Column(name = "bit_mask", nullable = true)
    private BitMask bitMask;

    @Valid
    @Convert(converter = BitMaskAttributeConverter.OfLong.class)
    @Column(name = "bit_mask_of_long", nullable = true)
    private BitMask.OfLong bitMaskOfLong;

    @Valid
    @NotNull
    @Convert(converter = BitMaskAttributeConverter.OfSet.class)
    @Column(name = "bit_mask_set", nullable = false)
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private final Set<BitMask> bitMaskSet = new HashSet<>();

    @Valid
    @NotNull
    @Convert(converter = BitMaskAttributeConverter.OfSet.OfLong.class)
    @Column(name = "bit_mask_of_long_set", nullable = false)
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private final Set<BitMask.OfLong> bitMaskOfLongSet = new HashSet<>();
}
