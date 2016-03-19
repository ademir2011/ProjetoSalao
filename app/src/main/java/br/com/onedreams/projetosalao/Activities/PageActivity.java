package br.com.onedreams.projetosalao.Activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import br.com.onedreams.projetosalao.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class PageActivity extends AppCompatActivity {

    @Bind(R.id.toolbarActivityPage)
    Toolbar toolbarActivityPage;

    @Bind(R.id.collapsing_toolbar_page)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        ButterKnife.bind(this);

        setSupportActionBar(toolbarActivityPage);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mCollapsingToolbarLayout.setTitle("João da silva");

    }

}
