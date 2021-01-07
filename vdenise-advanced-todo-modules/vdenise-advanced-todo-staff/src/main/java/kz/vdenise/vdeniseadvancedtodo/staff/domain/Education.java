package kz.vdenise.vdeniseadvancedtodo.staff.domain;

import kz.vdenise.vdeniseadvancedtodo.staff.common.domain.BaseEntity;

public class Education extends BaseEntity {

    private EducationType type;

    private String diplomaNumber;
    private Integer startYear;
    private Integer endYear;

    private Boolean isGraduated;

    private Institution institution;
    private Speciality speciality;

}
