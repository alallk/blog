package com.github.alallk.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Commentary entity.
 */
public class CommentaryDTO implements Serializable {

    private Long id;

    @NotNull
    private String text;

    private String authorId;

    private Long postId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommentaryDTO commentaryDTO = (CommentaryDTO) o;
        if(commentaryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), commentaryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CommentaryDTO{" +
            "id=" + getId() +
            ", text='" + getText() + "'" +
            "}";
    }
}
