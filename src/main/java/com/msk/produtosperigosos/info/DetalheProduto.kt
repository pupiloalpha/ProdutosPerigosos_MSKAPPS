package com.msk.produtosperigosos.info

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.color.MaterialColors
import com.msk.produtosperigosos.R
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.msk.produtosperigosos.AppProdutosPerigosos // Sua Application class
import com.msk.produtosperigosos.db.Dados // Sua Entidade do Room (Data Class)

class DetalheProduto : AppCompatActivity() {
    private lateinit var r: Resources

    // ELEMENTOD DA TELA
    private var nrONU: TextView? = null
    private var nrClasse: TextView? = null
    private var nrRisco: TextView? = null
    private var nomeProduto: TextView? = null
    private var nomeClasse: TextView? = null
    private var nomeRisco: TextView? = null
    private var saude: TextView? = null
    private var incendio: TextView? = null
    private var nivelProtecao: TextView? = null
    private var isolamento: TextView? = null
    private var evacuacao: TextView? = null
    private var rotulo: ImageView? = null

    // VETORES COM DADOS DOS PRODUTOS
    private lateinit var idRotulo: IntArray
    private var nclasse: String? = null
    private var nrisco: String? = null

    // VARIAVES QUE SERAO UTILIZADAS
    private var nProduto = 0
    private var guia = 0

    // NOVO: Variável para armazenar o produto completo retornado do DB
    private var produtoPerigoso: Dados? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.detalhe_produto)
        r = resources
        // RECEBE NUMERO PRODUTO
        val envelope = intent.extras
        nProduto = envelope!!.getInt("nr")

        inicando()
        usarActionBar()
        buscaInfoProduto(nProduto)
        mostraInfoProduto()
        defineRotuloClasse()
    }

    private fun inicando() {
        // ELEMENTOS QUE SERAO EXIBIDOS NA TELA

        nrONU = findViewById(R.id.tvNrONU)
        nrClasse = findViewById(R.id.tvNrClasse)
        nrRisco = findViewById(R.id.tvNrRisco)
        nomeProduto = findViewById(R.id.tvNomeProduto)
        nomeClasse = findViewById(R.id.tvNomeClasse)
        nomeRisco = findViewById(R.id.tvNomeRisco)
        rotulo = findViewById(R.id.ivRotuloRisco)
        saude = findViewById(R.id.tvPerigoSaude)
        incendio = findViewById(R.id.tvPerigoIncendio)
        nivelProtecao = findViewById(R.id.tvNivelProtecao)
        isolamento = findViewById(R.id.tvIsolamento)
        evacuacao = findViewById(R.id.tvEvacuacao)

        // DEFINE OS ROTULOS QUE SERAO EXIBIDOS
        idRotulo = intArrayOf(
            R.drawable.rotulo_explosivo,  // 1.1, 1.2, 1.3
            R.drawable.rotulo_explosivo_4,  // 1.4
            R.drawable.rotulo_explosivo_5,  // 1.5
            R.drawable.rotulo_explosivo_6,  // 1.6
            R.drawable.rotulo_gas_inflamavel,  // 2.1
            R.drawable.rotulo_gas_nao_toxico,  // 2.2
            R.drawable.rotulo_gas_toxico,  // 2.3
            R.drawable.rotulo_liquido_inflamavel,  // 3
            R.drawable.rotulo_solido_inflamavel,  // 4.1
            R.drawable.rotulo_combustao_expontanea,  // 4.2
            R.drawable.rotulo_perigoso_quando_molhado,  // 4.3
            R.drawable.rotulo_oxidante,  // 5.1
            R.drawable.rotulo_peroxido_organico,  // 5.2
            R.drawable.rotulo_toxico,  // 6.1
            R.drawable.rotulo_nocivo,  // 6.1*
            R.drawable.rotulo_substancia_infectante,  // 6.2
            R.drawable.rotulo_radioativo_veic,  // 7
            R.drawable.rotulo_corrosivo,  // 8
            R.drawable.rotulo_substancias_diversas // 9
        )
    }

    private fun buscaInfoProduto(nProduto: Int) {
        // 1. Inicia uma Coroutine no escopo da Activity (lifecycleScope)
        // O acesso ao DB deve ser assíncrono.
        lifecycleScope.launch {

            // 2. Obtém acesso ao DAO através da classe Application (AppPerigosos)
            // Certifique-se que o AppPerigosos está registrado no AndroidManifest.
            val app = application as AppProdutosPerigosos
            val dao = app.produtoPerigosoDao

            // 3. Executa a busca otimizada no Room, usando a posição (nProduto)
            // Lembre-se: nProduto já foi coletado no onCreate.
            val produtoEncontrado = dao.buscarPorPosicao(nProduto)

            // 4. Se o produto for encontrado, atualiza a UI
            produtoEncontrado?.let { produto ->
                produtoPerigoso = produto // Armazena o objeto completo para uso futuro

                // Atualiza a UI e variáveis de lógica com os dados do objeto:
                nrONU?.text = r.getString(R.string.dica_nr_onu, produto.numeroONU)
                nomeProduto?.text = produto.descricao
                nrClasse?.text = r.getString(R.string.dica_nr_classe, produto.classeRisco)
                nrRisco?.text = produto.numeroRisco
                guia = produto.guiaRisco.toInt()

                if (nrisco == "0") {
                    nrRisco?.visibility = View.GONE
                    nomeRisco?.text = Guias.nomeGuia[guia]
                } else {
                    nrRisco?.text = r.getString(R.string.dica_nr_risco, produto.numeroRisco)
                    var j = 0
                    while (j < Riscos.nrRisco.size && nrisco != Riscos.nrRisco[j]) {
                        j++
                    }
                    if (j < Riscos.nrRisco.size) {
                        nomeRisco?.text = Riscos.nomeRisco[j]
                    }
                }

            } ?: run {
                // Caso o produto não seja encontrado (índice inválido ou DB vazio)
                finish()
            }
        }
    }

    private fun mostraInfoProduto() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            saude?.text = Html.fromHtml(Guias.riscoSaude[guia], Html.FROM_HTML_MODE_LEGACY)
            incendio?.text = Html.fromHtml(Guias.riscoIncendio[guia], Html.FROM_HTML_MODE_LEGACY)
            nivelProtecao?.text = Html.fromHtml(Guias.epi[guia], Html.FROM_HTML_MODE_LEGACY)
            isolamento?.text = Html.fromHtml(Guias.isola[guia], Html.FROM_HTML_MODE_LEGACY)
            evacuacao?.text = Html.fromHtml(Guias.evacua[guia], Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            saude?.text = Html.fromHtml(Guias.riscoSaude[guia])
            @Suppress("DEPRECATION")
            incendio?.text = Html.fromHtml(Guias.riscoIncendio[guia])
            @Suppress("DEPRECATION")
            nivelProtecao?.text = Html.fromHtml(Guias.epi[guia])
            @Suppress("DEPRECATION")
            isolamento?.text = Html.fromHtml(Guias.isola[guia])
            @Suppress("DEPRECATION")
            evacuacao?.text = Html.fromHtml(Guias.evacua[guia])
        }
    }


    private fun defineRotuloClasse() {
        when (nclasse) {
            "9" -> {
                rotulo?.setImageResource(idRotulo[18])
                nomeClasse?.text = Riscos.classeRisco[21]
            }
            "8" -> {
                rotulo?.setImageResource(idRotulo[17])
                nomeClasse?.text = Riscos.classeRisco[20]
            }
            "7" -> {
                rotulo?.setImageResource(idRotulo[16])
                nomeClasse?.text = Riscos.classeRisco[19]
            }
            "6.2" -> {
                rotulo?.setImageResource(idRotulo[15])
                nomeClasse?.text = Riscos.classeRisco[18]
            }
            "6.1" -> {
                rotulo?.setImageResource(idRotulo[13])
                nomeClasse?.text = Riscos.classeRisco[17]
            }
            "5.2" -> {
                rotulo?.setImageResource(idRotulo[12])
                nomeClasse?.text = Riscos.classeRisco[16]
            }
            "5.1" -> {
                rotulo?.setImageResource(idRotulo[11])
                nomeClasse?.text = Riscos.classeRisco[15]
            }
            "4.3" -> {
                rotulo?.setImageResource(idRotulo[10])
                nomeClasse?.text = Riscos.classeRisco[14]
            }
            "4.2" -> {
                rotulo?.setImageResource(idRotulo[9])
                nomeClasse?.text = Riscos.classeRisco[13]
            }
            "4.1" -> {
                rotulo?.setImageResource(idRotulo[8])
                nomeClasse?.text = Riscos.classeRisco[12]
            }
            "3" -> {
                rotulo?.setImageResource(idRotulo[7])
                nomeClasse?.text = Riscos.classeRisco[11]
            }
            "2.3" -> {
                rotulo?.setImageResource(idRotulo[6])
                nomeClasse?.text = Riscos.classeRisco[10]
            }
            "2.2" -> {
                rotulo?.setImageResource(idRotulo[5])
                nomeClasse?.text = Riscos.classeRisco[9]
            }
            "2.1" -> {
                rotulo?.setImageResource(idRotulo[4])
                nomeClasse?.text = Riscos.classeRisco[8]
            }
            "2" -> {
                rotulo?.setImageResource(idRotulo[4])
                nomeClasse?.text = Riscos.classeRisco[7]
            }
            "1.6" -> {
                rotulo?.setImageResource(idRotulo[3])
                nomeClasse?.text = Riscos.classeRisco[6]
            }
            "1.5" -> {
                rotulo?.setImageResource(idRotulo[2])
                nomeClasse?.text = Riscos.classeRisco[5]
            }
            "1.4" -> {
                rotulo?.setImageResource(idRotulo[1])
                nomeClasse?.text = Riscos.classeRisco[4]
            }
            "1.3" -> {
                rotulo?.setImageResource(idRotulo[0])
                nomeClasse?.text = Riscos.classeRisco[3]
            }
            "1.2" -> {
                rotulo?.setImageResource(idRotulo[0])
                nomeClasse?.text = Riscos.classeRisco[2]
            }
            "1.1" -> {
                rotulo?.setImageResource(idRotulo[0])
                nomeClasse?.text = Riscos.classeRisco[1]
            }
            "1" -> {
                rotulo?.setImageResource(idRotulo[0])
                nomeClasse?.text = Riscos.classeRisco[0]
            }
        }
    }

    private fun usarActionBar() {
        val toolbar = findViewById<Toolbar?>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.dica_produto)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}