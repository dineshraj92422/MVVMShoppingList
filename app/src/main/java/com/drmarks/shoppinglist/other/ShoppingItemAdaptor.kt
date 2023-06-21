package com.drmarks.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.drmarks.shoppinglist.R
import com.drmarks.shoppinglist.data.db.entities.ShoppingItem
import com.drmarks.shoppinglist.ui.shoppinglist.ShoppingViewModel


class ShoppingItemAdaptor(
    var items:List<ShoppingItem>,
    private val viewModel:ShoppingViewModel
):RecyclerView.Adapter<ShoppingItemAdaptor.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_items, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]

        holder.itemView.findViewById<TextView>(R.id.tvName).text = curShoppingItem.name
        holder.itemView.findViewById<TextView>(R.id.tvAmount).text = "${curShoppingItem.amount}"

        holder.itemView.findViewById<TextView>(R.id.ivDelete).setOnClickListener {
            viewModel.delete(curShoppingItem)
        }

        holder.itemView.findViewById<TextView>(R.id.ivMinus).setOnClickListener {
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }

        holder.itemView.findViewById<TextView>(R.id.ivMinus).setOnClickListener {
            if(curShoppingItem.amount > 0) {
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }
        }    }

    override fun getItemCount(): Int {
        return items.size    }

    inner  class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}