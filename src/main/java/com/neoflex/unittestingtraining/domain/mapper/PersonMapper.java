package com.neoflex.unittestingtraining.domain.mapper;

import com.neoflex.unittestingtraining.domain.dto.PersonDto;
import com.neoflex.unittestingtraining.domain.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(source = "name", target = "name")
    PersonDto toDto(Person person);
}
