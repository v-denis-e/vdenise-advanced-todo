package kz.vdenise.vdeniseadvancedtodo.staff.place.repositories;

import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
}
