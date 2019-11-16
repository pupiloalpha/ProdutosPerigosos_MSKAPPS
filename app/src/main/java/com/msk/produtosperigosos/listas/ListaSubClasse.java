package com.msk.produtosperigosos.listas;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.msk.produtosperigosos.R;

public class ListaSubClasse extends AppCompatActivity {

    Resources r;
    // ELEMENTOS DA TELA
    private TextView classe, detalhe, numero, subclasse;
    private ListView subclasses;
    private View linhaView;
    private LayoutInflater inflaLista;
    private ImageView rotulo;
    // BUSCADOR DE INFORMACOES DA CLASSE
    private String[] nomeClasse, detClasse, nSubclasse, detSubclasse;
    private int nrClasse;
    private int[] rotuloId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sub_classes);
        r = getResources();

        Iniciador();
        usarActionBar();
        // BUSCA DADOS DA CLASSE DE RISCO
        Bundle envelope = getIntent().getExtras();
        nrClasse = envelope.getInt("nr");
        DefineInfoClasse(nrClasse);
        subclasses.setAdapter(new adapter());

    }

    private void Iniciador() {

        // ELEMENTO DA TELA
        subclasses = findViewById(R.id.lvSubclasse);
        // VETORES COM INFORMACOES DAS CLASSES
        nomeClasse = r.getStringArray(R.array.nr_classe_risco);
        detClasse = r.getStringArray(R.array.detalhe_classe);

    }

    private void DefineInfoClasse(int i) {

        if (i == 0) { // EXPLOSIVOS

            rotuloId = new int[]{R.drawable.rotulo_explosivo,
                    R.drawable.rotulo_explosivo, R.drawable.rotulo_explosivo,
                    R.drawable.rotulo_explosivo_4,
                    R.drawable.rotulo_explosivo_5,
                    R.drawable.rotulo_explosivo_6};
            nSubclasse = r.getStringArray(R.array.nr_sub_classe_1);
            detSubclasse = r.getStringArray(R.array.nome_sub_classe_1);
        }
        if (i == 1) { // GASES

            rotuloId = new int[]{R.drawable.rotulo_gas_inflamavel,
                    R.drawable.rotulo_gas_nao_toxico,
                    R.drawable.rotulo_gas_toxico};
            nSubclasse = r.getStringArray(R.array.nr_sub_classe_2);
            detSubclasse = r.getStringArray(R.array.nome_sub_classe_2);
        }
        if (i == 2) { // LIQUIDOS

            rotuloId = new int[]{R.drawable.rotulo_liquido_inflamavel};
            nSubclasse = new String[]{nomeClasse[i]};
            detSubclasse = new String[]{getResources().getString(
                    R.string.sem_subclasse)};
        }
        if (i == 3) { // SOLIDOS

            rotuloId = new int[]{R.drawable.rotulo_solido_inflamavel,
                    R.drawable.rotulo_combustao_expontanea,
                    R.drawable.rotulo_perigoso_quando_molhado};
            nSubclasse = r.getStringArray(R.array.nr_sub_classe_4);
            detSubclasse = r.getStringArray(R.array.nome_sub_classe_4);
        }
        if (i == 4) { // OXIDOS

            rotuloId = new int[]{R.drawable.rotulo_oxidante,
                    R.drawable.rotulo_peroxido_organico};
            nSubclasse = r.getStringArray(R.array.nr_sub_classe_5);
            detSubclasse = r.getStringArray(R.array.nome_sub_classe_5);
        }
        if (i == 5) { // TOXICOS

            rotuloId = new int[]{R.drawable.rotulo_toxico,
                    R.drawable.rotulo_nocivo,
                    R.drawable.rotulo_substancia_infectante};
            nSubclasse = r.getStringArray(R.array.nr_sub_classe_6);
            detSubclasse = r.getStringArray(R.array.nome_sub_classe_6);
        }
        if (i == 6) { // RADIOATIVO

            rotuloId = new int[]{R.drawable.rotulo_radioativo_veic, R.drawable.rotulo_fissil};
            nSubclasse = new String[]{nomeClasse[i], "Produtos considerados físseis"};
            detSubclasse = new String[]{getResources().getString(
                    R.string.sem_subclasse), "Utilizado para identificar produtos que podem apresentar fissão nuclear"};
        }
        if (i == 7) { // CORROSIVO

            rotuloId = new int[]{R.drawable.rotulo_corrosivo};
            nSubclasse = new String[]{nomeClasse[i]};
            detSubclasse = new String[]{getResources().getString(
                    R.string.sem_subclasse)};
        }
        if (i == 8) { // DIVERSOS

            rotuloId = new int[]{R.drawable.rotulo_substancias_diversas};
            nSubclasse = new String[]{nomeClasse[i]};
            detSubclasse = new String[]{getResources().getString(
                    R.string.sem_subclasse)};
        }

    }

    @SuppressLint("NewApi")
    private void usarActionBar() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    class adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return detSubclasse.length + 3;
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
        public View getView(int nrLinha, View lView, ViewGroup parent) {

            linhaView = lView;
            inflaLista = getLayoutInflater();
            if (nrLinha == 0 || nrLinha == 2) {
                linhaView = inflaLista.inflate(R.layout.nome_classe, null);
                classe = linhaView
                        .findViewById(R.id.tvNomeClasse);
                if (nrLinha == 0)
                    classe.setText(nomeClasse[nrClasse]);
                else
                    classe.setText(r.getString(R.string.subclasses));
            } else if (nrLinha == 1) {
                linhaView = inflaLista.inflate(R.layout.detalhe_classe,
                        null);
                detalhe = linhaView
                        .findViewById(R.id.tvDetalheClasse);
                detalhe.setText(Html.fromHtml("<b>" + r.getString(R.string.dica_nome_classe) + "</b>" + "<br />" + detClasse[nrClasse]));
            } else {
                linhaView = inflaLista.inflate(
                        R.layout.item_subclasse_risco, null);
                rotulo = linhaView
                        .findViewById(R.id.ivRotuloSubClasse);
                numero = linhaView
                        .findViewById(R.id.tvNrSubclasse);
                subclasse = linhaView
                        .findViewById(R.id.tvNomeSubclasse);
                rotulo.setImageResource(rotuloId[nrLinha - 3]);
                numero.setText(nSubclasse[nrLinha - 3]);
                subclasse.setText(detSubclasse[nrLinha - 3]);
            }

            return linhaView;
        }

    }

}
