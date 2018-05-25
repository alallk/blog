package com.github.alallk.service.mapper;

import com.github.alallk.domain.*;
import com.github.alallk.service.dto.BodyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Body and its DTO BodyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BodyMapper extends EntityMapper<BodyDTO, Body> {



    default Body fromId(Long id) {
        if (id == null) {
            return null;
        }
        Body body = new Body();
        body.setId(id);
        return body;
    }
}
