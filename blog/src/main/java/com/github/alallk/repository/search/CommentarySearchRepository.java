package com.github.alallk.repository.search;

import com.github.alallk.domain.Commentary;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Commentary entity.
 */
public interface CommentarySearchRepository extends ElasticsearchRepository<Commentary, Long> {
}
