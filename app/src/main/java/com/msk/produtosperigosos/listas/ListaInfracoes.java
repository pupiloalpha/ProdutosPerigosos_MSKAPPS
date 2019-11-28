package com.msk.produtosperigosos.listas;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.ListFragment;

import com.msk.produtosperigosos.R;
import com.msk.produtosperigosos.info.Infracoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListaInfracoes extends ListFragment implements TextWatcher {

    SimpleAdapter adapter;
    List<HashMap<String, String>> listaCompleta;
    HashMap<String, String> hm;
    Resources r = null;
    private AppCompatEditText textoPesquisado;
    private String[] itemlinha;
    private int[] linhaLista;
    private View tela;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textoPesquisado = tela.findViewById(R.id.etNomePesquisado);
        listaCompleta = new ArrayList<HashMap<String, String>>();

        r = getResources();

        for (int i = 0; i < Infracoes.codigoInfacao.length; i++) {
            hm = new HashMap<String, String>();
            hm.put("codigo", Infracoes.codigoInfacao[i]);
            hm.put("amparo", Infracoes.amparoInfacao[i]);
            hm.put("descricao", Infracoes.descricaoInfacao[i]);
            listaCompleta.add(hm);
        }

        itemlinha = new String[]{"codigo", "amparo", "descricao"};

        linhaLista = new int[]{R.id.tvCodigo, R.id.tvAmparo, R.id.tvInfracao};

        adapter = new SimpleAdapter(getActivity(), listaCompleta, R.layout.item_infracao, itemlinha, linhaLista);

        setListAdapter(adapter);

        textoPesquisado.addTextChangedListener(this);

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

    @Override
    public void afterTextChanged(Editable arg0) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        ListaInfracoes.this.adapter.getFilter().filter(s);
    }


}
