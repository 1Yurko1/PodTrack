package com.example.podtrack.presentation.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.podtrack.R
import com.example.podtrack.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PodcastAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)
        setupToolbar()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupToolbar() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, inflater: MenuInflater) {
                inflater.inflate(R.menu.menu_search, menu)
                val searchItem = menu.findItem(R.id.action_search)
                val searchView = searchItem.actionView as SearchView
                searchView.queryHint = "Поиск подкастов..."
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        viewModel.onSearchTriggered()
                        searchView.clearFocus()
                        return true
                    }
                    override fun onQueryTextChange(newText: String?): Boolean {
                        viewModel.onQueryChanged(newText ?: "")
                        return true
                    }
                })
            }
            override fun onMenuItemSelected(item: MenuItem) = false
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupRecyclerView() {
        adapter = PodcastAdapter(
            onItemClick = { /* TODO: навигация к деталям */ },
            onFavoriteToggle = { viewModel.toggleFavorite(it) }
        )
        binding.recycler.adapter = adapter
        // Простая анимация появления элементов
        binding.recycler.itemAnimator?.apply {
            addDuration = 150
            moveDuration = 150
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.progress.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                    binding.tvError.visibility = if (state.error != null) View.VISIBLE else View.GONE
                    binding.tvError.text = state.error

                    val hasResults = state.results.isNotEmpty()
                    binding.recycler.visibility = if (hasResults) View.VISIBLE else View.GONE
                    binding.tvEmpty.visibility = if (!hasResults && !state.isLoading && state.error == null)
                        View.VISIBLE else View.GONE

                    if (hasResults) {
                        adapter.submitList(state.results)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}