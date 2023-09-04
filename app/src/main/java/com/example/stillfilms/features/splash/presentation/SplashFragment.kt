package com.example.stillfilms.features.splash.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.stillfilms.R
import com.example.stillfilms.databinding.FragmentSplashBinding
import com.example.stillfilms.features.splash.data.entity.FilmEntity
import com.example.stillfilms.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment: Fragment(R.layout.fragment_splash) {

    private val viewBinding: FragmentSplashBinding by viewBinding(FragmentSplashBinding::bind)
    private val viewModel: SplashViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
    }

    private fun observe() {
        viewModel.films.observe(viewLifecycleOwner){
            when(it){
                ViewState.Empty -> {
                    Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
                }
                is ViewState.Error -> {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
                is ViewState.Show -> {
                    for (doc in it.data.docs){
                        viewModel.addFilmsToDataBase(FilmEntity(
                            id = doc.id,
                            rating = doc.rating.imdb,
                            name = doc.name,
                            poster = doc.poster!!.previewUrl,
                            false
                        ))
                    }
                }
            }
        }
    }

    private fun initViews() {
        viewModel.dataBase(requireContext())
        viewModel.goToFilms.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_splashFragment_to_filmsFragment)
        }
    }
}