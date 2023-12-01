package my.android.myapp04

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class Sensors : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensors)

        val sensorManager = getSystemService(SensorManager::class.java)
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        sensorManager.registerListener(
            Listener(this),
            sensor,
            SensorManager.SENSOR_DELAY_NORMAL)
    }
}

class Listener(val context: Context): SensorEventListener {
    override fun onSensorChanged(event: SensorEvent?) {
        try {
            val temperature = event!!.values[0]
            Toast
                .makeText(context, "T=$temperature", Toast.LENGTH_LONG)
                .show()
        }
        catch (e: Exception) {}
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

}