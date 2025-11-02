package com.msk.produtosperigosos.listas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msk.produtosperigosos.R

class ListaClasseDeRisco : Fragment() {

    private lateinit var nrClasse: Array<String>
    private lateinit var rotuloId: IntArray

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_classe_risco, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nrClasse = resources.getStringArray(R.array.classe_risco)

        rotuloId = intArrayOf(
            R.drawable.rotulo_explosivo,
            R.drawable.rotulo_gas_inflamavel,
            R.drawable.rotulo_liquido_inflamavel,
            R.drawable.rotulo_solido_inflamavel,
            R.drawable.rotulo_oxidante, R.drawable.rotulo_toxico,
            R.drawable.rotulo_radioativo_veic, R.drawable.rotulo_corrosivo,
            R.drawable.rotulo_substancias_diversas
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.lista_classe_risco_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ClasseDeRiscoAdapter(nrClasse, rotuloId) { position ->
            val intent = Intent(requireActivity(), ListaSubClasse::class.java).apply {
                putExtra("nr", position)
            }
            requireActivity().startActivity(intent)
        }
    }

    private class ClasseDeRiscoAdapter(
        private val nrClasse: Array<String>,
        private val rotuloId: IntArray,
        private val onItemClick: (Int) -> Unit
    ) : RecyclerView.Adapter<ClasseDeRiscoAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_classe_risco, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(rotuloId[position], nrClasse[position])
            holder.itemView.setOnClickListener {
                onItemClick(position)
            }
        }

        override fun getItemCount(): Int = rotuloId.size

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val rotulo: ImageView = view.findViewById(R.id.ivRotulo)
            private val classe: TextView = view.findViewById(R.id.tvClasseRotulo)

            fun bind(rotuloResId: Int, classeText: String) {
                rotulo.setImageResource(rotuloResId)
                classe.text = classeText
            }
        }
    }
}
