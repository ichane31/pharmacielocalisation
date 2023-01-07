package ma.projet.gestionville_zone.ui.ville;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ma.projet.gestionville_zone.MyApiRetrofit;
import ma.projet.gestionville_zone.R;
import ma.projet.gestionville_zone.adapter.PharmacieAdapter;
import ma.projet.gestionville_zone.adapter.VilleAdapter;
import ma.projet.gestionville_zone.beans.Url;
import ma.projet.gestionville_zone.beans.Ville;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VilleFragment extends Fragment {
    private RecyclerView recyclerView;

    private VilleAdapter villeAdapter;
    List<Ville> datavilles;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_ville, container, false);

        recyclerView = view.findViewById(R.id.allVilles);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Url.apibaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApiRetrofit myApiRetrofit = retrofit.create(MyApiRetrofit.class);
        Call<List<Ville>> listCall = myApiRetrofit.getAllVille();

        listCall.enqueue(new Callback<List<Ville>>() {
            @Override
            public void onResponse(Call<List<Ville>> call, Response<List<Ville>> response) {
                datavilles = response.body();
                int i = 0;
                for (i=0; i <datavilles.size(); i++) {
                    Log.d(i +"" , datavilles.get(i).getNom());
                }
                villeAdapter = new VilleAdapter(datavilles , getContext());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);

                recyclerView.setAdapter(villeAdapter);
                villeAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Ville>> call, Throwable t) {
                Log.d("Error" , t.getMessage());
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


}