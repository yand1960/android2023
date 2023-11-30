package my.android.myapp03

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class DisplayEmps : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val emps = RepositoryStub().employees

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_emps)
        val root = findViewById<LinearLayout>(R.id.root)

        var i = 0
        for(e in emps) {
            val txt1 = TextView(this)
            txt1.text = e.firstName

            val txt2 = TextView(this)
            txt2.text = e.lastName

            val txt3 = TextView(this)
            txt3.text = e.salary.toString()

            if (i % 2 == 1) {
                txt1.setBackgroundColor(Color.CYAN)
                txt2.setBackgroundColor(Color.CYAN)
                txt3.setBackgroundColor(Color.CYAN)
            }

            root.addView(txt1)
            root.addView(txt2)
            root.addView(txt3)

            i++
        }
    }
}