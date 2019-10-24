package com.mzone.jetpack.ui.tv;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mzone.jetpack.R;
import com.mzone.jetpack.data.source.local.entity.TvEntity;
import com.mzone.jetpack.ui.tv.detail.DetailTvActivity;

import java.util.ArrayList;
import java.util.List;

import static com.mzone.jetpack.BuildConfig.URL_COVER;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder>  {

    private Context context;
    private List<TvEntity> tv = new ArrayList<>();

    TvAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, int position) {
        holder.title.setText(tv.get(position).getTitleTv());
        holder.first.setText(tv.get(position).getFirstAirDateTv());
        holder.overview.setText(tv.get(position).getOverviewTv());
        Glide.with(context)
                .load(URL_COVER + tv.get(position).getPosterPathTv())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.imgPhoto);

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailTvActivity.class);
            intent.putExtra(DetailTvActivity.EXTRA_ID_TV, tv.get(position).getIdTv());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tv.size();
    }

    void setDataTV(List<TvEntity> tv) {
        if (tv == null) return;
        this.tv.clear();
        this.tv = tv;
        notifyDataSetChanged();
    }

    class TvViewHolder extends RecyclerView.ViewHolder {

        TextView title, first, overview;
        ImageView imgPhoto;
        CardView cardView;

        TvViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.movie_image);
            title = itemView.findViewById(R.id.item_title);
            first = itemView.findViewById(R.id.item_genres);
            overview = itemView.findViewById(R.id.item_overview);
            cardView = itemView.findViewById(R.id.cardParent);
        }
    }
}
