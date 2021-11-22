package com.example.shoppinglist.UI.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.UI.viewModels.ShoppingViewModel
import com.example.shoppinglist.model.repositories.ShoppingRepository

class ShoppingListFactory(
    private val repository: ShoppingRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repository) as T
    }
}