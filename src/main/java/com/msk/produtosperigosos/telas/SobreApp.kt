package com.msk.produtosperigosos.telas

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.msk.produtosperigosos.R

class SobreApp : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.sobre, container, false)
        val versionTextView = view.findViewById<TextView>(R.id.tvVersaoApp)

        try {
            val versionName = requireActivity().packageManager
                .getPackageInfo(requireActivity().packageName, 0).versionName
            versionTextView.text = getString(R.string.desenvolvedor, versionName)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            versionTextView.text = getString(R.string.desenvolvedor, "N/A")
        }

        return view
    }
}