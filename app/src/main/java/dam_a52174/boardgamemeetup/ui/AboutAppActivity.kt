package dam_a52174.boardgamemeetup.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dam_a52174.boardgamemeetup.R

class AboutAppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)

        val textGoBack = findViewById<TextView>(R.id.textGoBack)
        textGoBack.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
