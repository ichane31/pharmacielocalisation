package ma.projet.gestionville_zone.ui.zone;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ma.projet.gestionville_zone.MyApiRetrofit;
import ma.projet.gestionville_zone.R;
import ma.projet.gestionville_zone.adapter.VilleAdapter;
import ma.projet.gestionville_zone.adapter.ZoneAdapter;
import ma.projet.gestionville_zone.beans.Url;
import ma.projet.gestionville_zone.beans.Ville;
import ma.projet.gestionville_zone.beans.Zone;
import ma.projet.gestionville_zone.databinding.FragmentZoneBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZoneFragment extends Fragment {

    private FragmentZoneBinding binding;
    private RecyclerView recyclerView;
    private ZoneAdapter zoneAdapter;
    List<Zone> data;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_zone, container, false);

        recyclerView = view.findViewById(R.id.recycleZones);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Url.apibaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApiRetrofit myApiRetrofit = retrofit.create(MyApiRetrofit.class);
        Call<List<Zone>> listCall = myApiRetrofit.getAllZone();

        listCall.enqueue(new Callback<List<Zone>>() {
            @Override
            public void onResponse(Call<List<Zone>> call, Response<List<Zone>> response) {
                data = response.body();
                int i = 0;
                for (i=0; i <data.size(); i++) {
                    Log.d(i +"" , data.get(i).getNom());
                }
                zoneAdapter = new ZoneAdapter(data , getContext());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);

                recyclerView.setAdapter(zoneAdapter);
                zoneAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Zone>> call, Throwable t) {
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