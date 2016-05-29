package com.msk.produtosperigosos.info;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.msk.produtosperigosos.R;

public class Simbologia extends Fragment {

    private View tela;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tela = inflater.inflate(R.layout.simbologia, container, false);

        return tela;
    }


}
