package br.com.nuclear360.dto;

import java.time.LocalDateTime;

public record ErrorCustomResponse(
        LocalDateTime timestamp, int status, String error, String message) {
}
