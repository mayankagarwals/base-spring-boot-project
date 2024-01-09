package com.example.base.controllers;

import co.tryterra.terraclient.WebhookHandlerUtility;
import co.tryterra.terraclient.api.TerraWebhookPayload;
import co.tryterra.terraclient.models.AuthData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/test")
public class testController {

    /*
    Test for https://github.com/tryterra/terra-client-java/issues/19
     */
    @GetMapping("/webHookHandlerAuthData")
    public AuthData authTest(){
        String rawPayload = "{"
                + "\"user\": {"
                + "    \"last_webhook_update\": null,"
                + "    \"provider\": \"GOOGLE\","
                + "    \"scopes\": \"calendar.settings.readonly,fitness.body_temperature.read,fitness.blood_glucose.read,fitness.sleep.read,user.gender.read,fitness.location.read,fitness.blood_pressure.read,user.birthday.read,userinfo.profile,userinfo.email,fitness.nutrition.read,fitness.oxygen_saturation.read,fitness.heart_rate.read,fitness.reproductive_health.read,fitness.body.read,fitness.activity.read\","
                + "    \"user_id\": \"aa4bc489-e466-4db3-a1cd-2b2ccf64a9b1\""
                + "},"
                + "\"type\": \"auth\","
                + "\"status\": \"success\","
                + "\"message\": \"User has successfully authenticated\","
                + "\"widget_session_id\": \"1737787e-5aa4-479c-a2b3-8138b197a05b\","
                + "\"reference_id\": \"UAE-21500\","
                + "\"version\": \"2022-03-16\""
                + "}";


        WebhookHandlerUtility webhookHandler = new WebhookHandlerUtility("my-secret");
        TerraWebhookPayload parsedPayload = webhookHandler.parseWebhookPayload(rawPayload);
        AuthData authData = parsedPayload.asAuthData().orElseGet(null);
        return authData;
    }

}
