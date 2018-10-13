package co.za.school.dashboard.repository;

import co.za.school.dashboard.domain.Registration;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Registration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    @Query("select registration from Registration registration where registration.studentId>=?1")
    List<Registration>findAllForStudent(Long studentId);
}
