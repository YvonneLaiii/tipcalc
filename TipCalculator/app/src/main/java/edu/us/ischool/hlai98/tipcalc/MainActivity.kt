package edu.us.ischool.hlai98.tipcalc

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.text.NumberFormat
import java.util.*
import android.view.Menu;

class MainActivity : AppCompatActivity() {
    private var input : String = ""
    private val cur : Regex = Regex("[$,.]")
    private var percentage : Double = 0.15
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val format = NumberFormat.getCurrencyInstance(Locale.US)
        val entered =findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        button.isEnabled = false

        entered.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                button.isEnabled = true
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().equals(input)) {
                    entered.removeTextChangedListener(this)
                    var temp = s.toString().replace(cur, "")
                    var number = temp.toDouble()
                    var formatted = format.format((number / 100))
                    entered.setText(formatted)
                    entered.setSelection(formatted.length)
                    input = formatted
                    entered.addTextChangedListener(this)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })
        button.setOnClickListener{
            var amount = ((entered.text.toString().replace(cur, "")).toDouble() / 100) * percentage
            toast(format.format(amount))
        }
    }
    private fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}