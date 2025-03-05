package techzen.module4_c1224.service.dto.res;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class JsonResponse {
    public static <T> ResponseEntity<ApiResponse<T>> ok(T t) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<T>builder().data(t).timestamp(LocalDateTime.now()).build());
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(T t) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.<T>builder().data(t).timestamp(LocalDateTime.now()).build());
    }

    public static <T> ResponseEntity<ApiResponse<T>> noContent() {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.<T>builder().timestamp(LocalDateTime.now()).build());
    }

    public static <T> ResponseEntity<ApiResponse<T>> badRequest(String message) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.<T>builder().message(message).timestamp(LocalDateTime.now()).build());
    }

    public static <T> ResponseEntity<ApiResponse<T>> notFound(String message) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.<T>builder().message(message).timestamp(LocalDateTime.now()).build());
    }

    public static <T> ResponseEntity<ApiResponse<T>> unauthorized(String message) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.<T>builder().message(message).timestamp(LocalDateTime.now()).build());
    }

    public static <T> ResponseEntity<ApiResponse<T>> forbidden(String message) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.<T>builder().message(message).timestamp(LocalDateTime.now()).build());
    }

    public static <T> ResponseEntity<ApiResponse<T>> internalServerError(String message) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.<T>builder().message(message).timestamp(LocalDateTime.now()).build());
    }
}
