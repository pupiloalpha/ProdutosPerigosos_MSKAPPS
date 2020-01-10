package com.msk.produtosperigosos.info;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.msk.produtosperigosos.R;

public class DetalheProduto extends AppCompatActivity {

    Resources r;
    // ELEMENTOD DA TELA
    private TextView nrONU, nrClasse, nrRisco, nomeProduto, nomeClasse, nomeRisco, saude, Incêndio, nivelProtecao, isolamento, evacuacao;
    private ImageView rotulo;
    // VETORES COM DADOS DOS PRODUTOS
    private String[] onu, produto, dados;
    private int[] idRotulo;
    private String nclasse, nrisco;
    // VARIAVES QUE SERAO UTILIZADAS
    private int nProduto, guia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detalhe_produto);
        r = getResources();
        // RECEBE NUMERO PRODUTO
        Bundle envelope = getIntent().getExtras();
        nProduto = envelope.getInt("nr");

        Inicando();
        usarActionBar();
        BuscaInfoProduto(nProduto);
        MostraInfoProduto(nProduto);
        DefineRotuloClasse();
    }

    private void Inicando() {

        // ELEMENTOS QUE SERAO EXIBIDOS NA TELA
        nrONU = findViewById(R.id.tvNrONU);
        nrClasse = findViewById(R.id.tvNrClasse);
        nrRisco = findViewById(R.id.tvNrRisco);
        nomeProduto = findViewById(R.id.tvNomeProduto);
        nomeClasse = findViewById(R.id.tvNomeClasse);
        nomeRisco = findViewById(R.id.tvNomeRisco);
        rotulo = findViewById(R.id.ivRotuloRisco);
        saude = findViewById(R.id.tvPerigoSaude);
        Incêndio = findViewById(R.id.tvPerigoIncendio);
        nivelProtecao = findViewById(R.id.tvNivelProtecao);
        isolamento = findViewById(R.id.tvIsolamento);
        evacuacao = findViewById(R.id.tvEvacuacao);


        // BUSCA DETALHES NO ARQUIVO XML
        onu = r.getStringArray(R.array.nr_onu);
        produto = r.getStringArray(R.array.nome_produto);
        dados = r.getStringArray(R.array.dados_produto);

        // DEFINE OS ROTULOS QUE SERAO EXIBIDOS
        idRotulo = new int[]{
                R.drawable.rotulo_explosivo, // 1.1, 1.2, 1.3
                R.drawable.rotulo_explosivo_4, // 1.4
                R.drawable.rotulo_explosivo_5, // 1.5
                R.drawable.rotulo_explosivo_6, // 1.6
                R.drawable.rotulo_gas_inflamavel, // 2.1
                R.drawable.rotulo_gas_nao_toxico, // 2.2
                R.drawable.rotulo_gas_toxico, // 2.3
                R.drawable.rotulo_liquido_inflamavel, // 3
                R.drawable.rotulo_solido_inflamavel, // 4.1
                R.drawable.rotulo_combustao_expontanea, // 4.2
                R.drawable.rotulo_perigoso_quando_molhado, // 4.3
                R.drawable.rotulo_oxidante, // 5.1
                R.drawable.rotulo_peroxido_organico, // 5.2
                R.drawable.rotulo_toxico, // 6.1
                R.drawable.rotulo_nocivo, // 6.1*
                R.drawable.rotulo_substancia_infectante, // 6.2
                R.drawable.rotulo_radioativo_veic, // 7
                R.drawable.rotulo_corrosivo, // 8
                R.drawable.rotulo_substancias_diversas // 9
        };

    }

    private void BuscaInfoProduto(int nProduto) {


        String a = ",";
        String[] array = dados[nProduto].split(a);

        guia = Integer.parseInt(array[0]);
        nclasse = array[1];
        nrisco = array[2];

    }

    private void MostraInfoProduto(int i) {

        nrONU.setText(r.getString(R.string.dica_nr_onu, onu[i]));
        nomeProduto.setText(produto[i]);

        if (nrisco.equals("0")) {
            nrRisco.setVisibility(View.GONE);
            nomeRisco.setText(Guias.nomeGuia[guia]);
        } else {
            nrRisco.setText(r.getString(R.string.dica_nr_risco, nrisco));
            int j = 0;
            while (!nrisco.equals(Riscos.nrRisco[j]) && j < Riscos.nrRisco.length) {
                j++;
            }
            nomeRisco.setText(Riscos.nomeRisco[j]);
        }

        nrClasse.setText(r.getString(R.string.dica_nr_classe, nclasse));

        saude.setText(Html.fromHtml(Guias.riscoSaude[guia]));
        Incêndio.setText(Html.fromHtml(Guias.riscoIncêndio[guia]));
        nivelProtecao.setText(Html.fromHtml(Guias.epi[guia]));
        isolamento.setText(Html.fromHtml(Guias.isola[guia]));
        evacuacao.setText(Html.fromHtml(Guias.evacua[guia]));
    }


    private void DefineRotuloClasse() {

        if (nclasse.equals("9")) {
            rotulo.setImageResource(idRotulo[18]);
            nomeClasse.setText(Riscos.classeRisco[21]);
        }
        if (nclasse.equals("8")) {
            rotulo.setImageResource(idRotulo[17]);
            nomeClasse.setText(Riscos.classeRisco[20]);
        }
        if (nclasse.equals("7")) {
            rotulo.setImageResource(idRotulo[16]);
            nomeClasse.setText(Riscos.classeRisco[19]);
        }
        if (nclasse.equals("6.2")) {
            rotulo.setImageResource(idRotulo[15]);
            nomeClasse.setText(Riscos.classeRisco[18]);
        }
        if (nclasse.equals("6.1")) {
            rotulo.setImageResource(idRotulo[13]);
            nomeClasse.setText(Riscos.classeRisco[17]);
        }
        if (nclasse.equals("5.2")) {
            rotulo.setImageResource(idRotulo[12]);
            nomeClasse.setText(Riscos.classeRisco[16]);
        }
        if (nclasse.equals("5.1")) {
            rotulo.setImageResource(idRotulo[11]);
            nomeClasse.setText(Riscos.classeRisco[15]);
        }
        if (nclasse.equals("4.3")) {
            rotulo.setImageResource(idRotulo[10]);
            nomeClasse.setText(Riscos.classeRisco[14]);
        }
        if (nclasse.equals("4.2")) {
            rotulo.setImageResource(idRotulo[9]);
            nomeClasse.setText(Riscos.classeRisco[13]);
        }
        if (nclasse.equals("4.1")) {
            rotulo.setImageResource(idRotulo[8]);
            nomeClasse.setText(Riscos.classeRisco[12]);
        }
        if (nclasse.equals("3")) {
            rotulo.setImageResource(idRotulo[7]);
            nomeClasse.setText(Riscos.classeRisco[11]);
        }
        if (nclasse.equals("2.3")) {
            rotulo.setImageResource(idRotulo[6]);
            nomeClasse.setText(Riscos.classeRisco[10]);
        }
        if (nclasse.equals("2.2")) {
            rotulo.setImageResource(idRotulo[5]);
            nomeClasse.setText(Riscos.classeRisco[9]);
        }
        if (nclasse.equals("2.1")) {
            rotulo.setImageResource(idRotulo[4]);
            nomeClasse.setText(Riscos.classeRisco[8]);
        }
        if (nclasse.equals("2")) {
            rotulo.setImageResource(idRotulo[4]);
            nomeClasse.setText(Riscos.classeRisco[7]);
        }
        if (nclasse.equals("1.6")) {
            rotulo.setImageResource(idRotulo[3]);
            nomeClasse.setText(Riscos.classeRisco[6]);
        }
        if (nclasse.equals("1.5")) {
            rotulo.setImageResource(idRotulo[2]);
            nomeClasse.setText(Riscos.classeRisco[5]);
        }
        if (nclasse.equals("1.4")) {
            rotulo.setImageResource(idRotulo[1]);
            nomeClasse.setText(Riscos.classeRisco[4]);
        }
        if (nclasse.equals("1.3")) {
            rotulo.setImageResource(idRotulo[0]);
            nomeClasse.setText(Riscos.classeRisco[3]);
        }
        if (nclasse.equals("1.2")) {
            rotulo.setImageResource(idRotulo[0]);
            nomeClasse.setText(Riscos.classeRisco[2]);
        }
        if (nclasse.equals("1.1")) {
            rotulo.setImageResource(idRotulo[0]);
            nomeClasse.setText(Riscos.classeRisco[1]);
        }
        if (nclasse.equals("1")) {
            rotulo.setImageResource(idRotulo[0]);
            nomeClasse.setText(Riscos.classeRisco[0]);
        }


    }

    @SuppressLint("NewApi")
    private void usarActionBar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

}
