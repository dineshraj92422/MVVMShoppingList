package com.drmarks.shoppinglist.ui.shoppinglist

import com.drmarks.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}