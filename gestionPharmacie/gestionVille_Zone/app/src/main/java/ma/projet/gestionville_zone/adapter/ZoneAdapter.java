package ma.projet.gestionville_zone.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ma.projet.gestionville_zone.ParVilleActivity;
import ma.projet.gestionville_zone.ParZoneActivity;
import ma.projet.gestionville_zone.R;
import ma.projet.gestionville_zone.beans.Ville;
import ma.projet.gestionville_zone.beans.Zone;

public class ZoneAdapter extends RecyclerView.Adapter<ZoneAdapter.ViewZoneHolder>{

    List<Zone> zoneList;
    private  Context context;

    public ZoneAdapter(List<Zone> zoneList ,final Context context) {

        this.zoneList = zoneList;
        this.context=context;
    }

    @NonNull
    @Override
    public ZoneAdapter.ViewZoneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_zone_item,parent ,false);
        return new ViewZoneHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZoneAdapter.ViewZoneHolder holder, int position) {
        Zone zone = zoneList.get(position);
        holder.nomZone.setText(zone.getNom());
        holder.nomVille.setText(zone.getNomVille());
        holder.countPharmacie.setText(zone.getPharmacieCount() + " Pharmacies de garde");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ParZoneActivity.class);
                intent.putExtra("nom_ville", zone.getNom());
                intent.putExtra("id_ville" , zone.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return zoneList.size();
    }

    public class ViewZoneHolder extends RecyclerView.ViewHolder {
        TextView nomZone , nomVille ,countPharmacie;
        public ViewZoneHolder(@NonNull View itemView) {
            super(itemView);

            nomVille = itemView.findViewById(R.id.nomVilleDeZone);
            nomZone = itemView.findViewById(R.id.nomZoneItem);
            countPharmacie = itemView.findViewById(R.id.countPharByZone);
        }
    }
}
