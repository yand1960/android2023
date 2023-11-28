package my.android.myapp02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.title = item.title
        val root = findViewById<ConstraintLayout>(R.id.main_root)
        if(item.title == "Желтый")
            root.setBackgroundColor(0x33FFFF00.toInt())
        if(item.title == "Зеленый")
            root.setBackgroundColor(0x3300FF00.toInt())
        if(item.title == "Синий")
            root.setBackgroundColor(0x330000FF.toInt())
        if(item.title == "Калькулятор") {
            val intent = Intent(this, Calc1::class.java)
            intent.putExtra("x", 0.0)
            intent.putExtra("y", 0.0)
            startActivity(intent)
        }


        return super.onOptionsItemSelected(item)
    }
}