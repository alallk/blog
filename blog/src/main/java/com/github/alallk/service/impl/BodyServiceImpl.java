package com.github.alallk.service.impl;

import com.github.alallk.service.BodyService;
import com.github.alallk.domain.Body;
import com.github.alallk.repository.BodyRepository;
import com.github.alallk.repository.search.BodySearchRepository;
import com.github.alallk.service.dto.BodyDTO;
import com.github.alallk.service.mapper.BodyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Body.
 */
@Service
@Transactional
public class BodyServiceImpl implements BodyService {

    private final Logger log = LoggerFactory.getLogger(BodyServiceImpl.class);

    private final BodyRepository bodyRepository;

    private final BodyMapper bodyMapper;

    private final BodySearchRepository bodySearchRepository;

    public BodyServiceImpl(BodyRepository bodyRepository, BodyMapper bodyMapper, BodySearchRepository bodySearchRepository) {
        this.bodyRepository = bodyRepository;
        this.bodyMapper = bodyMapper;
        this.bodySearchRepository = bodySearchRepository;
    }

    /**
     * Save a body.
     *
     * @param bodyDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BodyDTO save(BodyDTO bodyDTO) {
        log.debug("Request to save Body : {}", bodyDTO);
        Body body = bodyMapper.toEntity(bodyDTO);
        body = bodyRepository.save(body);
        BodyDTO result = bodyMapper.toDto(body);
        bodySearchRepository.save(body);
        return result;
    }

    /**
     * Get all the bodies.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BodyDTO> findAll() {
        log.debug("Request to get all Bodies");
        return bodyRepository.findAll().stream()
            .map(bodyMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one body by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BodyDTO findOne(Long id) {
        log.debug("Request to get Body : {}", id);
        Body body = bodyRepository.findOne(id);
        return bodyMapper.toDto(body);
    }

    /**
     * Delete the body by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Body : {}", id);
        bodyRepository.delete(id);
        bodySearchRepository.delete(id);
    }

    /**
     * Search for the body corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BodyDTO> search(String query) {
        log.debug("Request to search Bodies for query {}", query);
        return StreamSupport
            .stream(bodySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(bodyMapper::toDto)
            .collect(Collectors.toList());
    }
}
