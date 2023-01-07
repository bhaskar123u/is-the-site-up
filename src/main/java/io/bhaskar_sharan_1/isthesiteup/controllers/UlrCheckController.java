package io.bhaskar_sharan_1.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UlrCheckController {

    private final String SITE_IS_UP = "Site is up";
    private final String SITE_IS_DOWN = "Site is down";
    private final String INCORRECT_URL = "URL is incorrect";

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url) {
        String returnMessage = "";

        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCodeCategory = conn.getResponseCode() / 100;
            System.out.println("DEBUGGING APPLICATION "+responseCodeCategory);
            if(responseCodeCategory == 2 || responseCodeCategory == 3){
                returnMessage = SITE_IS_UP;
            }
            else{
                returnMessage = SITE_IS_DOWN;
            }
        } catch (MalformedURLException e) {
            System.out.println("DEBUGGING APPLICATION MalformedURLException");
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            System.out.println("DEBUGGING APPLICATION IOException");
            returnMessage = SITE_IS_DOWN;
        }

        System.out.println("DEBUGGING APPLICATION "+returnMessage);
        return returnMessage;
    }
}
