package com.example.stillfilms.features.film.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.stillfilms.R
import com.example.stillfilms.databinding.ItemActorBinding
import com.example.stillfilms.features.film.domain.model.Person

class ActorsAdapter(
    private val persons: List<Person>
): RecyclerView.Adapter<ActorsAdapter.ActorsViewHolder>() {


    class ActorsViewHolder(view: View) : ViewHolder(view) {

        private val viewBinding: ItemActorBinding by viewBinding(ItemActorBinding::bind)

        fun bind(person: Person){
            viewBinding.tvName.text = person.name
            viewBinding.tvCharacter.text = person.description
            Glide.with(viewBinding.ivActor).load(person.photo).into(viewBinding.ivActor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val cellForActor = LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false)
        return ActorsViewHolder(cellForActor)
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(persons[position])
    }

    override fun getItemCount(): Int {
        return persons.size
    }
}