package com.nokavietnam.springsampleapi.controller;

import com.nokavietnam.springsampleapi.model.NotiRequest;
import com.nokavietnam.springsampleapi.model.NotiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/partner")
public class PartnerApiController {

    @RequestMapping(value = "/noti")
    public ResponseEntity<NotiResponse> postNoti(@RequestBody NotiRequest notiRequest) {
        var response = new NotiResponse();
        response.setError("00");
        response.setErrorMsg("Success");
        response.setDetails("success");
        response.setRefTransactionId(UUID.randomUUID().toString());
        return ResponseEntity.ok().body(response);
    }
}
