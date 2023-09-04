package com.example.stillfilms.features.films.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.stillfilms.R
import com.example.stillfilms.databinding.FragmentFilmsBinding
import com.example.stillfilms.features.films.presentation.adapter.FilmsAdapter
import com.example.stillfilms.features.splash.data.entity.FilmEntity
import com.example.stillfilms.utils.ViewState
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmsFragment : Fragment(R.layout.fragment_films) {

    private val viewBinding: FragmentFilmsBinding by viewBinding(FragmentFilmsBinding::bind)
    private val viewModel: FilmsViewModel by viewModels()
    private val searchFilms = mutableListOf<FilmEntity>()
    private lateinit var filmsAdapter: FilmsAdapter
    private lateinit var searchAdapter: FilmsAdapter
    private lateinit var manager: LinearLayoutManager
    private var position = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        manager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        if (savedInstanceState != null){
            viewModel.getSavedInstanceState(
                savedInstanceState.getInt("managerState")
            )
        }
        adapters()
        initViews()
        observe()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val lastFirstVisiblePosition =
            (viewBinding.rvFilms.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
        outState.putInt("managerState", lastFirstVisiblePosition)
    }

    private fun observe() {
        viewModel.database(requireContext())
        viewModel.getSavedPosition.observe(viewLifecycleOwner){
            position = it
        }
        viewModel.isLoad.observe(viewLifecycleOwner) {
            viewBinding.pbProgress.isVisible = it
        }
        viewModel.readAllData!!.observe(viewLifecycleOwner) {
            filmsAdapter.submitList(it)
            manager.scrollToPositionWithOffset(position,0)
        }
        viewModel.search.observe(viewLifecycleOwner) {
            when (it) {
                ViewState.Empty -> {
                    Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
                }
                is ViewState.Error -> {
                    Log.d("FilmsFragment", "observe: ${it.throwable}")
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
                is ViewState.Show -> {
                    for (film in it.data.docs) {
                        searchFilms.add(
                            FilmEntity(
                                id = film.id,
                                rating = film.rating,
                                name = film.name,
                                poster = film.poster,
                                isFavorite = false
                            )
                        )
                    }
                    searchAdapter.submitList(searchFilms)
                    manager.scrollToPositionWithOffset(position,0)
                }
            }
        }
    }

    private fun initViews() {
        viewBinding.rvFilms.layoutManager = manager
        viewBinding.rvFilms.adapter = filmsAdapter
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.isVisible = true
        viewBinding.etSearch.doAfterTextChanged {
            if (it.toString().isNotBlank()) {
                viewModel.searchFilm(it.toString())
                viewBinding.rvFilms.adapter = searchAdapter
            }
        }
    }

    private fun adapters() {
        filmsAdapter = FilmsAdapter(
            { filmEntity ->
                findNavController().navigate(
                    R.id.action_filmsFragment_to_filmFragment, bundleOf(
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

        searchAdapter = FilmsAdapter(
            { filmEntity ->
                findNavController().navigate(
                    R.id.action_filmsFragment_to_filmFragment, bundleOf(
                        "id" to filmEntity.id,
                        "favorite" to filmEntity.isFavorite,
                        "isSearch" to true
                    )
                )
            }
        ) { filmEntity, isFavorite ->
            if (isFavorite) {
                viewModel.addToFavorite(
                    FilmEntity(
                        filmEntity.id,
                        filmEntity.rating,
                        filmEntity.name,
                        filmEntity.poster,
                        true
                    )
                )
            } else {
                viewModel.deleteFromFavorite(
                    FilmEntity(
                        filmEntity.id,
                        filmEntity.rating,
                        filmEntity.name,
                        filmEntity.poster,
                        false
                    )
                )
            }
        }
    }
}