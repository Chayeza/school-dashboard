package co.za.school.dashboard.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.za.school.dashboard.domain.Coarse;
import co.za.school.dashboard.repository.CoarseRepository;
import co.za.school.dashboard.web.rest.errors.BadRequestAlertException;
import co.za.school.dashboard.web.rest.util.HeaderUtil;
import co.za.school.dashboard.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Coarse.
 */
@RestController
@RequestMapping("/api")
public class CoarseResource {

    private final Logger log = LoggerFactory.getLogger(CoarseResource.class);

    private static final String ENTITY_NAME = "coarse";

    private final CoarseRepository coarseRepository;

    public CoarseResource(CoarseRepository coarseRepository) {
        this.coarseRepository = coarseRepository;
    }

    /**
     * POST  /coarses : Create a new coarse.
     *
     * @param coarse the coarse to create
     * @return the ResponseEntity with status 201 (Created) and with body the new coarse, or with status 400 (Bad Request) if the coarse has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/coarses")
    @Timed
    public ResponseEntity<Coarse> createCoarse(@Valid @RequestBody Coarse coarse) throws URISyntaxException {
        log.debug("REST request to save Coarse : {}", coarse);
        if (coarse.getId() != null) {
            throw new BadRequestAlertException("A new coarse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Coarse result = coarseRepository.save(coarse);
        return ResponseEntity.created(new URI("/api/coarses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /coarses : Updates an existing coarse.
     *
     * @param coarse the coarse to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated coarse,
     * or with status 400 (Bad Request) if the coarse is not valid,
     * or with status 500 (Internal Server Error) if the coarse couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/coarses")
    @Timed
    public ResponseEntity<Coarse> updateCoarse(@Valid @RequestBody Coarse coarse) throws URISyntaxException {
        log.debug("REST request to update Coarse : {}", coarse);
        if (coarse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Coarse result = coarseRepository.save(coarse);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, coarse.getId().toString()))
            .body(result);
    }

    /**
     * GET  /coarses : get all the coarses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of coarses in body
     */
    @GetMapping("/coarses")
    @Timed
    public ResponseEntity<List<Coarse>> getAllCoarses(Pageable pageable) {
        log.debug("REST request to get a page of Coarses");
        Page<Coarse> page = coarseRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/coarses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /coarses/:id : get the "id" coarse.
     *
     * @param id the id of the coarse to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the coarse, or with status 404 (Not Found)
     */
    @GetMapping("/coarses/{id}")
    @Timed
    public ResponseEntity<Coarse> getCoarse(@PathVariable Long id) {
        log.debug("REST request to get Coarse : {}", id);
        Optional<Coarse> coarse = coarseRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(coarse);
    }

    /**
     * DELETE  /coarses/:id : delete the "id" coarse.
     *
     * @param id the id of the coarse to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/coarses/{id}")
    @Timed
    public ResponseEntity<Void> deleteCoarse(@PathVariable Long id) {
        log.debug("REST request to delete Coarse : {}", id);

        coarseRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
