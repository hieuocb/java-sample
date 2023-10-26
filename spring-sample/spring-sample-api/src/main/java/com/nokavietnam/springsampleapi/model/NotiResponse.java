package com.nokavietnam.springsampleapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotiResponse {
    @JsonProperty("error")
    private String error;

    @JsonProperty("errorMsg")
    private String errorMsg;

    @JsonProperty("details")
    private String details;

    @JsonProperty("refTransactionId")
    private String refTransactionId;
}
