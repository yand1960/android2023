package my.android.myapp03

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView

class Media : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)
        val btn1 = findViewById<Button>(R.id.button)
        btn1.setOnClickListener {
            val player = MediaPlayer.create(this, R.raw.bell)
            player.start()
        }

        val video = findViewById<VideoView>(R.id.videoView)
        video.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.clip))
        video.start()

    }
}