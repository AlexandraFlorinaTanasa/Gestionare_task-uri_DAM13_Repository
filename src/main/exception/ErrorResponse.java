package org.gestionare_taskuri.exception;
import lombok.Getter;




@Getter

public record ErrorResponse(int statusCode, String message) {
}