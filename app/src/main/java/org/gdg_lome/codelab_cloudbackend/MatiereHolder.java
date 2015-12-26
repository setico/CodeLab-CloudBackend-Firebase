package org.gdg_lome.codelab_cloudbackend;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by setico on 26/12/2015.
 */
public class MatiereHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public final ImageView logo;
    public final TextView nom;
    public final TextView description;
    public final ProgressBar progress;
    public ItemClickListener clickListener;


    public MatiereHolder(View itemView) {
        super(itemView);
        logo = (ImageView) itemView.findViewById(R.id.logo);
        nom = (TextView) itemView.findViewById(R.id.nom);
        description = (TextView) itemView.findViewById(R.id.description);
        progress = (ProgressBar) itemView.findViewById(R.id.progress);
        itemView.setOnClickListener(this);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
    @Override
    public void onClick(View v) {
        clickListener.onClick(v, getPosition(), false);
    }

}
