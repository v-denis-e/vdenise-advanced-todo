package kz.vdenise.vdeniseadvancedtodo.staff.domain;

import kz.vdenise.vdeniseadvancedtodo.staff.common.domain.BaseEntity;

import java.time.LocalDate;
import java.util.Set;

public class Employee extends BaseEntity {

    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birtDate;
    private LocalDate employmentDate;
    private Boolean isActive;

    private Country addressCountry;
    private Region addressRegion;
    private District addressDistrict;
    private String addressValue;
    private String zipCode;

    private Gender gender;
    private Position position;
    private Department department;

    private Set<Contact> contacts;
    private Set<Education> educations;

}
