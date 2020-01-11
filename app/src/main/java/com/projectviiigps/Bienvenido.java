package com.projectviiigps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;


public class Bienvenido extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navView;
    ConstraintLayout expandableView;
    Button arrowBtn;
    CardView cardView;
    private TextView  codigo ;
    Intent intent =getIntent();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        expandableView = findViewById(R.id.expandableView);
        arrowBtn = findViewById(R.id.arrowBtn);
        cardView = findViewById(R.id.cardView);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navView = findViewById(R.id.navview);
        Menu menu = navView.getMenu();

        MenuItem nav_camara = menu.findItem(R.id.menu_seccion_2);
        nav_camara.setVisible(true);
        navView.setNavigationItemSelectedListener(this);


        View headView = navView.getHeaderView(0);
        ImageView imgProfile = headView.findViewById(R.id.profile_image);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Bienvenido.this,Perfil.class);
                startActivity(i);
            }
        });


        arrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (expandableView.getVisibility()==View.GONE){

                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                    // Intent bienvenido = new Intent( Perfil.this, Bienvenido.class);
                    // Perfil.this.startActivity(bienvenido);
                    // Perfil.this.finish();

                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }

            }
        });

       // final TextView mensaje =(TextView)findViewById(R.id.name);
        Intent i = this.getIntent();
       // String nombre = i.getStringExtra("nombre");
      //  int edad = i.getIntExtra("edad", -1);

      //  mensaje.setText(mensaje.getText()+"  " + nombre +" Su edad:" + edad + " ");


        final TextView mensaje =(TextView)findViewById(R.id.name);
        final TextView direccion1 =(TextView)findViewById(R.id.direccion);
        final TextView telefono1 =(TextView)findViewById(R.id.phoneNumber);
        final TextView correo1 =(TextView)findViewById(R.id.mailNumber);
        codigo =(TextView)findViewById(R.id.codigopadre);//esto


        String nombre = i.getStringExtra("nombre");
        String apellido = i.getStringExtra("apellido");
        String direccion = i.getStringExtra("direccion");
        int telefono = i.getIntExtra("telefono", -1);
        String correo = i.getStringExtra("correo");
        int idpadre=i.getIntExtra("idpadre",-1);//esto


        mensaje.setText(mensaje.getText()+"  " + nombre +"" + apellido + " ");
        direccion1.setText(direccion1.getText()+"  " + direccion);
        telefono1.setText(telefono1.getText()+" "+ telefono);
        correo1.setText(correo1.getText()+" "+ correo);
        codigo.setText(codigo.getText()+" "+ idpadre);         //esto

       // i.putExtra("idpadre",idpadre);
        // startActivity(i);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

         if (id == R.id.menu_seccion_1) {
             Intent i = new Intent(Bienvenido.this,Perfil.class);
             startActivity(i);
        } else if (id == R.id.menu_seccion_2) {

             Intent i = new Intent(Bienvenido.this,RegistroHijo.class);
             int idpadre=i.getIntExtra("idpadre",-1);//esto
             i.putExtra("idpadre",idpadre);
             startActivity(i);

        } else if (id == R.id.menu_seccion_3) {
            Intent i = new Intent(Bienvenido.this,localizacion.class);
            startActivity(i);
        } else if (id == R.id.menu_seccion_4) {
            Intent i = new Intent(Bienvenido.this,Reportes.class);
            startActivity(i);
    }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
}

private void lanzarcodigo ()
{
    Intent intent = new Intent(this,RegistroHijo.class);
    intent.putExtra("idpadre",codigo.getText());
    startActivity(intent);
}





}