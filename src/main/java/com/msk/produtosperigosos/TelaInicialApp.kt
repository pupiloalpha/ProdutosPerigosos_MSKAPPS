package com.msk.produtosperigosos

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.WindowCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.msk.produtosperigosos.listas.ListaClasseDeRisco
import com.msk.produtosperigosos.listas.ListaInfracoes
import com.msk.produtosperigosos.listas.ListaNrDeRisco
import com.msk.produtosperigosos.listas.PesquisaProduto
import com.msk.produtosperigosos.telas.InicioFragment
import com.msk.produtosperigosos.telas.RotulosProdutos
import com.msk.produtosperigosos.telas.SobreApp
import com.msk.produtosperigosos.telas.TelefonesUteis

class TelaInicialApp : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.tela_inicial)

        drawerLayout = findViewById(R.id.drawer_layout)
        // Set the status bar scrim color to transparent - This line is now redundant as we handle insets manually
        // drawerLayout.setStatusBarBackgroundColor(Color.TRANSPARENT)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Handle window insets for the main content layout
        val mainContentLayout = findViewById<View>(R.id.main_content_layout)
        ViewCompat.setOnApplyWindowInsetsListener(mainContentLayout) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null) {
            openFragment(InicioFragment())
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    if (supportFragmentManager.backStackEntryCount > 0) {
                        supportFragmentManager.popBackStack()
                    }
                    else {
                        finish()
                    }
                }
            }
        })
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_rotulos -> openFragment(RotulosProdutos())
            R.id.nav_classes -> openFragment(ListaClasseDeRisco())
            R.id.nav_numero_risco -> openFragment(ListaNrDeRisco())
            R.id.nav_pesquisa -> openFragment(PesquisaProduto())
            R.id.nav_infracoes -> openFragment(ListaInfracoes())
            R.id.nav_telefones -> openFragment(TelefonesUteis())
            R.id.nav_sobre -> openFragment(SobreApp())
        }
        supportActionBar?.title = item.title
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}