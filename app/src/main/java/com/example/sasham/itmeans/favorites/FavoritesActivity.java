package com.example.sasham.itmeans.favorites;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.sasham.itmeans.BR;
import com.example.sasham.itmeans.R;
import com.example.sasham.itmeans.adapter.BaseRecyclerAdapter;
import com.example.sasham.itmeans.data.db.model.FavoriteWord;
import com.example.sasham.itmeans.search.SearchActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class FavoritesActivity extends AppCompatActivity {


    @BindView(R.id.favorites_list)
    RecyclerView favoritesList;

    @Inject
    public FavoritesViewModel.Factory factory;

    private FavoritesViewModel favoritesViewModel;
    private BaseRecyclerAdapter<FavoriteWord> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        setTitle(getString(R.string.favorites_activity_title));
        ButterKnife.bind(this);

        favoritesViewModel = ViewModelProviders.of(this, factory).get(FavoritesViewModel.class);

        adapter = new BaseRecyclerAdapter<>(R.layout.favorites_item, BR.favorite);
        favoritesList.setLayoutManager(new LinearLayoutManager(this));

        setFavoriteWords();

        adapter.setListener(new BaseRecyclerAdapter.Listener<FavoriteWord>() {
            @Override
            public void onItemClick(View itemRoot, FavoriteWord item, int position) {
                Intent intent = new Intent(FavoritesActivity.this, SearchActivity.class);
                intent.putExtra(SearchActivity.STRING_WORD_EXTRA, item.getEntry());
                intent.setAction(SearchActivity.SEARCH_WORD_ACTION);
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

    private void setFavoriteWords() {
        adapter.setItems(favoritesViewModel.favoriteWords());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_saved_words, menu);
        MenuItem deleteAll = menu.findItem(R.id.delete_all);
        deleteAll.setOnMenuItemClickListener(showDeleteDialog);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        favoritesViewModel.dispose();
    }


    MenuItem.OnMenuItemClickListener showDeleteDialog = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            AlertDialog.Builder deleteDialogBuilder = new AlertDialog.Builder(FavoritesActivity.this);
            deleteDialogBuilder.setTitle(getString(R.string.warning_title));
            deleteDialogBuilder.setMessage(getString(R.string.delete_all_favorites_words_message));

            deleteDialogBuilder.setPositiveButton(getString(R.string.answer_yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    favoritesViewModel.removeAllFavoriteWords();
                    setFavoriteWords();
                }
            });

            deleteDialogBuilder.setNegativeButton(getString(R.string.answer_no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            deleteDialogBuilder.show();

            return true;
        }
    };
}
