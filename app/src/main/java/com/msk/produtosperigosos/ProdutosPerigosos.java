package com.msk.produtosperigosos;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.msk.produtosperigosos.info.Simbologia;
import com.msk.produtosperigosos.info.SobreApp;
import com.msk.produtosperigosos.info.TelaInicial;
import com.msk.produtosperigosos.info.TelefonesUteis;
import com.msk.produtosperigosos.listas.ListaClasseDeRisco;
import com.msk.produtosperigosos.listas.ListaInfracoes;
import com.msk.produtosperigosos.listas.ListaNrDeRisco;
import com.msk.produtosperigosos.listas.PesquisaProduto;

public class ProdutosPerigosos extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Resources r = null;
    private FragmentManager fm;
    private Fragment fragmento;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_navegador);
        Toolbar toolbar = findViewById(R.id.toolbar);
        r = getResources();

        drawer = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);

        fm = getSupportFragmentManager();

        Iniciador();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_rotulos) {
            fragmento = new Simbologia();
            getSupportActionBar().setTitle(r.getString(R.string.menu_rotulos));
        } else if (id == R.id.nav_classes) {
            fragmento = new ListaClasseDeRisco();
            getSupportActionBar().setTitle(r.getString(R.string.menu_classes));
        } else if (id == R.id.nav_numero_risco) {
            fragmento = new ListaNrDeRisco();
            getSupportActionBar().setTitle(r.getString(R.string.menu_numeros));
        } else if (id == R.id.nav_infracoes) {
            fragmento = new ListaInfracoes();
            getSupportActionBar().setTitle(r.getString(R.string.menu_infracoes));
        } else if (id == R.id.nav_pesquisa) {
            fragmento = new PesquisaProduto();
            getSupportActionBar().setTitle(r.getString(R.string.menu_pesquisa));
        } else if (id == R.id.nav_telefones) {
            fragmento = new TelefonesUteis();
            getSupportActionBar().setTitle(r.getString(R.string.menu_telefones));
        } else if (id == R.id.nav_sobre) {
            fragmento = new SobreApp();
            getSupportActionBar().setTitle(r.getString(R.string.menu_sobre));
            navigationView.getMenu().findItem(R.id.nav_rotulos).setChecked(false);
            navigationView.getMenu().findItem(R.id.nav_classes).setChecked(false);
            navigationView.getMenu().findItem(R.id.nav_numero_risco).setChecked(false);
            navigationView.getMenu().findItem(R.id.nav_infracoes).setChecked(false);
            navigationView.getMenu().findItem(R.id.nav_pesquisa).setChecked(false);
            navigationView.getMenu().findItem(R.id.nav_telefones).setChecked(false);
        }

        fm.beginTransaction().replace(R.id.tela, fragmento).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void Iniciador() {
        // DEFINE OS ITENS DA TELA

        fragmento = new TelaInicial();
        fm.beginTransaction().replace(R.id.tela, fragmento).commit();

        navigationView.getMenu().findItem(R.id.nav_rotulos).setChecked(false);
        navigationView.getMenu().findItem(R.id.nav_classes).setChecked(false);
        navigationView.getMenu().findItem(R.id.nav_numero_risco).setChecked(false);
        navigationView.getMenu().findItem(R.id.nav_infracoes).setChecked(false);
        navigationView.getMenu().findItem(R.id.nav_pesquisa).setChecked(false);
        navigationView.getMenu().findItem(R.id.nav_telefones).setChecked(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        drawer = findViewById(R.id.drawer_layout);
        getSupportActionBar().setTitle(r.getString(R.string.app_name));

        if (keyCode == KeyEvent.KEYCODE_MENU) {
            drawer.closeDrawer(GravityCompat.START);
        }

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Iniciador();
            if (!drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
                Dialogo();
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    private void Dialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.dica_sair);
        builder.setMessage(R.string.texto_sair);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                finish();
            }
        });
        builder.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int id) {
                        dialogo.dismiss();
                        Iniciador();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = builder.create();
        // show it
        alertDialog.show();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
        Iniciador();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
        Iniciador();
    }


}
