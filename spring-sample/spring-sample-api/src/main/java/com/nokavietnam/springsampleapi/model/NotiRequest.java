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
public class NotiRequest {
    @JsonProperty("pushNotifyId")
    private String pushNotifyId;

    @JsonProperty("coreBankRefNum")
    private String coreBankRefNum;

    @JsonProperty("bankaccount")
    private String bankAccount;

    @JsonProperty("transactionAmount")
    private Long transactionAmount;

    @JsonProperty("narrative")
    private String narrative;

    @JsonProperty("transactionType")
    private String transactionType;

    @JsonProperty("transactionTime")
    private Long transactionTime;

    @JsonProperty("additionalField1")
    private String additionalField1;

    @JsonProperty("additionalField2")
    private String additionalField2;

    @JsonProperty("additionalField3")
    private String additionalField3;

    @JsonProperty("additionalField4")
    private String additionalField4;
}