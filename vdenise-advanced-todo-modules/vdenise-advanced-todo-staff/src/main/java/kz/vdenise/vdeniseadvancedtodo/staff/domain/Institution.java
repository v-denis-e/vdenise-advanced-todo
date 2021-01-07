package kz.vdenise.vdeniseadvancedtodo.staff.domain;

public class Institution {

    private Long id;
    private String name;

    private String url;

    private Country addressCountry;
    private Region addressRegion;
    private District addressDistrict;
    private String addressValue;
    private String zipCode;

    private InstitutionType type;

}
