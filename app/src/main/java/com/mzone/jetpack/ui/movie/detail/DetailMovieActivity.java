package com.mzone.jetpack.ui.movie.detail;

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
import com.mzone.jetpack.data.source.local.entity.MovieEntity;
import com.mzone.jetpack.viewmodel.ViewModelFactory;

import static com.mzone.jetpack.BuildConfig.URL_COVER;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_ID_MOVIE = "extra_id_movie";

    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView img;
    private TextView overview, date, original;
    private DetailMovieViewModel viewModel;
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
                String ID = extras.getString(EXTRA_ID_MOVIE);
                if (ID != null) {
                    viewModel.setMovieId(ID);
                }
            }

        }
        loadMovieById();
    }

    private void loadMovieById() {
        viewModel.getMovieById().observe(this, movieResult -> {
            if (movieResult != null) {
                pbDetail.setVisibility(View.GONE);
                updateUI(movieResult);
            } else {
                pbDetail.setVisibility(View.GONE);
                Toast.makeText(this, getResources().getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(MovieEntity movieById) {
        final String title = String.format("%s (%s)", movieById.getTitleMovie(), movieById.getReleaseDateMovie().substring(0, 4));
        Glide.with(this)
                .load(URL_COVER + movieById.getPosterPathMovie())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(img);
        overview.setText(movieById.getOverviewMovie());
        original.setText(movieById.getOriginalTitleMovie());
        date.setText(movieById.getReleaseDateMovie());
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

    private DetailMovieViewModel obtainViewModel(DetailMovieActivity detailMovieActivity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(detailMovieActivity.getApplication());
        return ViewModelProviders.of(detailMovieActivity, factory).get(DetailMovieViewModel.class);
    }
}
