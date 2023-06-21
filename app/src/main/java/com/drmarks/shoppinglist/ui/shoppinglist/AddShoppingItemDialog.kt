package com.drmarks.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.drmarks.shoppinglist.R
import com.drmarks.shoppinglist.data.db.entities.ShoppingItem

class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener) :
    AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_shopping_item)

        findViewById<TextView>(R.id.tvAdd)?.setOnClickListener {
            val name = findViewById<EditText>(R.id.etName)?.text.toString()
            val amount = findViewById<EditText>(R.id.etAmount)?.text.toString().toInt()
            if(name.isEmpty()) {
                Toast.makeText(context, "Please enter a name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, amount)
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        findViewById<TextView>(R.id.tvCancel)?.setOnClickListener {
            cancel()
        }
    }
}