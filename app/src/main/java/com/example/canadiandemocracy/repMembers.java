package com.example.canadiandemocracy;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface repMembers {
    @GET("{loc}")
    Call<ResponseBody> apiResponse(@Path(value = "loc", encoded = true) String loc);
}
