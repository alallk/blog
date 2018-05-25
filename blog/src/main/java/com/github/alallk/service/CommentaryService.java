package com.github.alallk.service;

import com.github.alallk.service.dto.CommentaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Commentary.
 */
public interface CommentaryService {

    /**
     * Save a commentary.
     *
     * @param commentaryDTO the entity to save
     * @return the persisted entity
     */
    CommentaryDTO save(CommentaryDTO commentaryDTO);

    /**
     * Get all the commentaries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CommentaryDTO> findAll(Pageable pageable);

    /**
     * Get the "id" commentary.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CommentaryDTO findOne(Long id);

    /**
     * Delete the "id" commentary.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the commentary corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CommentaryDTO> search(String query, Pageable pageable);
}
