package com.gl.mybatisapirest.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface Converter<E, D> {
    E fromDTO(D dto);

    D fromModel(E model);

    default List<E> fromDTO(List<D> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }

        return dtos.stream().map(this::fromDTO).collect(Collectors.toList());
    }

    default List<D> fromModel(List<E> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }

        return dtos.stream().map(this::fromModel).collect(Collectors.toList());
    }
}
