package br.com.onedreams.projetosalao.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.onedreams.projetosalao.Adapters.CardFeedAdapter;
import br.com.onedreams.projetosalao.Classes.CardFeed;
import br.com.onedreams.projetosalao.Classes.LibraryClass;
import br.com.onedreams.projetosalao.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private List<CardFeed> cardFeedList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CardFeedAdapter mAdapter;
    private Toolbar toolbar;

    Firebase firebase = LibraryClass.getFirebase();

    @Bind(R.id.fabMain)
    FloatingActionButton fabMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        listenerFabMain();

        /*Firebase.setAndroidContext(this);
        mRef = new Firebase("https://bookbeauty.firebaseio.com/postagem");
        Map<String, Object> map = new HashMap<>();
        CardFeed cardFeed = new CardFeed("teste","teste2");
        map.put("mNomeDoComentarista",cardFeed.getmNomeDoComentarista());
        map.put("mMensagemDoComentarista",cardFeed.getmMensagemDoComentarista());
        mRef.push().setValue(map);*/

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new CardFeedAdapter(cardFeedList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        cardFeedList.add( new CardFeed("Joao ferreira", "Trabalho excelente feito por eu, no salão Mega Hair, estou esperando a presença de todos !!!! venham !!!!"));
        cardFeedList.add( new CardFeed("Luiza bernembaut", "Mais um trabalho finalizado !! Lindo, maravilhoso"));
        cardFeedList.add( new CardFeed("Odebratch silva", "Esse trabalho foi feita no salão do tio alvo"));
        mAdapter.notifyDataSetChanged();

        createNavigationDrawer();
        managerClick();


    }

    private void listenerFabMain() {
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CameraActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        /*FirebaseRecyclerAdapter<CardFeed, ChatMessageViewHolder> adapter =
                new FirebaseRecyclerAdapter<CardFeed, ChatMessageViewHolder>(
                        CardFeed.class,
                        R.layout.card_feed_row,
                        ChatMessageViewHolder.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(ChatMessageViewHolder chatMessageViewHolder, CardFeed cardFeed, int i) {
                        chatMessageViewHolder.tvNomeDoComentaristaCardFeed.setText(cardFeed.getmNomeDoComentarista());
                        chatMessageViewHolder.tvMensagemDoComentaristaCardFeed.setText(cardFeed.getmMensagemDoComentarista());
                    }
                };

        recyclerView.setAdapter(adapter);*/

    }

    public static class ChatMessageViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeDoComentaristaCardFeed;
        TextView tvMensagemDoComentaristaCardFeed;

        public ChatMessageViewHolder(View itemView) {
            super(itemView);
            tvNomeDoComentaristaCardFeed = (TextView) itemView.findViewById(R.id.tvNomeDoComentaristaCardFeed);
            tvMensagemDoComentaristaCardFeed = (TextView) itemView.findViewById(R.id.tvMensagemDoComentaristaCardFeed);
        }
    }

    private void managerClick() {
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getApplicationContext(), " Clicou "+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    /**
     * Método para criação e manipulação do navigation drawer
     */

    private void createNavigationDrawer() {

        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("Ademir Bezerra")
                                .withIcon(getResources().getDrawable(R.drawable.person))
                )
                .withDividerBelowHeader(false)
                .withProfileImagesVisible(true)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .withTextColorRes(R.color.textColorPrimary)
                .withHeaderBackground(R.color.colorAccent)
                .build();

        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withToolbar(toolbar)
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withTranslucentStatusBar(true)
                .withActionBarDrawerToggle(true)
                .withSliderBackgroundColorRes(R.color.colorAccent)
                .withSelectedItem(-1)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName("Buscar Serviços")
                                .withTextColorRes(R.color.textColorPrimary)
                                .withIcon(GoogleMaterial.Icon.gmd_content_cut)
                                .withIconColorRes(R.color.textColorPrimary)
                                .withSelectedColor(Color.argb(40, 0, 0, 0))
                                .withSelectedIconColorRes(R.color.colorAccent)
                                .withSelectedTextColorRes(R.color.colorAccent),
                        new PrimaryDrawerItem()
                                .withName("Desenvolvedores")
                                .withTextColorRes(R.color.textColorPrimary)
                                .withIcon(GoogleMaterial.Icon.gmd_people)
                                .withIconColorRes(R.color.textColorPrimary)
                                .withSelectedColor(Color.argb(40, 0, 0, 0))
                                .withSelectedIconColorRes(R.color.colorAccent)
                                .withSelectedTextColorRes(R.color.colorAccent),
                        new PrimaryDrawerItem()
                                .withName("Help")
                                .withTextColorRes(R.color.textColorPrimary)
                                .withIcon(GoogleMaterial.Icon.gmd_error_outline)
                                .withIconColorRes(R.color.textColorPrimary)
                                .withSelectedColor(Color.argb(40, 0, 0, 0))
                                .withSelectedIconColorRes(R.color.colorAccent)
                                .withSelectedTextColorRes(R.color.colorAccent),
                        new PrimaryDrawerItem()
                                .withName("Sobre")
                                .withTextColorRes(R.color.textColorPrimary)
                                .withIcon(GoogleMaterial.Icon.gmd_phone_android)
                                .withIconColorRes(R.color.textColorPrimary)
                                .withSelectedColor(Color.argb(40, 0, 0, 0))
                                .withSelectedIconColorRes(R.color.colorAccent)
                                .withSelectedTextColorRes(R.color.colorAccent)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1: startActivity(new Intent(MainActivity.this, ServicosActivity.class)); break;
                        }

                        return true;
                    }
                })
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.idLogout:
                firebase.unauth();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private MainActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MainActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


}
