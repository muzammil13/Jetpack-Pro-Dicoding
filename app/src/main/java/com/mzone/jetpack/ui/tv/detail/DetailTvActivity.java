package com.mzone.jetpack.ui.tv.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.mzone.jetpack.R;
import com.mzone.jetpack.data.source.local.entity.TvEntity;
import com.mzone.jetpack.viewmodel.ViewModelFactory;

import static com.mzone.jetpack.BuildConfig.URL_COVER;

public class DetailTvActivity extends AppCompatActivity {

    public static final String EXTRA_ID_TV = "extra_id_tv";

    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView img;
    private TextView overview, date, original;
    private DetailTvViewModel viewModel;
    private ProgressBar pbDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();

        viewModel = obtainViewModel(this);

        if (savedInstanceState == null) {
            pbDetail.setVisibility(View.VISIBLE);
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String ID = extras.getString(EXTRA_ID_TV);
                if (ID != null) {
                    viewModel.setTvId(ID);
                }
            }

        }
        loadTvById();
    }

    private DetailTvViewModel obtainViewModel(DetailTvActivity detailTvActivity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(detailTvActivity.getApplication());
        return ViewModelProviders.of(detailTvActivity, factory).get(DetailTvViewModel.class);
    }

    private void loadTvById() {
        viewModel.getTvById().observe(this, tvResult -> {
            if (tvResult != null) {
                pbDetail.setVisibility(View.GONE);
                updateUI(tvResult);
            } else {
                pbDetail.setVisibility(View.GONE);
                Toast.makeText(this, getResources().getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(TvEntity tvById) {
        final String title = String.format("%s (%s)", tvById.getTitleTv(), tvById.getFirstAirDateTv().substring(0, 4));
        Glide.with(this)
                .load(URL_COVER + tvById.getPosterPathTv())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(img);
        overview.setText(tvById.getOverviewTv());
        original.setText(tvById.getOriginalTitleTv());
        date.setText(tvById.getFirstAirDateTv());
        collapsingToolbar.setTitle(title);
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        pbDetail = findViewById(R.id.pb_detail);
        collapsingToolbar = findViewById(R.id.collapsing);
        img = findViewById(R.id.detail_image);
        overview = findViewById(R.id.detail_overview);
        date = findViewById(R.id.detail_release);
        original = findViewById(R.id.detail_original);
    }
}
