package com.github.alallk.service;

import com.github.alallk.service.dto.AssetDTO;
import java.util.List;

/**
 * Service Interface for managing Asset.
 */
public interface AssetService {

    /**
     * Save a asset.
     *
     * @param assetDTO the entity to save
     * @return the persisted entity
     */
    AssetDTO save(AssetDTO assetDTO);

    /**
     * Get all the assets.
     *
     * @return the list of entities
     */
    List<AssetDTO> findAll();

    /**
     * Get the "id" asset.
     *
     * @param id the id of the entity
     * @return the entity
     */
    AssetDTO findOne(Long id);

    /**
     * Delete the "id" asset.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the asset corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<AssetDTO> search(String query);
}
