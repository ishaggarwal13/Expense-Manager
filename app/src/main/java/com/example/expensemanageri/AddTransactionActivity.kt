package com.example.expensemanageri

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTransactionActivity : AppCompatActivity() {
    private lateinit var btn : Button
    private lateinit var cbtn : ImageButton
    private lateinit var desInput: TextInputEditText
    private lateinit var lbInput: TextInputEditText
    private lateinit var amtInput: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        btn = findViewById(R.id.addTransactionBtn)
        cbtn = findViewById(R.id.closeBtn)
        desInput = findViewById(R.id.descriptionInput)
        lbInput = findViewById(R.id.labelInput)
        amtInput = findViewById(R.id.amountInput)

        lbInput.addTextChangedListener {
            if(it!!.count() > 0)
                lbInput.error = null
        }

        amtInput.addTextChangedListener {
            if(it!!.count() > 0)
                amtInput.error = null
        }

        btn.setOnClickListener {
            val label = lbInput.text.toString()
            val description = desInput.text.toString()
            val amount = amtInput.text.toString().toDoubleOrNull()

            if(label.isEmpty())
                lbInput.error = "Please enter a valid label"

            else if(amount == null)
                amtInput.error = "Please enter a valid amount"
            else {
                val transaction  = Transaction(0, label, amount, description)
                insert(transaction)
            }
        }

        cbtn.setOnClickListener {
            finish()
        }
    }

    private fun insert(transaction: Transaction){
        val db = Room.databaseBuilder(this,
            AppDatabase::class.java,
            "transactions").build()

        GlobalScope.launch {
            try {
                db.transactionDao().insertAll(transaction)
                finish()
            } catch (e: Exception) {
                // Handle database update error
                e.printStackTrace()
            }
        }
    }
}