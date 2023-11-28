package my.android.myapp02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Calc1 : AppCompatActivity() {

    var M: Double? = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc1)
        val x = findViewById<EditText>(R.id.num1)
        val y = findViewById<EditText>(R.id.num2)
        val z = findViewById<EditText>(R.id.result)
        val plus = findViewById<Button>(R.id.plus)
        val minus = findViewById<Button>(R.id.minus)
        val memory = findViewById<Button>(R.id.memory)
        plus.setOnClickListener {
            val num1 = x.text.toString()?.toDoubleOrNull()
            val num2 = y.text.toString()?.toDoubleOrNull()
            val result = if(num1 != null && num2 != null) num1 + num2 else null
            z.setText(result.toString())
        }

        minus.setOnClickListener {
            val num1 = x.text.toString().toDouble()
            val num2 = y.text.toString().toDouble()
            val result = num1 - num2
            z.setText(result.toString())
        }

        memory.setOnClickListener {
            M = z.text.toString()?.toDoubleOrNull()
        }
    }
}