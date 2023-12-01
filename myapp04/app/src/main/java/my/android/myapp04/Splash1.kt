package my.android.myapp04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import java.net.URL

class Splash1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash1)
        val browser = findViewById<WebView>(R.id.browser)
        browser.loadUrl("https://developer.alexanderklimov.ru/android/views/webview.php")
//        browser.loadUrl("https://yandex.ru")
    }
}