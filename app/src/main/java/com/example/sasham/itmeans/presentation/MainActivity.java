package com.example.sasham.itmeans.presentation;

import android.support.v4.view.ViewGroupCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sasham.itmeans.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IDefinitionView {
    @BindView(R.id.tv_definition)
    TextView mDefinitionView;

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_base);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        final DefinitionPresenter presenter=new DefinitionPresenter(this);

        //TODO delete
        button.setText("GET MEANT");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.init();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main,menu);

        return true;
    }

    @Override
    public void showDefinition(String s) {
        mDefinitionView.setText(s);
    }

    @Override
    public void showError() {
        mDefinitionView.setText("Error");
    }
}
