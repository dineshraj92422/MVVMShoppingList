package com.drmarks.shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drmarks.shoppinglist.R
import com.drmarks.shoppinglist.data.db.ShoppingDatabase
import com.drmarks.shoppinglist.data.db.entities.ShoppingItem
import com.drmarks.shoppinglist.data.repositories.ShoppingRepository
import com.drmarks.shoppinglist.other.ShoppingItemAdaptor
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)

        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adaptor = ShoppingItemAdaptor(listOf(),viewModel)
        findViewById<RecyclerView>(R.id.rvShoppingItems).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.rvShoppingItems).adapter =adaptor

        viewModel.getAllItems().observe(this, Observer {
             adaptor.items =it
            adaptor.notifyDataSetChanged()
        })

            findViewById<FloatingActionButton>(R.id.fab).setOnClickListener{
                AddShoppingItemDialog(this,
                object : AddDialogListener{
                    override fun onAddButtonClicked(item: ShoppingItem) {
                       viewModel.upsert(item )
                    }
                }).show()
            }
    }
}