package my.android.myapp02

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.getSystemService
import com.google.android.material.snackbar.Snackbar

class Calc1 : AppCompatActivity() {

    var M: Double? = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_calc1)
        setContentView(R.layout.activity_calc2)
        val x = findViewById<EditText>(R.id.num1)
        val y = findViewById<EditText>(R.id.num2)
        val z = findViewById<EditText>(R.id.result)
        val plus = findViewById<Button>(R.id.plus)
        val minus = findViewById<Button>(R.id.minus)
        val memory = findViewById<Button>(R.id.memory)
        val root = findViewById<View>(R.id.root)

        plus.setOnClickListener {
            val num1 = x.text.toString()?.toDoubleOrNull()
            val num2 = y.text.toString()?.toDoubleOrNull()
            val result = if(num1 != null && num2 != null) num1 + num2 else null
            z.setText(result.toString())
            hideKeyBoard(x)
        }

        val num1 = intent.extras?.getDouble("x")
        val num2 = intent.extras?.getDouble("y")
        x.setText(num1?.toString())
        y.setText(num2?.toString())

        minus.setOnClickListener {
            try {
                val num1 = x.text.toString().toDouble()
                val num2 = y.text.toString().toDouble()
                val result = num1 - num2
                z.setText(result.toString())
            } catch(e: Exception) {
                AlertDialog
                    .Builder(this)
                    .setMessage("Ты понял, что сделал не так?")
                    .setTitle("ОШИБКА!")
                    .setPositiveButton("ДА") { a, b -> this.title = "ДА" }
                    .setNeutralButton("НЕ УВЕРЕН", { a, b -> this.title = "ОК" })
                    .setNegativeButton("НЕТ", fun(a, b) {this.title = "НЕТ" })
                    .show()
            }
            hideKeyBoard(x)
        }

        memory.setOnClickListener {
            Toast
                .makeText(this, "До того М=$M", Toast.LENGTH_LONG)
                .show()
            M = z.text.toString()?.toDoubleOrNull()
            Snackbar
                .make(root,"После того M=$M", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", {this.title = M.toString()})
                .show()
        }
    }

    private fun hideKeyBoard(view: View) {

        val imm = getSystemService(InputMethodManager::class.java)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}