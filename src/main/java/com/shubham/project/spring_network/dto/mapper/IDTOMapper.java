package com.shubham.project.spring_network.dto.mapper;

import org.springframework.context.annotation.Bean;

public interface IDTOMapper <T1, T2> {

    public T1 toEntity (T2 dto) throws Exception;

    public T2 toDTO (T1 entity) throws Exception;
}
