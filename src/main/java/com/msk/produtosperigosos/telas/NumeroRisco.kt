package com.msk.produtosperigosos.telas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.msk.produtosperigosos.R

class NumeroRisco : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.numeros_risco, container, false)
        val listView = view.findViewById<ListView>(R.id.list_view)
        val items = arrayOf("Item 3.1", "Item 3.2", "Item 3.3")
        val adapter = ArrayAdapter(requireContext(), R.layout.custom_list_item, R.id.text_view, items)
        listView.adapter = adapter
        return view
    }
}