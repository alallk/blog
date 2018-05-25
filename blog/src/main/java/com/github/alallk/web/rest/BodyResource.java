package com.github.alallk.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.github.alallk.service.BodyService;
import com.github.alallk.web.rest.errors.BadRequestAlertException;
import com.github.alallk.web.rest.util.HeaderUtil;
import com.github.alallk.service.dto.BodyDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Body.
 */
@RestController
@RequestMapping("/api")
public class BodyResource {

    private final Logger log = LoggerFactory.getLogger(BodyResource.class);

    private static final String ENTITY_NAME = "body";

    private final BodyService bodyService;

    public BodyResource(BodyService bodyService) {
        this.bodyService = bodyService;
    }

    /**
     * POST  /bodies : Create a new body.
     *
     * @param bodyDTO the bodyDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bodyDTO, or with status 400 (Bad Request) if the body has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bodies")
    @Timed
    public ResponseEntity<BodyDTO> createBody(@Valid @RequestBody BodyDTO bodyDTO) throws URISyntaxException {
        log.debug("REST request to save Body : {}", bodyDTO);
        if (bodyDTO.getId() != null) {
            throw new BadRequestAlertException("A new body cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BodyDTO result = bodyService.save(bodyDTO);
        return ResponseEntity.created(new URI("/api/bodies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bodies : Updates an existing body.
     *
     * @param bodyDTO the bodyDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bodyDTO,
     * or with status 400 (Bad Request) if the bodyDTO is not valid,
     * or with status 500 (Internal Server Error) if the bodyDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bodies")
    @Timed
    public ResponseEntity<BodyDTO> updateBody(@Valid @RequestBody BodyDTO bodyDTO) throws URISyntaxException {
        log.debug("REST request to update Body : {}", bodyDTO);
        if (bodyDTO.getId() == null) {
            return createBody(bodyDTO);
        }
        BodyDTO result = bodyService.save(bodyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bodyDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bodies : get all the bodies.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bodies in body
     */
    @GetMapping("/bodies")
    @Timed
    public List<BodyDTO> getAllBodies() {
        log.debug("REST request to get all Bodies");
        return bodyService.findAll();
        }

    /**
     * GET  /bodies/:id : get the "id" body.
     *
     * @param id the id of the bodyDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bodyDTO, or with status 404 (Not Found)
     */
    @GetMapping("/bodies/{id}")
    @Timed
    public ResponseEntity<BodyDTO> getBody(@PathVariable Long id) {
        log.debug("REST request to get Body : {}", id);
        BodyDTO bodyDTO = bodyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bodyDTO));
    }

    /**
     * DELETE  /bodies/:id : delete the "id" body.
     *
     * @param id the id of the bodyDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bodies/{id}")
    @Timed
    public ResponseEntity<Void> deleteBody(@PathVariable Long id) {
        log.debug("REST request to delete Body : {}", id);
        bodyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/bodies?query=:query : search for the body corresponding
     * to the query.
     *
     * @param query the query of the body search
     * @return the result of the search
     */
    @GetMapping("/_search/bodies")
    @Timed
    public List<BodyDTO> searchBodies(@RequestParam String query) {
        log.debug("REST request to search Bodies for query {}", query);
        return bodyService.search(query);
    }

}
