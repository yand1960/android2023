package my.android.myapp04

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView

class Camera : AppCompatActivity() {

    val CODE = 12345
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        val btn = findViewById<Button>(R.id.btnPhoto)
        btn.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE) {
            //print("got it")
            val bmp = data?.extras?.get("data") as Bitmap
            findViewById<ImageView>(R.id.imagePhoto).setImageBitmap(bmp)
        }
    }
}