package com.example.norrisapplication.ui.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.norrisapplication.R
import kotlinx.android.synthetic.main.categories_list_item.view.*

class CategoryAdapter(
    private val categoriesList: List<String>,
    private val categoryAdapterListener: CategoryAdapterListener
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_list_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(position: Int) {
            itemView.categoryName.text = categoriesList[position]
            itemView.setOnClickListener {
                categoryAdapterListener.onItemClicked(categoriesList[position])
            }

        }
    }
}