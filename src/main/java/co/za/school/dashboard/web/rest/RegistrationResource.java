package co.za.school.dashboard.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.za.school.dashboard.domain.Registration;
import co.za.school.dashboard.service.RegistrationService;
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
 * REST controller for managing Registration.
 */
@RestController
@RequestMapping("/api")
public class RegistrationResource {

    private final Logger log = LoggerFactory.getLogger(RegistrationResource.class);

    private static final String ENTITY_NAME = "registration";

    private final RegistrationService registrationService;

    public RegistrationResource(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     * POST  /registrations : Create a new registration.
     *
     * @param registration the registration to create
     * @return the ResponseEntity with status 201 (Created) and with body the new registration, or with status 400 (Bad Request) if the registration has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/registrations")
    @Timed
    public ResponseEntity<Registration> createRegistration(@Valid @RequestBody Registration registration) throws URISyntaxException {
        log.debug("REST request to save Registration : {}", registration);
        if (registration.getId() != null) {
            throw new BadRequestAlertException("A new registration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Registration result = registrationService.save(registration);
        return ResponseEntity.created(new URI("/api/registrations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /registrations : Updates an existing registration.
     *
     * @param registration the registration to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated registration,
     * or with status 400 (Bad Request) if the registration is not valid,
     * or with status 500 (Internal Server Error) if the registration couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/registrations")
    @Timed
    public ResponseEntity<Registration> updateRegistration(@Valid @RequestBody Registration registration) throws URISyntaxException {
        log.debug("REST request to update Registration : {}", registration);
        if (registration.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Registration result = registrationService.save(registration);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, registration.getId().toString()))
            .body(result);
    }

    /**
     * GET  /registrations : get all the registrations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of registrations in body
     */
    @GetMapping("/registrations")
    @Timed
    public ResponseEntity<List<Registration>> getAllRegistrations(Pageable pageable) {
        log.debug("REST request to get a page of Registrations");
        Page<Registration> page = registrationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/registrations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /registrations/:id : get the "id" registration.
     *
     * @param id the id of the registration to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the registration, or with status 404 (Not Found)
     */
    @GetMapping("/registrations/{id}")
    @Timed
    public ResponseEntity<Registration> getRegistration(@PathVariable Long id) {
        log.debug("REST request to get Registration : {}", id);
        Optional<Registration> registration = registrationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(registration);
    }

    /**
     * DELETE  /registrations/:id : delete the "id" registration.
     *
     * @param id the id of the registration to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/registrations/{id}")
    @Timed
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        log.debug("REST request to delete Registration : {}", id);
        registrationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
