package com.example.stillfilms.features.film.presentation

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.stillfilms.R
import com.example.stillfilms.databinding.FragmentFilmBinding
import com.example.stillfilms.features.film.presentation.adapter.ActorsAdapter
import com.example.stillfilms.features.films.presentation.adapter.FilmsAdapter
import com.example.stillfilms.features.splash.data.entity.FilmEntity
import com.example.stillfilms.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint
import org.joda.time.format.DateTimeFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


@AndroidEntryPoint
class FilmFragment : Fragment(R.layout.fragment_film) {

    private val viewBinding: FragmentFilmBinding by viewBinding(FragmentFilmBinding::bind)
    private val viewModel: FilmViewModel by viewModels()
    private var isFavorite = false
    private var isSearch = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
    }

    @SuppressLint("SetTextI18n")
    private fun observe() {
        viewModel.dataBase(requireContext())
        viewModel.goToBack.observe(viewLifecycleOwner){
            findNavController().popBackStack()
        }
        viewModel.isLoad.observe(viewLifecycleOwner){
            viewBinding.pbProgress.isVisible = it
        }
        viewModel.film.observe(viewLifecycleOwner) { film ->
            when (film) {
                ViewState.Empty -> {
                    Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
                }
                is ViewState.Error -> {
                    Log.d("FilmFragment", "observe: ${film.throwable}")
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
                is ViewState.Show -> {
                    viewBinding.tvRating.text = "Рейтинг: ${film.data.rating.imdb} IMDB"
                    viewBinding.tvName.text = film.data.name
                    Glide.with(viewBinding.ivPoster).load(film.data.poster?.url)
                        .into(viewBinding.ivPoster)
                    viewBinding.tvDateInRussia.text = "Релиз в России: ${getDate(film.data.premiere.russia)}"
                    viewBinding.tvDateInWorld.text = "Релиз в мире: ${getDate(film.data.premiere.world)}"
                    viewBinding.tvDescription.text = film.data.description
                    viewBinding.btnTrailer.setOnClickListener {
                        watchYoutubeVideo(film.data.videos.trailers[0].url)
                    }
                    viewBinding.rvActors.adapter = ActorsAdapter(film.data.persons)
                    viewBinding.btnBack.setOnClickListener {
                        viewModel.goToBack()
                    }
                    viewBinding.ivFavorite.setOnClickListener {
                        isFavorite = if (isFavorite){
                            viewBinding.ivFavorite.setImageResource(R.drawable.ic_add_favorite)
                            false
                        }else{
                            viewBinding.ivFavorite.setImageResource(R.drawable.ic_favorite)
                            true
                        }
                        if (isSearch){
                            viewModel.addToFavorite(FilmEntity(
                                id = film.data.id.toLong(),
                                rating = film.data.rating.imdb,
                                name = film.data.name,
                                poster = film.data.poster!!.url,
                                isFavorite = isFavorite
                            ))
                            isSearch = false
                        }else{
                            viewModel.updateFavorite(FilmEntity(
                                id = film.data.id.toLong(),
                                rating = film.data.rating.imdb,
                                name = film.data.name,
                                poster = film.data.poster!!.url,
                                isFavorite = isFavorite
                            ))
                        }
                    }
                }
            }
        }
    }

    private fun initViews() {
        (arguments?.getLong("id") as Long).let {
            viewModel.getFilm(it.toString())
        }
        (arguments?.getBoolean("favorite") as Boolean).let {
            isFavorite = it
        }
        (arguments?.getBoolean("isSearch") as Boolean).let {
            isSearch = it
        }
        if (isFavorite){
            viewBinding.ivFavorite.setImageResource(R.drawable.ic_favorite)
        }else{
            viewBinding.ivFavorite.setImageResource(R.drawable.ic_add_favorite)
        }
        viewBinding.rvActors.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun getDate(date: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter: DateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            LocalDate.parse(date, formatter).toString()
        } else {
            val formatter: org.joda.time.format.DateTimeFormatter =
                DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            org.joda.time.LocalDate.parse(date, formatter).toString()
        }
    }

    private fun watchYoutubeVideo(id: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse(id))
        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(id)
        )
        try {
            startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            startActivity(webIntent)
        }
    }

}