package ma.projet.gestionville_zone.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

import ma.projet.gestionville_zone.R;
import ma.projet.gestionville_zone.adapter.VPAdapter;

public class Pharmacie extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private VPAdapter vpAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pharmacie, container, false);

        tabLayout = view.findViewById(R.id.tabs_pharmacie);
        viewPager = view.findViewById(R.id.viewPager_pharmacie);
        vpAdapter = new VPAdapter(getFragmentManager() , FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Toutes_PharmaciesFragment() , "Toutes");
        vpAdapter.addFragment(new GardeJ_PharmacieFragment() ,"Garde Jour");
        vpAdapter.addFragment(new GardeN_PharmacieFragment(), "Garde Nuit");
        viewPager.setAdapter(vpAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}