package ma.projet.gestionville_zone.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ma.projet.gestionville_zone.MapsActivity;
import ma.projet.gestionville_zone.ParVilleActivity;
import ma.projet.gestionville_zone.R;
import ma.projet.gestionville_zone.beans.PharmacieVilleZone;
import ma.projet.gestionville_zone.ui.Pharmacie;
import ma.projet.gestionville_zone.ui.Toutes_PharmaciesFragment;

public class PharmacieAdapter extends RecyclerView.Adapter<PharmacieAdapter.ViewPharmacieHolder>  {

    List<PharmacieVilleZone> pharmacieList ;
    private Context context;

    public PharmacieAdapter(List<PharmacieVilleZone> pharmacieList , Context context) {
        this.pharmacieList = pharmacieList;
        this.context = context;
    }

    @NonNull
    @Override
    public PharmacieAdapter.ViewPharmacieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_pharmacie_item,parent ,false);
        return new ViewPharmacieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacieAdapter.ViewPharmacieHolder holder, int position) {
        PharmacieVilleZone pharmacieVilleZone = pharmacieList.get(position);
        holder.nomZone.setText(pharmacieVilleZone.getNomZone());
        holder.nomPharmacie.setText(pharmacieVilleZone.getNom());
        holder.adressePhar.setText(pharmacieVilleZone.getAdresse());
        holder.nomVille.setText(pharmacieVilleZone.getNomVille());
        holder.localiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("pharmacie",  pharmacieVilleZone);
                context.startActivity(intent);
            }
        });

        boolean isExpandable = pharmacieList.get(position).isExpansable();
        holder.layout.setVisibility(isExpandable? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return pharmacieList.size();
    }

    public class ViewPharmacieHolder extends RecyclerView.ViewHolder {
        TextView nomPharmacie ,adressePhar , numPhone ,nomZone , nomVille;
        RelativeLayout relativeLayout ;
        ImageView localiser ;
        LinearLayout layout;
        public ViewPharmacieHolder(@NonNull View itemView) {
            super(itemView);

            nomPharmacie = itemView.findViewById(R.id.nomPharmacie);
            adressePhar = itemView.findViewById(R.id.adressePharmacie);
            numPhone = itemView.findViewById(R.id.numPhonePharm);
            nomZone =itemView.findViewById(R.id.nomZone);
            nomVille = itemView.findViewById(R.id.nomVillePhar);
            relativeLayout = itemView.findViewById(R.id.relativeparentPhar);
            layout =itemView.findViewById(R.id.linearchildPhar);
            localiser =itemView.findViewById(R.id.btnLoc);

            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PharmacieVilleZone pharmacieVilleZone = pharmacieList.get(getAdapterPosition());
                    pharmacieVilleZone.setExpansable(!pharmacieVilleZone.isExpansable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
