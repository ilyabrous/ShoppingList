package com.example.shoppinglist.model.repositories

import com.androiddevs.grocerylist.data.db.ShoppingDatabase
import com.example.shoppinglist.model.db.entity.ShoppingItem

class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingList() = db.getShoppingDao().getAllShoppingItems()
}