package kg.nail.hw50.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class LikeAlreadyExistException extends RuntimeException {
    final HttpStatus httpStatus;

    public LikeAlreadyExistException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
