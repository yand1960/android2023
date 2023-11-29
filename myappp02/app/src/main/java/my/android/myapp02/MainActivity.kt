package my.android.myapp02

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val receiver: BroadcastReceiver = CalcReceiver()
        val filter = IntentFilter()
        filter.addAction("my.android.myapp02.CALC_INTENT")
        registerReceiver(receiver, filter)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.title = item.title
        val root = findViewById<ConstraintLayout>(R.id.main_root)
        if (item.title == "Желтый")
            root.setBackgroundColor(0x33FFFF00.toInt())
        if (item.title == "Зеленый")
            root.setBackgroundColor(0x3300FF00.toInt())
        if (item.title == "Синий")
            root.setBackgroundColor(0x330000FF.toInt())
        if (item.title == "Калькулятор") {
            val intent = Intent(this, Calc1::class.java)
            intent.putExtra("x", 0.0)
            intent.putExtra("y", 0.0)
            startActivity(intent)
        }

        if (item.title == "Позвонить") {
            val p = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)
            if (p != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CALL_PHONE),
                    0
                )
            } else {
                val intent = Intent(Intent.ACTION_CALL)
                intent.setData(Uri.parse("tel:+71234567"))
                startActivity(intent)
            }
        }

        if (item.title == "SMS") {
            val p = ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS)
            if (p != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.SEND_SMS),
                    0
                )
            } else {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse("sms:+71234567"))
                startActivity(intent)
            }
        }

        if (item.title == "SMS2") {
            var smsManager: SmsManager
            if (getSystemService(SmsManager::class.java) != null)
                smsManager = getSystemService(SmsManager::class.java)
            else
                smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("+71234568", null, "QWERTY!", null, null)

            Toast
                .makeText(this, "SMS Sent", Toast.LENGTH_LONG)
                .show()
        }

        return super.onOptionsItemSelected(item)
    }
}

class CalcReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val history = (context as MainActivity).findViewById<TextView>(R.id.history)
        val message = intent?.getStringExtra("message")
        history.append("\n$message")
    }

}