package kz.vdenise.vdeniseadvancedtodo.staff.place.exceptions;

import kz.vdenise.vdeniseadvancedtodo.staff.exceptions.NotFoundStaffException;

public class RegionNotFoundStaffException extends NotFoundStaffException {
    public RegionNotFoundStaffException(Long id) {
        super("Region with id " + id + " not found!");
    }
}
