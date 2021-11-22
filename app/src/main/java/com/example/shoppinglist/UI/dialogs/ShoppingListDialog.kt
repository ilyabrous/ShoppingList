package com.example.shoppinglist.UI.dialogs

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.DialogAddShoppingItemBinding
import com.example.shoppinglist.model.db.entity.ShoppingItem

interface ShoppingListListener{
    fun onAddButtonClick(item: ShoppingItem)
}

class ShoppingListDialog(
    context: Context,
    var shoppingListListener: ShoppingListListener
) : AppCompatDialog(context) {

    val binding = DialogAddShoppingItemBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        binding.tvAdd.setOnClickListener {
            if(!AreCorrectInputs()){
                Toast.makeText(context, "Please, enter all the information", Toast.LENGTH_LONG ).show()
            }
            val shoppingItem = ShoppingItem( binding.etName.text.toString(), Integer.parseInt(binding.etAmount.text.toString()) )
            shoppingListListener.onAddButtonClick(shoppingItem)
            dismiss()
        }
        binding.tvCancel.setOnClickListener {
            cancel()
        }


    }

    fun AreCorrectInputs() : Boolean{
        if(binding.etAmount.text.isNotBlank() || binding.etName.text.isNotBlank()) return true
        return false
    }
}