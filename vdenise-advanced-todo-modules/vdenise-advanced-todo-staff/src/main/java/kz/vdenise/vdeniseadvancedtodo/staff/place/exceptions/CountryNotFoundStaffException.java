package kz.vdenise.vdeniseadvancedtodo.staff.place.exceptions;

import kz.vdenise.vdeniseadvancedtodo.staff.exceptions.NotFoundStaffException;

public class CountryNotFoundStaffException extends NotFoundStaffException {
    public CountryNotFoundStaffException(Long id) {
        super("Country with id " + id + " not found!");
    }
}
