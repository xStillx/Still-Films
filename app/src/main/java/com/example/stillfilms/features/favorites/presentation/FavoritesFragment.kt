package com.example.stillfilms.features.favorites.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.stillfilms.R
import com.example.stillfilms.databinding.FragmentFavoritesBinding
import com.example.stillfilms.features.films.presentation.adapter.FilmsAdapter
import com.example.stillfilms.features.splash.data.entity.FilmEntity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val viewBinding: FragmentFavoritesBinding by viewBinding(FragmentFavoritesBinding::bind)
    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var filmsAdapter: FilmsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter()
        initViews()
        observe()
    }

    private fun adapter() {
        filmsAdapter = FilmsAdapter( { filmEntity ->
            findNavController().navigate(
                R.id.action_favoritesFragment_to_filmFragment, bundleOf(
                    "id" to filmEntity.id,
                    "favorite" to filmEntity.isFavorite
                )
            )
        }
        ) { filmEntity, isFavorite ->
            viewModel.updateFilm(
                FilmEntity(
                    filmEntity.id,
                    filmEntity.rating,
                    filmEntity.name,
                    filmEntity.poster,
                    isFavorite
                )
            )
        }
    }

    private fun observe() {
        viewModel.database(requireContext())
        viewModel.readAllData!!.observe(viewLifecycleOwner) {
            val favorites: List<FilmEntity> = it.filter { filter ->
                filter.isFavorite
            }
            filmsAdapter.submitList(favorites)
        }
    }

    private fun initViews() {
        viewBinding.rvFavorites.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewBinding.rvFavorites.adapter = filmsAdapter
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.isVisible = true
    }
}