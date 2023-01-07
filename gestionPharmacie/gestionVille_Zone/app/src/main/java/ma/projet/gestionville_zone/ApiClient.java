package ma.projet.gestionville_zone;

import ma.projet.gestionville_zone.beans.Url;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient  {

    public static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Url.apibaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    public static MyApiRetrofit getService(){
        MyApiRetrofit userService = getRetrofit().create(MyApiRetrofit.class);
        return userService;
    }
}