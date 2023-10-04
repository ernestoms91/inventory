package com.menchaca.inventory.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data
@Builder
public class HttpCreatedDTO<T> {
    private int status;
    private String msg;
    private T content;
}
