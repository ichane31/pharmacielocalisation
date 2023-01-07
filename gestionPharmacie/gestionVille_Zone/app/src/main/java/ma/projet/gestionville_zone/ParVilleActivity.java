package ma.projet.gestionville_zone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ma.projet.gestionville_zone.adapter.PharmacieJourAdapter;
import ma.projet.gestionville_zone.adapter.PharmacieNuitAdapter;
import ma.projet.gestionville_zone.beans.PharmacieVilleZone;
import ma.projet.gestionville_zone.beans.Url;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParVilleActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PharmacieNuitAdapter pharmacieNuitAdapter;
    PharmacieJourAdapter pharmacieJourAdapter;
    TextView searchView;
    ImageView nuit , jour;
    LinearLayout layout;
    String nomVille;
    MyApiRetrofit myApiRetrofit;
    Long idVille;
    List<PharmacieVilleZone> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_par_ville);

        Intent intent = getIntent();
        idVille = intent.getLongExtra("id_ville" , 0);
        nomVille = intent.getStringExtra("nom_ville");
        recyclerView = findViewById(R.id.searchByVilleRecycle);

        layout = findViewById(R.id.butJNV);

        nuit = findViewById(R.id.btnNuit);
        jour =findViewById(R.id.btnJOUR);
        searchView= findViewById(R.id.chaneVille);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Url.apibaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myApiRetrofit = retrofit.create(MyApiRetrofit.class);
        getNuitPharmacie();

        jour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setBackgroundResource(R.color.jour);
                Call<List<PharmacieVilleZone>> listCallJour = myApiRetrofit.getPharmacieGardeJourByVille(nomVille);
                listCallJour.enqueue(new Callback<List<PharmacieVilleZone>>() {
                    @Override
                    public void onResponse(Call<List<PharmacieVilleZone>> call, Response<List<PharmacieVilleZone>> response) {
                        if(response.body() !=null) {
                            data = response.body();
                            int i = 0;
                            for (i=0; i <data.size(); i++) {
                                Log.d(i +"" , data.get(i).getNom());
                            }
                            pharmacieJourAdapter = new PharmacieJourAdapter(data , ParVilleActivity.this);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ParVilleActivity.this);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            //recyclerView.setHasFixedSize(true);
                            //recyclerView.setHasFixedSize(true);

                            recyclerView.setAdapter(pharmacieJourAdapter);
                            pharmacieJourAdapter.notifyDataSetChanged();
                        }
                        else {
                            Toast.makeText(ParVilleActivity.this, "Aucune Pharmacie de Garde de Nuit trouvée", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<PharmacieVilleZone>> call, Throwable t) {
                        Log.d("Error" , t.getMessage());
                    }
                });
            }
        });
        nuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNuitPharmacie();
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void getNuitPharmacie() {
        layout.setBackgroundResource(R.color.nuit);
        Call<List<PharmacieVilleZone>> listCall = myApiRetrofit.getPharmacieGardeNuitByVille(nomVille);

        listCall.enqueue(new Callback<List<PharmacieVilleZone>>() {
            @Override
            public void onResponse(Call<List<PharmacieVilleZone>> call, Response<List<PharmacieVilleZone>> response) {
                if(response.body() !=null) {
                    data = response.body();
                    int i = 0;
                    for (i=0; i <data.size(); i++) {
                        Log.d(i +"" , data.get(i).getNom());
                    }
                    pharmacieNuitAdapter = new PharmacieNuitAdapter(data , ParVilleActivity.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ParVilleActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    //recyclerView.setHasFixedSize(true);
                    //recyclerView.setHasFixedSize(true);

                    recyclerView.setAdapter(pharmacieNuitAdapter);
                    pharmacieNuitAdapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(ParVilleActivity.this, "Aucune Pharmacie de Garde de Nuit trouvée", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PharmacieVilleZone>> call, Throwable t) {
                Log.d("Error" , t.getMessage());
            }
        });
    }
}