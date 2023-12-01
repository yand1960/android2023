package my.android.myapp04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView

class Anim : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim)
        val btnStart = findViewById<Button>(R.id.startAnim)
        val btnStop = findViewById<Button>(R.id.stopAnim)
        val txt = findViewById<TextView>(R.id.textAnim)

        btnStart.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
            txt.startAnimation(animation)
        }

        btnStop.setOnClickListener {
            txt.clearAnimation()
        }
    }
}