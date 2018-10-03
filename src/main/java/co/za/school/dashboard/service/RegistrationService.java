package co.za.school.dashboard.service;

import co.za.school.dashboard.domain.Registration;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Registration.
 */
public interface RegistrationService {

    /**
     * Save a registration.
     *
     * @param registration the entity to save
     * @return the persisted entity
     */
    Registration save(Registration registration);

    /**
     * Get all the registrations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Registration> findAll(Pageable pageable);


    /**
     * Get the "id" registration.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Registration> findOne(Long id);

    /**
     * Delete the "id" registration.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
