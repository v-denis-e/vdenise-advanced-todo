package kz.vdenise.vdeniseadvancedtodo.staff.place.repositories;

import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
