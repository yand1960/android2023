package my.android.myapp04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_12)
        val txt1 = findViewById<TextView>(R.id.myNumber)
        txt1.text = MyData.counter.toString();
        MyData.counter += 1
        val root = findViewById<ConstraintLayout>(R.id.root)
        root.setBackgroundColor(0x55FFFF00)

        val next = findViewById<Button>(R.id.next)
        next.setOnClickListener {
            val intent = Intent(this, Activity1::class.java)
            startActivity(intent)
        }
    }
}