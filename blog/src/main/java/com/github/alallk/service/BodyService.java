package com.github.alallk.service;

import com.github.alallk.service.dto.BodyDTO;
import java.util.List;

/**
 * Service Interface for managing Body.
 */
public interface BodyService {

    /**
     * Save a body.
     *
     * @param bodyDTO the entity to save
     * @return the persisted entity
     */
    BodyDTO save(BodyDTO bodyDTO);

    /**
     * Get all the bodies.
     *
     * @return the list of entities
     */
    List<BodyDTO> findAll();

    /**
     * Get the "id" body.
     *
     * @param id the id of the entity
     * @return the entity
     */
    BodyDTO findOne(Long id);

    /**
     * Delete the "id" body.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the body corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<BodyDTO> search(String query);
}
