package com.example.sasham.itmeans.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sasham.itmeans.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IDefinitionView {
    @BindView(R.id.tv_definition)
    TextView mDefinitionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        DefinitionPresenter presenter=new DefinitionPresenter(this);
        presenter.init();
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
