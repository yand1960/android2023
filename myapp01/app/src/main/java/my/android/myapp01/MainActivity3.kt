package my.android.myapp01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val btn1 = findViewById<Button>(R.id.btn1)
        val txt1 = findViewById<TextView>(R.id.txt1)
        val edt1 = findViewById<EditText>(R.id.edt1)
        btn1.setOnClickListener {
            val user = edt1.text;
            btn1.text = "Hello, $user!"
            txt1.text = "Hello, $user!"
            edt1.setText("$user - балда")
        }

    }
}