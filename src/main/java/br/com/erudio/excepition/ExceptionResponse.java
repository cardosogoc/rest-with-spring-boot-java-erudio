package br.com.erudio.excepition;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {
}
