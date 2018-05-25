package com.github.alallk.service.mapper;

import com.github.alallk.domain.*;
import com.github.alallk.service.dto.PostDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Post and its DTO PostDTO.
 */
@Mapper(componentModel = "spring", uses = {BodyMapper.class, AssetMapper.class})
public interface PostMapper extends EntityMapper<PostDTO, Post> {

    @Mapping(source = "body.id", target = "bodyId")
    @Mapping(source = "cover.id", target = "coverId")
    @Mapping(source = "authorId", target = "authorId")
    PostDTO toDto(Post post);

    @Mapping(source = "bodyId", target = "body")
    @Mapping(source = "coverId", target = "cover")
    @Mapping(source = "authorId", target = "authorId")
    @Mapping(target = "commentaries", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Post toEntity(PostDTO postDTO);

    default Post fromId(Long id) {
        if (id == null) {
            return null;
        }
        Post post = new Post();
        post.setId(id);
        return post;
    }
}
