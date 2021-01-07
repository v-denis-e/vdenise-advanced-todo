package kz.vdenise.vdeniseadvancedtodo.staff.place.repositories;

import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
