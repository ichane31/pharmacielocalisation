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

import java.util.List;

import ma.projet.gestionville_zone.MapsActivity;
import ma.projet.gestionville_zone.R;
import ma.projet.gestionville_zone.beans.PharmacieVilleZone;

public class PharmacieJourAdapter extends RecyclerView.Adapter<PharmacieJourAdapter.ViewPharmacieJourHolder>{

    List<PharmacieVilleZone> pharmacieList ;
    private  Context context;

    public PharmacieJourAdapter(List<PharmacieVilleZone> pharmacieList , final Context context) {
        this.pharmacieList = pharmacieList;
        this.context = context;
    }

    @NonNull
    @Override
    public PharmacieJourAdapter.ViewPharmacieJourHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pharmacie_jour_item,parent ,false);
        return new ViewPharmacieJourHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacieJourAdapter.ViewPharmacieJourHolder holder, int position) {
        PharmacieVilleZone pharmacieVilleZone = pharmacieList.get(position);
        holder.nomZone.setText(pharmacieVilleZone.getNomZone());
        holder.nomPharmacie.setText(pharmacieVilleZone.getNom());
        holder.adressePhar.setText(pharmacieVilleZone.getAdresse());
        //holder.nomVille.setText(pharmacieVilleZone.getNomVille());
        holder.heureOuvert.setText("Ouvert de "+pharmacieVilleZone.getGardeStartTime() + "à" + pharmacieVilleZone.getGardeEndTime());

        boolean isExpandable = pharmacieList.get(position).isExpansable();
        holder.layout.setVisibility(isExpandable? View.VISIBLE : View.GONE);

        holder.localisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("pharmacie",  pharmacieVilleZone);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pharmacieList.size();
    }

    public class ViewPharmacieJourHolder extends RecyclerView.ViewHolder {
        TextView nomPharmacie ,adressePhar , numPhone ,nomZone , nomVille , heureOuvert;
        RelativeLayout relativeLayout ;
        ImageView localisation;
        LinearLayout layout;
        public ViewPharmacieJourHolder(@NonNull View itemView) {
            super(itemView);

            nomPharmacie = itemView.findViewById(R.id.nomPharmacieJour);
            adressePhar = itemView.findViewById(R.id.adressePharmacieJour);
            numPhone = itemView.findViewById(R.id.numPhonePharm);
            nomZone =itemView.findViewById(R.id.nomZoneJour);
            heureOuvert = itemView.findViewById(R.id.heureOuverture);
            relativeLayout = itemView.findViewById(R.id.relativeparentPharJour);
            layout =itemView.findViewById(R.id.linearchildPharJour);
            localisation =itemView.findViewById(R.id.btnLocalJour);

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
