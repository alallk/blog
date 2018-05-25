package com.github.alallk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Post.
 */
@Entity
@Table(name = "post")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "post")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @NotNull
    @Column(name = "publicated_at", nullable = false)
    private ZonedDateTime publicatedAt;

    @Column(name = "removed")
    private Boolean removed;

    @OneToOne
    @JoinColumn(unique = true)
    private Body body;

    @OneToOne
    @JoinColumn(unique = true)
    private Asset cover;

    @NotNull
    @Column(name = "author_id", nullable = false)
    private String authorId;

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Commentary> commentaries = new HashSet<>();

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Category> categories = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Post title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public Post createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getPublicatedAt() {
        return publicatedAt;
    }

    public Post publicatedAt(ZonedDateTime publicatedAt) {
        this.publicatedAt = publicatedAt;
        return this;
    }

    public void setPublicatedAt(ZonedDateTime publicatedAt) {
        this.publicatedAt = publicatedAt;
    }

    public Boolean isRemoved() {
        return removed;
    }

    public Post removed(Boolean removed) {
        this.removed = removed;
        return this;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    public Body getBody() {
        return body;
    }

    public Post body(Body body) {
        this.body = body;
        return this;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Asset getCover() {
        return cover;
    }

    public Post cover(Asset asset) {
        this.cover = asset;
        return this;
    }

    public void setCover(Asset asset) {
        this.cover = asset;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Set<Commentary> getCommentaries() {
        return commentaries;
    }

    public Post commentaries(Set<Commentary> commentaries) {
        this.commentaries = commentaries;
        return this;
    }

    public Post addCommentaries(Commentary commentary) {
        this.commentaries.add(commentary);
        commentary.setPost(this);
        return this;
    }

    public Post removeCommentaries(Commentary commentary) {
        this.commentaries.remove(commentary);
        commentary.setPost(null);
        return this;
    }

    public void setCommentaries(Set<Commentary> commentaries) {
        this.commentaries = commentaries;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Post categories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public Post addCategories(Category category) {
        this.categories.add(category);
        category.setPost(this);
        return this;
    }

    public Post removeCategories(Category category) {
        this.categories.remove(category);
        category.setPost(null);
        return this;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        if (post.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), post.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Post{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", publicatedAt='" + getPublicatedAt() + "'" +
            ", removed='" + isRemoved() + "'" +
            "}";
    }
}
