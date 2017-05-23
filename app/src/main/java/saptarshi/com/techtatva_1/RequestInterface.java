package saptarshi.com.techtatva_1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {
    @GET("/users")
    Call<List<Details>> getDetails();
}
