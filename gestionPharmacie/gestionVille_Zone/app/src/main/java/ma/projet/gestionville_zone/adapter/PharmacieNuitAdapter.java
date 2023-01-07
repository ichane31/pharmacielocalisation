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

public class PharmacieNuitAdapter  extends RecyclerView.Adapter<PharmacieNuitAdapter.ViewPharmacieNuitHolder>{
    List<PharmacieVilleZone> pharmacieNuitList ;
    public Context context;

    public PharmacieNuitAdapter(List<PharmacieVilleZone> pharmacieList , final Context context) {
        this.pharmacieNuitList = pharmacieList;
        this.context=context;
    }

    @NonNull
    @Override
    public PharmacieNuitAdapter.ViewPharmacieNuitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pharmacie_nuit_item,parent ,false);
        return new ViewPharmacieNuitHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacieNuitAdapter.ViewPharmacieNuitHolder holder, int position) {
        PharmacieVilleZone pharmacieVilleZone = pharmacieNuitList.get(position);
        holder.nomZone.setText(pharmacieVilleZone.getNomZone());
        holder.nomPharmacie.setText(pharmacieVilleZone.getNom());
        holder.adressePhar.setText(pharmacieVilleZone.getAdresse());
        //holder.nomVille.setText(pharmacieVilleZone.getNomVille());
        holder.heureOuvert.setText("Ouvert de "+pharmacieVilleZone.getGardeStartTime() + "à" + pharmacieVilleZone.getGardeEndTime());

        boolean isExpandable = pharmacieNuitList.get(position).isExpansable();
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
        return pharmacieNuitList.size();
    }

    public class ViewPharmacieNuitHolder extends RecyclerView.ViewHolder {
        TextView nomPharmacie ,adressePhar , numPhone ,nomZone , nomVille , heureOuvert;
        RelativeLayout relativeLayout ;
        ImageView localisation ;
        LinearLayout layout;
        public ViewPharmacieNuitHolder(@NonNull View itemView) {
            super(itemView);

            nomPharmacie = itemView.findViewById(R.id.nomPharmacieNuit);
            adressePhar = itemView.findViewById(R.id.adressePharmacieNuit);
            numPhone = itemView.findViewById(R.id.numPhonePharmNuit);
            nomZone =itemView.findViewById(R.id.nomZoneNuit);
            heureOuvert = itemView.findViewById(R.id.heureOuverture);
            relativeLayout = itemView.findViewById(R.id.relativeparentPharNuit);
            layout =itemView.findViewById(R.id.linearchildPharNuit);
            localisation = itemView.findViewById(R.id.btnLocal);

            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PharmacieVilleZone pharmacieVilleZone = pharmacieNuitList.get(getAdapterPosition());
                    pharmacieVilleZone.setExpansable(!pharmacieVilleZone.isExpansable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
