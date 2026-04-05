package com.example.podtrack.presentation.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.podtrack.R
import com.example.podtrack.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)

        binding.tvTitle.text = "Настройки"
        binding.tvStatus.text = "Заглушка. Данные подключим на следующем шаге."
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Предотвращаем утечку памяти
    }
}