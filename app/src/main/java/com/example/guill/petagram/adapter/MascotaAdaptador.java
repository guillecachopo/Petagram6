package com.example.guill.petagram.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guill.petagram.DetalleMascota;
import com.example.guill.petagram.R;
import com.example.guill.petagram.model.MascotaModel;
import com.example.guill.petagram.pojo.Mascota;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by guill on 17/08/2017.
 */
public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    private List<Mascota> mascotas;
    private boolean thumbnail;
    private Context context;
    private MascotaModel mascotaModel;


    public MascotaAdaptador(List<Mascota> mascotas, boolean thumbnail, Context context) {
        this.mascotas = mascotas;
        this.thumbnail = thumbnail;
        this.context = context;
        this.mascotaModel = new MascotaModel(context);
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(thumbnail ? R.layout.thumbnail_mascota : R.layout.mascota, parent, false);

        return new MascotaViewHolder(view, thumbnail);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewholder, int position) {
        final Mascota mascota = mascotas.get(position);

        Picasso.with(context)
                .load(mascota.getFotoUrl())
                .resizeDimen(R.dimen.timeline_image_width, R.dimen.timeline_image_height)
                .centerCrop()
                .placeholder(R.drawable.loader)
                .into(mascotaViewholder.imgMascota);

        mascotaViewholder.tvLikes.setText(String.valueOf(mascota.getLikes()));
        mascotaViewholder.imgLikes.setImageResource(R.drawable.btn_like_counter);

        if(!thumbnail) {
            mascotaViewholder.tvNombreMascota.setText(mascota.getNombre());
        }

        //Para hacer OnClick:
        mascotaViewholder.imgMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalleMascota.class);
                intent.putExtra("url", mascota.getFotoUrl());
                intent.putExtra("like", mascota.getLikes());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNombreMascota;
        public TextView tvLikes;
        public ImageView imgLikes;
        public ImageView imgMascota;

        public MascotaViewHolder(View itemView, boolean thumbnail) {
            super(itemView);

            tvLikes = (TextView) itemView.findViewById(R.id.tvLikes);
            imgLikes = (ImageView) itemView.findViewById(R.id.imgvLikes);
            imgMascota = (ImageView) itemView.findViewById(R.id.imgvPet);

            if(!thumbnail) {
                tvNombreMascota = (TextView) itemView.findViewById(R.id.tvPetName);
            }
        }
    }

}
