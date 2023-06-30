package gr.aueb.cf.finalprojpets.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Controller responsible for handling error-related requests and exceptions.
 */
@ControllerAdvice
public class ErrorController {

    @GetMapping("/error")
    public String handleError(){

        return "error";
    }

    /**
     * Handles IllegalArgumentException and IllegalStateException.
     *
     * @param ex The RuntimeException to handle.
     * @return ResponseEntity containing the error message and HTTP status code.
     */
    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleBadRequest(RuntimeException ex) {
        String message = ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

    }

    /**
     * Handles Exception.
     * @param e The Exception to handle.
     * @return ResponseEntity containing the error message and HTTP status code.
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
}
