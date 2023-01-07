package ma.projet.gestionville_zone;

import java.util.List;

import ma.projet.gestionville_zone.beans.LoginRequest;
import ma.projet.gestionville_zone.beans.LoginResponse;
import ma.projet.gestionville_zone.beans.PharmacieVilleZone;
import ma.projet.gestionville_zone.beans.RegisterRequest;
import ma.projet.gestionville_zone.beans.RegisterResponse;
import ma.projet.gestionville_zone.beans.Ville;
import ma.projet.gestionville_zone.beans.Zone;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyApiRetrofit {

    @POST("userpharmacie/auth")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
    @POST("userpharmacie/add")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @GET("getAllPharmacieVilleZone")
    Call<List<PharmacieVilleZone>> getPharmacies();

    @GET("pharmacy-counts")
    Call<List<Ville>> getAllVille();

    @GET("getZonesVille")
    Call<List<Zone>> getAllZone();

    @GET("allPharmacieGarde/1")
    Call<List<PharmacieVilleZone>> getPharmacieGardeJour();

    @GET("allPharmacieGarde/2")
    Call<List<PharmacieVilleZone>> getPharmacieGardeNuit();

    @GET("allPharmacieGarde/1/ByVille/{villeId}")
    Call<List<PharmacieVilleZone>> getPharmacieByVille(@Path("villeId") String villeId);

    @GET("allPharmacieGarde/1/ByVille/{villeId}")
    Call<List<PharmacieVilleZone>> getPharmacieByZone(@Path("villeId") String villeId);

    @GET("allPharmacieGarde/1/ByVille/{villeId}")
    Call<List<PharmacieVilleZone>> getPharmacieGardeJourByVille(@Path("villeId") String villeId);

    @GET("allPharmacieGarde/2/ByVille/{villeId}")
    Call<List<PharmacieVilleZone>> getPharmacieGardeNuitByVille(@Path("villeId") String villeId);

    @GET("allPharmacieGarde/1/ByZone/{zoneId}")
    Call<List<PharmacieVilleZone>> getPharmacieGardeJourByZone(@Path("zoneId") String zoneId);

    @GET("allPharmacieGarde/2/ByZone/{zoneId}")
    Call<List<PharmacieVilleZone>> getPharmacieGardeNuitByZone(@Path("zoneId") String zoneId);
}
