package ma.projet.gestionville_zone.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ma.projet.gestionville_zone.MyApiRetrofit;
import ma.projet.gestionville_zone.R;
import ma.projet.gestionville_zone.adapter.PharmacieAdapter;
import ma.projet.gestionville_zone.adapter.PharmacieJourAdapter;
import ma.projet.gestionville_zone.beans.PharmacieVilleZone;
import ma.projet.gestionville_zone.beans.Url;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GardeJ_PharmacieFragment extends Fragment {
    private RecyclerView recyclerView;
    private PharmacieJourAdapter pharmacieAdapter;
    List<PharmacieVilleZone> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_garde_j__pharmacie, container, false);

        recyclerView = view.findViewById(R.id.gardeJourAll);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Url.apibaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApiRetrofit myApiRetrofit = retrofit.create(MyApiRetrofit.class);
        Call<List<PharmacieVilleZone>> listCall = myApiRetrofit.getPharmacieGardeJour();

        listCall.enqueue(new Callback<List<PharmacieVilleZone>>() {
            @Override
            public void onResponse(Call<List<PharmacieVilleZone>> call, Response<List<PharmacieVilleZone>> response) {
                data = response.body();
                int i = 0;
                for (i=0; i <data.size(); i++) {
                    Log.d(i +"" , data.get(i).getNom());
                }
                pharmacieAdapter = new PharmacieJourAdapter(data ,getContext());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);
                //recyclerView.setHasFixedSize(true);
                //recyclerView.setHasFixedSize(true);

                recyclerView.setAdapter(pharmacieAdapter);
                pharmacieAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<PharmacieVilleZone>> call, Throwable t) {
                Log.d("Error" , t.getMessage());
            }
        });

        return view;
    }
}