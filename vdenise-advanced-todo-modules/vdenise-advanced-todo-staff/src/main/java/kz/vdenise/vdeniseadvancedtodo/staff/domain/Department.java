package kz.vdenise.vdeniseadvancedtodo.staff.domain;

import kz.vdenise.vdeniseadvancedtodo.staff.common.domain.NamedEntity;

public class Department extends NamedEntity {

    private Boolean isActive;

    private Country addressCountry;
    private Region addressRegion;
    private District addressDistrict;
    private String addressValue;
    private String zipCode;

    private Department parent;

}
