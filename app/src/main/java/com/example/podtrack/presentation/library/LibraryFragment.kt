package com.example.podtrack.presentation.library

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.podtrack.R
import com.example.podtrack.databinding.FragmentLibraryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LibraryFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLibraryBinding.bind(view)

        binding.tvTitle.text = "Библиотека"
        binding.tvStatus.text = "Заглушка. Данные подключим на следующем шаге."
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Предотвращаем утечку памяти
    }
}