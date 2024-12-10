package com.Arjun.journalApplication.WeatherApiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Response {

  public Current current;
    @Getter
    @Setter
    public class Current{

        public int temperature;

        @JsonProperty("weather_description")
        public List<String> weatherDescriptions;

        public int feelslike;

    }







}
