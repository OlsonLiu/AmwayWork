package calculator.api;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalState(IllegalStateException ex) {
        return ResponseEntity
                .badRequest()
                .body("Error message:" + ex.getMessage());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleInvalidEnum(InvalidFormatException ex) {
        if (ex.getTargetType().isEnum() && ex.getMessage().contains("OperationType")) {
            return ResponseEntity.badRequest().body("illegal operation type");
        }
        return ResponseEntity.badRequest().body("invalid format");
    }
}
