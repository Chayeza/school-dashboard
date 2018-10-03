package co.za.school.dashboard.repository;

import co.za.school.dashboard.domain.Coarse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Coarse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoarseRepository extends JpaRepository<Coarse, Long> {

}
