package br.com.nuclear360.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationErrorResponse(LocalDateTime timestamp, int status, String error, List<ErrorFieldCustom> errors) {
}
