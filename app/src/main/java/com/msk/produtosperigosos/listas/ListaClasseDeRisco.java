package com.msk.produtosperigosos.listas;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.ListFragment;

import com.msk.produtosperigosos.R;

public class ListaClasseDeRisco extends ListFragment {

    View tela;
    Resources r;
    private String[] nrClasse;
    private int[] rotuloId;
    private TextView classe;
    private View linhaView;
    private LayoutInflater inflaLista;
    private ImageView rotulo;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        r = getResources();

        nrClasse = r.getStringArray(R.array.classe_risco);

        rotuloId = new int[]{R.drawable.rotulo_explosivo,
                R.drawable.rotulo_gas_inflamavel,
                R.drawable.rotulo_liquido_inflamavel,
                R.drawable.rotulo_solido_inflamavel,
                R.drawable.rotulo_oxidante, R.drawable.rotulo_toxico,
                R.drawable.rotulo_radioativo_veic, R.drawable.rotulo_corrosivo,
                R.drawable.rotulo_substancias_diversas};

        setListAdapter(new adapter());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tela = inflater.inflate(R.layout.lista_classe_risco, container, false);

        return tela;
    }

    @Override
    public void onListItemClick(ListView l, View v, int posicao, long id) {

        Bundle envelope = new Bundle();
        envelope.putInt("nr", posicao);
        Intent atividade = new Intent("com.msk.produtosperigosos.CLASSE");
        atividade.putExtras(envelope);
        getActivity().startActivity(atividade);

    }

    class adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return rotuloId.length;
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            linhaView = convertView;
            inflaLista = getActivity().getLayoutInflater();

            linhaView = inflaLista.inflate(R.layout.item_classe_risco, null);
            rotulo = linhaView.findViewById(R.id.ivRotulo);
            classe = linhaView.findViewById(R.id.tvClasseRotulo);

            rotulo.setImageResource(rotuloId[position]);
            classe.setText(nrClasse[position]);

            return linhaView;
        }

    }
}
