package com.github.alallk.service.impl;

import com.github.alallk.service.CommentaryService;
import com.github.alallk.domain.Commentary;
import com.github.alallk.repository.CommentaryRepository;
import com.github.alallk.repository.search.CommentarySearchRepository;
import com.github.alallk.service.dto.CommentaryDTO;
import com.github.alallk.service.mapper.CommentaryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Commentary.
 */
@Service
@Transactional
public class CommentaryServiceImpl implements CommentaryService {

    private final Logger log = LoggerFactory.getLogger(CommentaryServiceImpl.class);

    private final CommentaryRepository commentaryRepository;

    private final CommentaryMapper commentaryMapper;

    private final CommentarySearchRepository commentarySearchRepository;

    public CommentaryServiceImpl(CommentaryRepository commentaryRepository, CommentaryMapper commentaryMapper, CommentarySearchRepository commentarySearchRepository) {
        this.commentaryRepository = commentaryRepository;
        this.commentaryMapper = commentaryMapper;
        this.commentarySearchRepository = commentarySearchRepository;
    }

    /**
     * Save a commentary.
     *
     * @param commentaryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CommentaryDTO save(CommentaryDTO commentaryDTO) {
        log.debug("Request to save Commentary : {}", commentaryDTO);
        Commentary commentary = commentaryMapper.toEntity(commentaryDTO);
        commentary = commentaryRepository.save(commentary);
        CommentaryDTO result = commentaryMapper.toDto(commentary);
        commentarySearchRepository.save(commentary);
        return result;
    }

    /**
     * Get all the commentaries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CommentaryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Commentaries");
        return commentaryRepository.findAll(pageable)
            .map(commentaryMapper::toDto);
    }

    /**
     * Get one commentary by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CommentaryDTO findOne(Long id) {
        log.debug("Request to get Commentary : {}", id);
        Commentary commentary = commentaryRepository.findOne(id);
        return commentaryMapper.toDto(commentary);
    }

    /**
     * Delete the commentary by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Commentary : {}", id);
        commentaryRepository.delete(id);
        commentarySearchRepository.delete(id);
    }

    /**
     * Search for the commentary corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CommentaryDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Commentaries for query {}", query);
        Page<Commentary> result = commentarySearchRepository.search(queryStringQuery(query), pageable);
        return result.map(commentaryMapper::toDto);
    }
}
