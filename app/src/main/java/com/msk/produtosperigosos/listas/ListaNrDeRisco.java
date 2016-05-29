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

public class ListaNrDeRisco extends ListFragment {

    SimpleAdapter adapter;
    List<HashMap<String, String>> listaCompleta;
    HashMap<String, String> hm;
    Resources r = null;
    private String[] nrRisco, nomeRisco, itemlinha;
    private int[] linhaLista;
    private View tela;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        listaCompleta = new ArrayList<HashMap<String, String>>();

        r = getResources();

        nrRisco = r.getStringArray(R.array.nr_risco_codigo);
        nomeRisco = r.getStringArray(R.array.nr_risco_nome);

        for (int i = 0; i < nrRisco.length; i++) {
            hm = new HashMap<String, String>();
            hm.put("nr", nrRisco[i]);
            hm.put("nome", nomeRisco[i]);
            listaCompleta.add(hm);
        }

        itemlinha = new String[]{"nr", "nome"};

        linhaLista = new int[]{R.id.tvNrRisco, R.id.tvNomeNrRisco};

        adapter = new SimpleAdapter(getActivity(), listaCompleta, R.layout.item_nr_risco, itemlinha, linhaLista);

        setListAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tela = inflater.inflate(R.layout.lista_nr_risco, container, false);

        return tela;
    }

    @Override
    public void onListItemClick(ListView l, View v, int posicao, long id) {

        // ACAO QUANDO ALGUM ITEM FOR CLICADO

    }


}
