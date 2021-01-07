package kz.vdenise.vdeniseadvancedtodo.staff.place.exceptions;

import kz.vdenise.vdeniseadvancedtodo.staff.exceptions.NotFoundStaffException;

public class DistrictNotFoundStaffException extends NotFoundStaffException {
    public DistrictNotFoundStaffException(Long id) {
        super("District with id " + id + " not found!");
    }
}
