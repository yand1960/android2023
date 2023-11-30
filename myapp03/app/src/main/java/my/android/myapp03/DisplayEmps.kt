package my.android.myapp03

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.StrictMode
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class DisplayEmps : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //val emps = RepositoryStub().employees
//        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
//        StrictMode.setThreadPolicy(policy)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_emps)

        val handler = Handler(Looper.getMainLooper())
        Thread {
//            val emps = RepositoryWebService(this).employees
//            val emps = RepositoryCBR(this).employees
//            val emps = RepositoryAsset(this).employees
            val emps = RepositoryFile(this).employees
//            val emps = RepositoryDB(this).employees
            handler.post {
                renderEmps(emps)
            }
        }.start()

    }

    private fun renderEmps(emps: List<Employee>) {
        val root = findViewById<LinearLayout>(R.id.root)
//        val root = findViewById<ScrollView>(R.id.display)
//
        var i = 0
        for(e in emps) {
            val txt1 = TextView(this)
            txt1.text = e.firstName

            val txt2 = TextView(this)
            txt2.text = e.lastName

            val txt3 = TextView(this)
            txt3.text = e.salary.toString()

            if (i % 2 == 1) {
                val color = 0x66999999
                txt1.setBackgroundColor(color)
                txt2.setBackgroundColor(color)
                txt3.setBackgroundColor(color)
            }

            root.addView(txt1)
            root.addView(txt2)
            root.addView(txt3)

            i++
        }
    }
}