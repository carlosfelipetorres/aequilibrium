package com.ct.codetest.screens.transformers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ct.codetest.R
import com.ct.codetest.models.transformers.Transformer


class TransformersAdapter(
    val context: Context,
    list: ArrayList<Transformer>
) :
    RecyclerView.Adapter<TransformersAdapter.TransformersFragViewHolder>() {

    var mItemList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransformersFragViewHolder {
        return TransformersFragViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.transformer_list_view_item,
                parent,
                false
            )
        )
    }

    fun updateListItems(updatedList: ArrayList<Transformer>) {
        mItemList.clear()
        mItemList = updatedList
        notifyDataSetChanged()
    }

    fun addListItems(updatedList: ArrayList<Transformer>) {
        mItemList.addAll(updatedList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mItemList.size
    }

    override fun onBindViewHolder(holder: TransformersFragViewHolder, position: Int) {
        val model: Transformer = mItemList[position]
        holder.title.text = model.name
        holder.author.text = model.team
        holder.country.text = model.rank.toString()
        holder.message.text = model.strength.toString()
        holder.rating.text = model.firepower.toString()
        Glide.with(context).load(model.teamIcon).into(holder.photo)
    }

    class TransformersFragViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val title: TextView = item.findViewById(R.id.title)
        val message: TextView = item.findViewById(R.id.message)
        val rating: TextView = item.findViewById(R.id.rating)
        val author: TextView = item.findViewById(R.id.author)
        val country: TextView = item.findViewById(R.id.country)
        val photo: ImageView = item.findViewById(R.id.photo)
    }
}