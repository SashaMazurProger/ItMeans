package com.example.sasham.itmeans.favorites;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.sasham.itmeans.BR;
import com.example.sasham.itmeans.BaseApplication;
import com.example.sasham.itmeans.R;
import com.example.sasham.itmeans.adapter.BaseRecyclerAdapter;
import com.example.sasham.itmeans.data.network.db.FavoriteWord;
import com.example.sasham.itmeans.search.SearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesActivity extends AppCompatActivity {


    @BindView(R.id.favorites_list)
    RecyclerView favoritesList;


    private FavoritesViewModel favoritesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        ButterKnife.bind(this);

        favoritesViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);

        BaseApplication.get(this)
                .createFavoritesComponent()
                .inject(favoritesViewModel);

        final BaseRecyclerAdapter<FavoriteWord> adapter=new BaseRecyclerAdapter<>(R.layout.favorites_item, BR.favorite);
        favoritesList.setLayoutManager(new LinearLayoutManager(this));
        adapter.setItems(favoritesViewModel.favoriteWords());
        adapter.setListener(new BaseRecyclerAdapter.Listener<FavoriteWord>() {
            @Override
            public void onItemClick(View itemRoot, FavoriteWord item, int position) {
                Intent intent=new Intent(FavoritesActivity.this, SearchActivity.class);
                intent.putExtra(SearchActivity.STRING_WORD_EXTRA,item.getEntry());
                intent.setAction(SearchActivity.SEARCH_WORD);
                startActivity(intent);
            }

            @Override
            public boolean onLongItemClick(View v, FavoriteWord favoriteWord, int position) {
//                favoritesViewModel.removeFavoriteWord(favoriteWord);
//                adapter.setItems(favoritesViewModel.favoriteWords());
                return false;
            }
        });
        favoritesList.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.get(this).releaseFavoritesComponent();
    }
}
