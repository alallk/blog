package com.github.alallk.repository.search;

import com.github.alallk.domain.Body;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Body entity.
 */
public interface BodySearchRepository extends ElasticsearchRepository<Body, Long> {
}
