package kz.vdenise.vdeniseadvancedtodo.staff.place.bootstrap;

import kz.vdenise.vdeniseadvancedtodo.staff.bootstrap.StaffBootstrap;
import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.Country;
import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.District;
import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.Region;
import kz.vdenise.vdeniseadvancedtodo.staff.place.services.PlaceCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
public class PlaceBootstrap implements StaffBootstrap {

    private final PlaceCreator service;

    @Override
    public void load() {
        log.info("Loading places ...");

        var kz = Country.builder().name("Republic of Kazakhstan").build();

        var almaty = Region.builder().name("Almaty").build();
        var medeu = District.builder().name("Medeu district").build();
        var almaly = District.builder().name("Almaly district").build();
        almaty.addDistrict(medeu);
        almaty.addDistrict(almaly);
        kz.addRegion(almaty);

        var nurSultan = Region.builder().name("Nur-Sultan").build();
        var baykonyr = District.builder().name("Baykonyr district").build();
        var saryarka = District.builder().name("Saryarka district").build();
        nurSultan.addDistrict(baykonyr);
        nurSultan.addDistrict(saryarka);
        kz.addRegion(nurSultan);

        service.creteAll(Set.of(kz));

        log.info("Places loaded!");
    }
}
