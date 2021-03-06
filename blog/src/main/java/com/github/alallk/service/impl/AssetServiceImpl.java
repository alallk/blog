package com.github.alallk.service.impl;

import com.github.alallk.service.AssetService;
import com.github.alallk.domain.Asset;
import com.github.alallk.repository.AssetRepository;
import com.github.alallk.repository.search.AssetSearchRepository;
import com.github.alallk.service.dto.AssetDTO;
import com.github.alallk.service.mapper.AssetMapper;
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
 * Service Implementation for managing Asset.
 */
@Service
@Transactional
public class AssetServiceImpl implements AssetService {

    private final Logger log = LoggerFactory.getLogger(AssetServiceImpl.class);

    private final AssetRepository assetRepository;

    private final AssetMapper assetMapper;

    private final AssetSearchRepository assetSearchRepository;

    public AssetServiceImpl(AssetRepository assetRepository, AssetMapper assetMapper, AssetSearchRepository assetSearchRepository) {
        this.assetRepository = assetRepository;
        this.assetMapper = assetMapper;
        this.assetSearchRepository = assetSearchRepository;
    }

    /**
     * Save a asset.
     *
     * @param assetDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AssetDTO save(AssetDTO assetDTO) {
        log.debug("Request to save Asset : {}", assetDTO);
        Asset asset = assetMapper.toEntity(assetDTO);
        asset = assetRepository.save(asset);
        AssetDTO result = assetMapper.toDto(asset);
        assetSearchRepository.save(asset);
        return result;
    }

    /**
     * Get all the assets.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AssetDTO> findAll() {
        log.debug("Request to get all Assets");
        return assetRepository.findAll().stream()
            .map(assetMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one asset by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AssetDTO findOne(Long id) {
        log.debug("Request to get Asset : {}", id);
        Asset asset = assetRepository.findOne(id);
        return assetMapper.toDto(asset);
    }

    /**
     * Delete the asset by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Asset : {}", id);
        assetRepository.delete(id);
        assetSearchRepository.delete(id);
    }

    /**
     * Search for the asset corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AssetDTO> search(String query) {
        log.debug("Request to search Assets for query {}", query);
        return StreamSupport
            .stream(assetSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(assetMapper::toDto)
            .collect(Collectors.toList());
    }
}
