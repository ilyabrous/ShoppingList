package com.example.shoppinglist.UI.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.grocerylist.data.db.ShoppingDatabase
import com.example.shoppinglist.R
import com.example.shoppinglist.UI.dialogs.ShoppingListDialog
import com.example.shoppinglist.UI.dialogs.ShoppingListListener
import com.example.shoppinglist.UI.viewModelFactories.ShoppingListFactory
import com.example.shoppinglist.UI.viewModels.ShoppingViewModel
import com.example.shoppinglist.adapters.ShoppingListAdapter
import com.example.shoppinglist.databinding.ActivityShoppingBinding
import com.example.shoppinglist.model.db.entity.ShoppingItem
import com.example.shoppinglist.model.repositories.ShoppingRepository
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private lateinit var binding: ActivityShoppingBinding
    private val factory: ShoppingListFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)
        val adapter = ShoppingListAdapter(viewModel, listOf())
        binding.rvShoppingItems.layoutManager = LinearLayoutManager(this)
        binding.rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.shoppingList = it
            adapter.notifyDataSetChanged()
        })


        binding.fab.setOnClickListener {
            ShoppingListDialog(this,
                object: ShoppingListListener{
                    override fun onAddButtonClick(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }

                }).show()

        }

    }
}