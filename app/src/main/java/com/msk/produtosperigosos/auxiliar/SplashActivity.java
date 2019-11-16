package com.msk.produtosperigosos.auxiliar;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.msk.produtosperigosos.ProdutosPerigosos;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, ProdutosPerigosos.class);
        startActivity(intent);
        finish();
    }
}
