package org.gdg_lome.codelab_cloudbackend;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.firebase.ui.FirebaseRecyclerViewAdapter;

import org.gdg_lome.codelab_cloudbackend.model.Matiere;

/**
 * programme scolaire : liste des matieres :)
 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView list;
    private FirebaseRecyclerViewAdapter<Matiere,MatiereHolder> adapter;
    ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (RecyclerView)findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setIndeterminate(true);
        dialog.setMessage("Chargement...");
        dialog.setCancelable(false);
        dialog.show();
        adapter = new FirebaseRecyclerViewAdapter<Matiere,MatiereHolder>(Matiere.class,R.layout.items, MatiereHolder.class,App.backend.child(Config.PROGRAMME_CHILD)){
            @Override
            public void populateViewHolder(final MatiereHolder matiereHolder, final Matiere matiere) {
                if(dialog.isShowing())
                    dialog.dismiss();
                matiereHolder.nom.setText(matiere.getNom());
                matiereHolder.description.setText(matiere.getDescription());
                Glide.with(MainActivity.this)
                        .load(matiere.getLogo())
                        .centerCrop()
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                matiereHolder.progress.setVisibility(View.GONE);
                                return false;
                            }
                        }) .into(matiereHolder.logo);

                matiereHolder.setClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent i = new Intent(MainActivity.this,DetailActivity.class);
                        i.putExtra(Config.MATIERE_EXTRA,matiere);
                        startActivity(i);
                    }
                });
            }
        };
        list.setAdapter(adapter);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
