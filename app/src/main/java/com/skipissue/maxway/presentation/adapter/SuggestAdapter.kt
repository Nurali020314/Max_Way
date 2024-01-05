package com.skipissue.maxway.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skipissue.maxway.R
import com.skipissue.maxway.app.App
import com.squareup.picasso.Picasso
import uz.gita.lesson40.data.settings.SettingsImpl


class SuggestAdapter() : ListAdapter<SuggestBasket,  SuggestViewHolder>(CharacterComparator) {

    private var onClickListener: ((food: SuggestBasket) -> Unit)? = null
    fun setOnClickClickListener(clickListener: (food: SuggestBasket) -> Unit) {
        onClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  SuggestViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.detail_item, parent, false)
        return  SuggestViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder:  SuggestViewHolder, position: Int) {
        val character = getItem(position)
        character?.let { holder.bind(it) }
    }

    object CharacterComparator : DiffUtil.ItemCallback<SuggestBasket>() {
        override fun areItemsTheSame(oldItem: SuggestBasket, newItem: SuggestBasket): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: SuggestBasket, newItem: SuggestBasket): Boolean {
            return oldItem == newItem
        }
    }
}

class SuggestViewHolder(
    view: View,
    val onItemClickListener: ((food: SuggestBasket) -> Unit)?,
) :
    RecyclerView.ViewHolder(view) {
    private lateinit var settingsImpl: SettingsImpl
    private val name: TextView = view.findViewById(R.id.suggest_name)
    private val description: TextView = view.findViewById(R.id.suggest_description)
    private val cost: TextView = view.findViewById(R.id.suggest_cost)
    private val image: ImageView = view.findViewById(R.id.suggest_image)
    private val layout:FrameLayout=view.findViewById(R.id.detail_item_layout)


    fun bind(food: SuggestBasket) {
        settingsImpl = SettingsImpl(App.context)
        name.text = food.nameUz
        description.text=food.descriptionUZ
        cost.text="${food.cost} so'm"

      Picasso.get().load("https://cdn.delever.uz/delever/${food.image}").into(image)

          cost.setOnClickListener {
              onItemClickListener?.invoke(food)
          }


    }
}