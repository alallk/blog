package com.github.alallk.service.mapper;

import com.github.alallk.domain.*;
import com.github.alallk.service.dto.CommentaryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Commentary and its DTO CommentaryDTO.
 */
@Mapper(componentModel = "spring", uses = {PostMapper.class})
public interface CommentaryMapper extends EntityMapper<CommentaryDTO, Commentary> {

    @Mapping(source = "authorId", target = "authorId")
    @Mapping(source = "post.id", target = "postId")
    CommentaryDTO toDto(Commentary commentary);

    @Mapping(source = "authorId", target = "authorId")
    @Mapping(source = "postId", target = "post")
    Commentary toEntity(CommentaryDTO commentaryDTO);

    default Commentary fromId(Long id) {
        if (id == null) {
            return null;
        }
        Commentary commentary = new Commentary();
        commentary.setId(id);
        return commentary;
    }
}
