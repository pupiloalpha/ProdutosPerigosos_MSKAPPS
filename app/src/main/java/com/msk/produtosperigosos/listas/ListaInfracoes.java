package com.msk.produtosperigosos.listas;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.msk.produtosperigosos.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListaInfracoes extends ListFragment {

    SimpleAdapter adapter;
    List<HashMap<String, String>> listaCompleta;
    HashMap<String, String> hm;
    Resources r = null;
    private String[] codigo, amparo, descricao, itemlinha;
    private int[] linhaLista;
    private View tela;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        listaCompleta = new ArrayList<HashMap<String, String>>();

        r = getResources();

        codigo = r.getStringArray(R.array.codigo_infracao);
        amparo = r.getStringArray(R.array.amparo_infracao);
        descricao = r.getStringArray(R.array.descricao_infracao);

        for (int i = 0; i < codigo.length; i++) {
            hm = new HashMap<String, String>();
            hm.put("codigo", codigo[i]);
            hm.put("amparo", amparo[i]);
            hm.put("descricao", descricao[i]);
            listaCompleta.add(hm);
        }

        itemlinha = new String[]{"codigo", "amparo", "descricao"};

        linhaLista = new int[]{R.id.tvCodigo, R.id.tvAmparo, R.id.tvInfracao};

        adapter = new SimpleAdapter(getActivity(), listaCompleta, R.layout.item_infracao, itemlinha, linhaLista);

        setListAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tela = inflater.inflate(R.layout.lista_infracoes, container, false);

        return tela;
    }

    @Override
    public void onListItemClick(ListView l, View v, int posicao, long id) {

        // ACAO QUANDO ALGUM ITEM FOR CLICADO

    }


}
