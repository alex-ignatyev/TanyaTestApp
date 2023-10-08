package com.tapp.ui.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tapp.R
import com.tapp.databinding.ItemPhoneBinding
import com.tapp.domain.Product

class ProductsAdapter : ListAdapter<Product, ProductsAdapter.Holder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_phone, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemPhoneBinding.bind(view)

        fun bind(product: Product) {
            binding.title.text = product.title
            binding.description.text = product.description
        }
    }

    private object Differ : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}