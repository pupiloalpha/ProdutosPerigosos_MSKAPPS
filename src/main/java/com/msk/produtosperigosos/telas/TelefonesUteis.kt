package com.msk.produtosperigosos.telas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.msk.produtosperigosos.databinding.TelefonesBinding

class TelefonesUteis : Fragment() {

    private var _binding: TelefonesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TelefonesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onClickListener = View.OnClickListener {
            val numero = when (it.id) {
                binding.b190.id -> "tel:190"
                binding.b191.id -> "tel:191"
                binding.b192.id -> "tel:192"
                binding.b193.id -> "tel:193"
                binding.b166.id -> "tel:166"
                binding.b199.id -> "tel:199"
                binding.b0800.id -> "tel:08006440199"
                else -> ""
            }
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = numero.toUri()
            requireActivity().startActivity(intent)
        }

        binding.b190.setOnClickListener(onClickListener)
        binding.b191.setOnClickListener(onClickListener)
        binding.b192.setOnClickListener(onClickListener)
        binding.b193.setOnClickListener(onClickListener)
        binding.b166.setOnClickListener(onClickListener)
        binding.b199.setOnClickListener(onClickListener)
        binding.b0800.setOnClickListener(onClickListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}