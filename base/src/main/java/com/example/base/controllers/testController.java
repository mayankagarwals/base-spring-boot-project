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
        String rawPayload = "{\"type\": \"auth\", \"user\": {\"user_id\": \"123456789\", \"provider\": \"ExampleProvider\", \"last_webhook_update\": \"2022-01-01T10:00:00+00:00\", \"scopes\": \"read,write\", \"reference_id\": \"ref987654321\"}}";

        WebhookHandlerUtility webhookHandler = new WebhookHandlerUtility("my-secret");
        TerraWebhookPayload parsedPayload = webhookHandler.parseWebhookPayload(rawPayload);
        AuthData authData = parsedPayload.asAuthData().orElseGet(null);
        return authData;
    }

}
