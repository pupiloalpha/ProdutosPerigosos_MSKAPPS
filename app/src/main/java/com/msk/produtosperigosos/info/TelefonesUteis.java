package com.msk.produtosperigosos.info;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.msk.produtosperigosos.R;

public class TelefonesUteis extends Fragment implements OnClickListener {

    Button n190, n191, n192, n193, n166, n0800, n199;
    Intent atividade;
    String numero = "tel:193";
    View tela;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tela = inflater.inflate(R.layout.telefones, container, false);

        n190 = (Button) tela.findViewById(R.id.b190);
        n191 = (Button) tela.findViewById(R.id.b191);
        n192 = (Button) tela.findViewById(R.id.b192);
        n193 = (Button) tela.findViewById(R.id.b193);
        n166 = (Button) tela.findViewById(R.id.b166);
        n199 = (Button) tela.findViewById(R.id.b199);
        n0800 = (Button) tela.findViewById(R.id.b0800);

        n190.setOnClickListener(this);
        n191.setOnClickListener(this);
        n192.setOnClickListener(this);
        n193.setOnClickListener(this);
        n199.setOnClickListener(this);
        n166.setOnClickListener(this);
        n0800.setOnClickListener(this);

        return tela;

    }

    @Override
    public void onClick(View botao) {

        switch (botao.getId()) {

            case R.id.b190:
                numero = "tel:190";
                break;
            case R.id.b191:
                numero = "tel:191";
                break;
            case R.id.b192:
                numero = "tel:192";
                break;
            case R.id.b193:
                numero = "tel:193";
                break;
            case R.id.b166:
                numero = "tel:166";
                break;
            case R.id.b199:
                numero = "tel:199";
                break;
            case R.id.b0800:
                numero = "tel:08006440199";
                break;
        }

        atividade = new Intent(Intent.ACTION_DIAL);
        atividade.setData(Uri.parse(numero));
        getActivity().startActivity(atividade);

    }

}
