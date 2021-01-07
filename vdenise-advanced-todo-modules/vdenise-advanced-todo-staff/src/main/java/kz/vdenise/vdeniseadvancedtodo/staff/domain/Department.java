package kz.vdenise.vdeniseadvancedtodo.staff.domain;

public class Department {

    private Long id;
    private String name;
    private Boolean isActive;

    private Country addressCountry;
    private Region addressRegion;
    private District addressDistrict;
    private String addressValue;
    private String zipCode;

    private Department parent;

}
