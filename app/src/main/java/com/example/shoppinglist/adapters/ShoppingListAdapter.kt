package com.example.shoppinglist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.UI.viewModels.ShoppingViewModel
import com.example.shoppinglist.databinding.ShoppingItemBinding
import com.example.shoppinglist.model.db.entity.ShoppingItem

class ShoppingListAdapter(
    val viewModel: ShoppingViewModel,
    var shoppingList: List<ShoppingItem>
) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingViewHolder>() {

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
         val inflater = LayoutInflater.from(parent.context)
         val binding = ShoppingItemBinding.inflate(inflater, parent, false)



         return ShoppingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = shoppingList[position]


        holder.binding.tvName.text = currentShoppingItem.name
        holder.binding.tvAmount.text = currentShoppingItem.amount.toString()

        holder.binding.ivDelete.setOnClickListener {
            viewModel.delete(currentShoppingItem)
        }
        holder.binding.ivMinus.setOnClickListener {
            currentShoppingItem.amount -= 1
            viewModel.upsert(currentShoppingItem)
        }
        holder.binding.ivPlus.setOnClickListener {
            currentShoppingItem.amount += 1
            viewModel.upsert(currentShoppingItem)
        }
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }

    class ShoppingViewHolder(val binding: ShoppingItemBinding): RecyclerView.ViewHolder(binding.root)
}