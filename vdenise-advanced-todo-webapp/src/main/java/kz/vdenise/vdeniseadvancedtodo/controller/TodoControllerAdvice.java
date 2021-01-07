package kz.vdenise.vdeniseadvancedtodo.controller;

import kz.vdenise.vdeniseadvancedtodo.model.ErrorDTO;
import kz.vdenise.vdeniseadvancedtodo.staff.exceptions.NotFoundStaffException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class TodoControllerAdvice {

    @ExceptionHandler(NotFoundStaffException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundStaff(NotFoundStaffException e) {
        return new ResponseEntity<>(new ErrorDTO(e.getMessage(), new ArrayList<>()), headers(), HttpStatus.NOT_FOUND);
    }

    private MultiValueMap<String, String> headers() {
        var result = new LinkedMultiValueMap<String, String>();
        result.put("Content-Type", List.of(MediaType.APPLICATION_JSON_VALUE));
        return result;
    }


}
