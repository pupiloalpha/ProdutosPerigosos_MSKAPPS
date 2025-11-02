package com.msk.produtosperigosos.listas

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.color.MaterialColors
import com.msk.produtosperigosos.R

class ListaSubClasse : AppCompatActivity() {
    private var r: Resources? = null

    // ELEMENTOS DA TELA
    private var classe: TextView? = null
    private var detalhe: TextView? = null
    private var numero: TextView? = null
    private var subclasse: TextView? = null
    private var subclasses: ListView? = null
    private var inflaLista: LayoutInflater? = null
    private var rotulo: ImageView? = null

    // BUSCADOR DE INFORMACOES DA CLASSE
    private lateinit var nomeClasse: Array<String>
    private lateinit var detClasse: Array<String>
    private lateinit var nSubclasse: Array<String>
    private lateinit var detSubclasse: Array<String>
    private var nrClasse = 0
    private lateinit var rotuloId: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.sub_classes)
        r = resources

        iniciador()
        usarActionBar()
        // BUSCA DADOS DA CLASSE DE RISCO
        val envelope = intent.extras
        nrClasse = envelope!!.getInt("nr")
        defineInfoClasse(nrClasse)
        subclasses!!.adapter = Adapter()
    }

    private fun iniciador() {
        // ELEMENTO DA TELA

        subclasses = findViewById(R.id.lvSubclasse)
        // VETORES COM INFORMACOES DAS CLASSES
        nomeClasse = r!!.getStringArray(R.array.nr_classe_risco)
        detClasse = r!!.getStringArray(R.array.detalhe_classe)
    }

    private fun defineInfoClasse(i: Int) {
        when (i) {
            0 -> { // EXPLOSIVOS
                rotuloId = intArrayOf(
                    R.drawable.rotulo_explosivo,
                    R.drawable.rotulo_explosivo, R.drawable.rotulo_explosivo,
                    R.drawable.rotulo_explosivo_4,
                    R.drawable.rotulo_explosivo_5,
                    R.drawable.rotulo_explosivo_6
                )
                nSubclasse = r!!.getStringArray(R.array.nr_sub_classe_1)
                detSubclasse = r!!.getStringArray(R.array.nome_sub_classe_1)
            }
            1 -> { // GASES
                rotuloId = intArrayOf(
                    R.drawable.rotulo_gas_inflamavel,
                    R.drawable.rotulo_gas_nao_toxico,
                    R.drawable.rotulo_gas_toxico
                )
                nSubclasse = r!!.getStringArray(R.array.nr_sub_classe_2)
                detSubclasse = r!!.getStringArray(R.array.nome_sub_classe_2)
            }
            2 -> { // LIQUIDOS
                rotuloId = intArrayOf(R.drawable.rotulo_liquido_inflamavel)
                nSubclasse = arrayOf(nomeClasse[i])
                detSubclasse = arrayOf(
                    resources.getString(
                        R.string.sem_subclasse
                    )
                )
            }
            3 -> { // SOLIDOS
                rotuloId = intArrayOf(
                    R.drawable.rotulo_solido_inflamavel,
                    R.drawable.rotulo_combustao_expontanea,
                    R.drawable.rotulo_perigoso_quando_molhado
                )
                nSubclasse = r!!.getStringArray(R.array.nr_sub_classe_4)
                detSubclasse = r!!.getStringArray(R.array.nome_sub_classe_4)
            }
            4 -> { // OXIDOS
                rotuloId = intArrayOf(
                    R.drawable.rotulo_oxidante,
                    R.drawable.rotulo_peroxido_organico
                )
                nSubclasse = r!!.getStringArray(R.array.nr_sub_classe_5)
                detSubclasse = r!!.getStringArray(R.array.nome_sub_classe_5)
            }
            5 -> { // TOXICOS
                rotuloId = intArrayOf(
                    R.drawable.rotulo_toxico,
                    R.drawable.rotulo_nocivo,
                    R.drawable.rotulo_substancia_infectante
                )
                nSubclasse = r!!.getStringArray(R.array.nr_sub_classe_6)
                detSubclasse = r!!.getStringArray(R.array.nome_sub_classe_6)
            }
            6 -> { // RADIOATIVO
                rotuloId = intArrayOf(R.drawable.rotulo_radioativo_veic, R.drawable.rotulo_fissil)
                nSubclasse = arrayOf(nomeClasse[i], "Produtos considerados físseis")
                detSubclasse = arrayOf(
                    resources.getString(
                        R.string.sem_subclasse
                    ), "Utilizado para identificar produtos que podem apresentar fissão nuclear"
                )
            }
            7 -> { // CORROSIVO
                rotuloId = intArrayOf(R.drawable.rotulo_corrosivo)
                nSubclasse = arrayOf(nomeClasse[i])
                detSubclasse = arrayOf(
                    resources.getString(
                        R.string.sem_subclasse
                    )
                )
            }
            8 -> { // DIVERSOS
                rotuloId = intArrayOf(R.drawable.rotulo_substancias_diversas)
                nSubclasse = arrayOf(nomeClasse[i])
                detSubclasse = arrayOf(
                    resources.getString(
                        R.string.sem_subclasse
                    )
                )
            }
        }
    }

    @SuppressLint("NewApi")
    private fun usarActionBar() {
        val toolbar = findViewById<Toolbar?>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.dica_detalhe_classe)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    internal inner class Adapter : BaseAdapter() {
        override fun getCount(): Int {
            return if (detSubclasse.size == 1) 3 else detSubclasse.size + 3
        }

        override fun getItem(arg0: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(nrLinha: Int, convertView: View?, parent: ViewGroup): View {
            val view: View
            inflaLista = layoutInflater

            if (nrLinha == 0) {
                view = inflaLista!!.inflate(R.layout.nome_classe, parent, false)
                classe = view.findViewById(R.id.tvNomeClasse)
                classe?.text = nomeClasse[nrClasse]
            } else if (nrLinha == 1) {
                view = inflaLista!!.inflate(R.layout.detalhe_classe, parent, false)
                detalhe = view.findViewById(R.id.tvDetalheClasse)
                detalhe?.text = detClasse[nrClasse]
            } else if (nrLinha == 2 && detSubclasse.size > 1) {
                view = inflaLista!!.inflate(R.layout.nome_subclasse, parent, false)
                classe = view.findViewById(R.id.tvPossuiSubClasse)
            } else if (detSubclasse.size == 1) {
                view = inflaLista!!.inflate(R.layout.item_subclasse_risco, parent, false)
                rotulo = view.findViewById(R.id.ivRotuloSubClasse)
                numero = view.findViewById(R.id.tvNrSubclasse)
                subclasse = view.findViewById(R.id.tvNomeSubclasse)
                rotulo?.setImageResource(rotuloId[0])
                numero?.text = nSubclasse[0]
                subclasse?.text = detSubclasse[0]
            } else {
                view = inflaLista!!.inflate(R.layout.item_subclasse_risco, parent, false)
                rotulo = view.findViewById(R.id.ivRotuloSubClasse)
                numero = view.findViewById(R.id.tvNrSubclasse)
                subclasse = view.findViewById(R.id.tvNomeSubclasse)
                rotulo?.setImageResource(rotuloId[nrLinha - 3])
                numero?.text = nSubclasse[nrLinha - 3]
                subclasse?.text = detSubclasse[nrLinha - 3]
            }

            return view
        }
    }
}
