package com.example.stillfilms.features.films.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.stillfilms.R
import com.example.stillfilms.databinding.ItemFilmsBinding
import com.example.stillfilms.features.splash.data.entity.FilmEntity

class FilmsAdapter(
    private val onFilmClick: (FilmEntity) -> Unit,
    private val onFavoriteClick: (FilmEntity, Boolean) -> Unit,
) : ListAdapter<FilmEntity, FilmsAdapter.FilmsViewHolder>(FilmsDiffCallBack()) {

    class FilmsViewHolder(
        view: View,
        private val onFilmClick: (FilmEntity) -> Unit,
        private val onFavoriteClick: (FilmEntity, Boolean) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val viewBinding: ItemFilmsBinding by viewBinding(ItemFilmsBinding::bind)

        @SuppressLint("SetTextI18n")
        fun bind(film: FilmEntity) {
            if (film.isFavorite) {
                viewBinding.ivFavorite.setImageResource(R.drawable.ic_favorite)
            } else {
                viewBinding.ivFavorite.setImageResource(R.drawable.ic_add_favorite)
            }
            viewBinding.ivFavorite.setOnClickListener {
                if (film.isFavorite) {
                    viewBinding.ivFavorite.setImageResource(R.drawable.ic_add_favorite)
                    onFavoriteClick.invoke(film, false)
                } else {
                    viewBinding.ivFavorite.setImageResource(R.drawable.ic_favorite)
                    onFavoriteClick.invoke(film, true)
                }
            }
            Glide.with(viewBinding.ivFilm).load(film.poster).into(viewBinding.ivFilm)
            viewBinding.tvName.text = film.name
            viewBinding.tvRank.text = "Рейтинг: ${film.rating} IMDB"
            viewBinding.clFilm.setOnClickListener {
                onFilmClick.invoke(film)
            }
        }

    }

    class FilmsDiffCallBack: DiffUtil.ItemCallback<FilmEntity>(){
        override fun areItemsTheSame(oldItem: FilmEntity, newItem: FilmEntity): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: FilmEntity, newItem: FilmEntity): Boolean {
            return oldItem.name == newItem.name && oldItem.isFavorite == newItem.isFavorite
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val cellForItem =
            LayoutInflater.from(parent.context).inflate(R.layout.item_films, parent, false)
        return FilmsViewHolder(cellForItem, onFilmClick, onFavoriteClick)
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}