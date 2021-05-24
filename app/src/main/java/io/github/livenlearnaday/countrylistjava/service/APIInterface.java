package io.github.livenlearnaday.countrylistjava.service;

import java.util.List;


import io.github.livenlearnaday.countrylistjava.data.entity.Country;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    String BASE_URL = "https://restcountries.eu/rest/v2/";

    @GET("all?fields=name;flag;capital;region;subregion")
    Call<List<Country>> getAllCountries();


}
