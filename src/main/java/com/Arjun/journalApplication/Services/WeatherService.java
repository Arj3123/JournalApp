package com.Arjun.journalApplication.Services;

import com.Arjun.journalApplication.Cache.AppCache;
import com.Arjun.journalApplication.WeatherApiResponse.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

  //  private static final String API="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
      @Autowired
    AppCache appCache;
    @Autowired
    private RestTemplate restTemplate;

    public Response report(String city){
        String finalApi=appCache.map.get("weather_api").replace("API_KEY",apiKey).replace("CITY",city);
       ResponseEntity<Response>response= restTemplate.exchange(finalApi, HttpMethod.GET,null, Response.class);
        System.out.println(response.getBody());
       return response.getBody();
    }

}
