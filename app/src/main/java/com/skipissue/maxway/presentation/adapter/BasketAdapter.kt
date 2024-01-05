package com.skipissue.maxway.presentation.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skipissue.maxway.R
import com.skipissue.maxway.app.App
import com.skipissue.maxway.data.local.entity.BasketEntity
import com.skipissue.maxway.domain.entity.Basket
import com.squareup.picasso.Picasso
import uz.gita.lesson40.data.settings.SettingsImpl


class BasketAdapter() : ListAdapter<BasketEntity, BasketViewHolder>(CharacterComparator) {



    private var onClickListener: ((Int) -> Unit)? = null
    fun setOnClickClickListener(clickListener: (Int) -> Unit) {
        onClickListener = clickListener
    }

    private var plusClickListener: ((Int) -> Unit)? = null
    fun plusOnClickClickListener(clickListener: (Int) -> Unit) {
        plusClickListener = clickListener
    }

    private var minusClickListener: ((Int) -> Unit)? = null
    fun minusOnClickClickListener(clickListener: (Int) -> Unit) {
        minusClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cart_basket, parent, false)
        return BasketViewHolder(view, onClickListener,plusClickListener,minusClickListener)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val character = getItem(position)
        character?.let { holder.bind(it) }
    }

    object CharacterComparator : DiffUtil.ItemCallback<BasketEntity>() {
        override fun areItemsTheSame(oldItem: BasketEntity, newItem: BasketEntity): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: BasketEntity, newItem: BasketEntity): Boolean {
            return oldItem == newItem
        }
    }
}

class BasketViewHolder(
    view: View,
    val onItemClickListener: ((Int) -> Unit)?,
    val plusItemClickListener: ((Int) -> Unit)?,
    val minusItemClickListener: ((Int) -> Unit)?,

) :
    RecyclerView.ViewHolder(view) {
    private lateinit var settingsImpl: SettingsImpl
    private val name: TextView = view.findViewById(R.id.basket_name)
    private val cost: TextView = view.findViewById(R.id.basket_cost)
    private val count: TextView = view.findViewById(R.id.basket_count)
    private val image: ImageView= view.findViewById(R.id.basket_image)
    private val detete: ImageView= view.findViewById(R.id.detete_basket)
    private val layout:FrameLayout=view.findViewById(R.id.item_basket_layout)



    fun bind(food: BasketEntity) {
   //  a+=food.count!!+food.cost!!.toInt()
        settingsImpl = SettingsImpl(App.context)
        name.text = food.name
        cost.text = food.cost
        Picasso.get().load("https://cdn.delever.uz/delever/${food.image}").into(image)
        count.text=food.count.toString()

        detete.setOnClickListener {
            onItemClickListener?.invoke(bindingAdapterPosition)

        }

    }
}