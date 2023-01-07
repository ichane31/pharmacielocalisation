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
import ma.projet.gestionville_zone.R;
import ma.projet.gestionville_zone.beans.Ville;

public class VilleAdapter extends RecyclerView.Adapter<VilleAdapter.ViewVilleHolder> {

    List<Ville> villeList;
    private Context context;

    public VilleAdapter(List<Ville> villeList ,final Context context) {
        this.villeList = villeList;
        this.context = context;
    }

    @NonNull
    @Override
    public VilleAdapter.ViewVilleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ville_item,parent ,false);
        return new ViewVilleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VilleAdapter.ViewVilleHolder holder, int position) {
        Ville ville = villeList.get(position);
        holder.nomVille.setText(ville.getNom());
        holder.countPharmacie.setText(ville.getPharmacieCount() + " Pharmacies ");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ParVilleActivity.class);
                intent.putExtra("nom_ville", ville.getNom());
                intent.putExtra("id_ville" , ville.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return villeList.size();
    }


    public class ViewVilleHolder extends RecyclerView.ViewHolder {
        TextView nomVille , countPharmacie;
        public ViewVilleHolder(@NonNull View itemView) {
            super(itemView);
            nomVille = itemView.findViewById(R.id.nomVille);
            countPharmacie = itemView.findViewById(R.id.countPharByVille);
        }
    }


}
