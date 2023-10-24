package kg.nail.hw50.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class CommentNotFoundException extends RuntimeException {
    final HttpStatus httpStatus;

    public CommentNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
