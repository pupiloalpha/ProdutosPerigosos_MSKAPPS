package com.msk.produtosperigosos.listas;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.msk.produtosperigosos.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PesquisaProduto extends Fragment implements OnItemClickListener,
        TextWatcher {

    List<HashMap<String, String>> listaCompleta;
    HashMap<String, String> hm;
    Resources r = null;
    private AppCompatEditText textoPesquisado;
    private ListView listaProdutos;
    private SimpleAdapter adapter;
    private View tela;
    private String[] nrONU, nomeProduto, dadosProduto, itemlinha;
    private int[] linhaLista;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textoPesquisado = (AppCompatEditText) tela.findViewById(R.id.etNomePesquisado);
        listaProdutos = (ListView) tela
                .findViewById(R.id.lvProdutosPesquisados);

        listaCompleta = new ArrayList<HashMap<String, String>>();

        r = getResources();

        nrONU = r.getStringArray(R.array.nr_onu);
        nomeProduto = r.getStringArray(R.array.nome_produto);
        dadosProduto = r.getStringArray(R.array.dados_produto);

        for (int i = 0; i < nrONU.length; i++) {
            hm = new HashMap<String, String>();
            hm.put("nr", nrONU[i]);
            hm.put("nome", nomeProduto[i]);
            hm.put("dados", dadosProduto[i]);
            listaCompleta.add(hm);
        }

        itemlinha = new String[]{"nr", "nome"};

        linhaLista = new int[]{R.id.tvNrONU, R.id.tvNomeProduto};

        adapter = new SimpleAdapter(getActivity(), listaCompleta,
                R.layout.item_produto, itemlinha, linhaLista);

        listaProdutos.setAdapter(adapter);
        listaProdutos.setOnItemClickListener(this);

        textoPesquisado.addTextChangedListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        tela = inflater.inflate(R.layout.pesquisa_produtos, container, false);

        return tela;
    }

    @Override
    public void onItemClick(AdapterView<?> adaptador, View v, int posicao,
                            long arg3) {

        hm = (HashMap) adaptador.getItemAtPosition(posicao);

        String b = hm.get("nr");
        String c = hm.get("nome");
        String d = hm.get("dados");
        int j = 0;
        while (!b.equals(nrONU[j]) && j < nrONU.length) {
            j++;
        }
        int k = 0;
        while (!c.equals(nomeProduto[k]) && k < nomeProduto.length) {
            k++;
        }
        int l = 0;
        while (!d.equals(dadosProduto[l]) && l < dadosProduto.length) {
            l++;
        }
        int jk = 0;
        if (j > k) {
            if (l > j)
                jk = l;
            else
                jk = j;
        } else {
            if (l > k)
                jk = l;
            else
                jk = k;
        }
        Bundle envelope = new Bundle();
        envelope.putInt("nr", jk);
        Intent atividade = new Intent(
                "com.msk.produtosperigosos.PRODUTO");
        atividade.putExtras(envelope);
        getActivity().startActivity(atividade);

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

        PesquisaProduto.this.adapter.getFilter().filter(s);
    }

}
