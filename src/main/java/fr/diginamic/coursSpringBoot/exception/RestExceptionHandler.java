package fr.diginamic.coursSpringBoot.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler (InvalidVilleException.class)
    public ResponseEntity<String> handleInvalidVilleException(InvalidVilleException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler (VilleNonTrouvee.class)
    public ResponseEntity<String> handleInvalidTownNotFound(VilleNonTrouvee ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler (InvalidDepartementException.class)
    public ResponseEntity<String> handleInvalidDepartementException(VilleNonTrouvee ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}

