package co.za.school.dashboard.service.impl;

import co.za.school.dashboard.service.RegistrationService;
import co.za.school.dashboard.domain.Registration;
import co.za.school.dashboard.repository.RegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Registration.
 */
@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    private final Logger log = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    private final RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    /**
     * Save a registration.
     *
     * @param registration the entity to save
     * @return the persisted entity
     */
    @Override
    public Registration save(Registration registration) {
        log.debug("Request to save Registration : {}", registration);        return registrationRepository.save(registration);
    }

    /**
     * Get all the registrations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Registration> findAll(Pageable pageable) {
        log.debug("Request to get all Registrations");
        return registrationRepository.findAll(pageable);
    }


    /**
     * Get one registration by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Registration> findOne(Long id) {
        log.debug("Request to get Registration : {}", id);
        return registrationRepository.findById(id);
    }

    /**
     * Delete the registration by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Registration : {}", id);
        registrationRepository.deleteById(id);
    }
}
