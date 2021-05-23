package io.github.livenlearnaday.countrylistjava.service;

import java.util.List;


import io.github.livenlearnaday.countrylistjava.data.entity.Country;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

//    String BASE_URL = "https://livenlearnaday.github.io/";

    String BASE_URL = "https://restcountries.eu/rest/v2/";

//    @FormUrlEncoded  => FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).

//    @GET("name/{name}")
//    Response getCountryDetail(String name,
//                              Callback<List<Country>> callback);

//// data is too large...cannot get data have to set longer timeout ....do it by country
//    @GET("data/all_countries.json")
//    Call<List<Country>> getAllCountries();

//    @GET("data/countries_name_flag.json")
//    Call<List<Country>> getCountryNameFlag();


    @GET("all?fields=name;flag")
    Call<List<Country>> getAllCountriesNameFlag();

    @GET("name/{name}")
    Call<List<Country>> getCountryDetail();


}
