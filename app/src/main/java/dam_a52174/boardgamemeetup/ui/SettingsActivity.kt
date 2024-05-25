package dam_a52174.boardgamemeetup.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dam_a52174.boardgamemeetup.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        // Find the buttons from the layout
        val buttonAboutApp = findViewById<Button>(R.id.buttonAboutApp)
        val buttonAccountSettings = findViewById<Button>(R.id.buttonAccountSettings)
        val textGoBack = findViewById<TextView>(R.id.textGoBack)

        // Set up click listeners
        buttonAboutApp.setOnClickListener {
            // Navigate to GamesActivity
            startActivity(Intent(this, AboutAppActivity::class.java))
        }

        buttonAccountSettings.setOnClickListener {
            // Navigate to MapActivity
            startActivity(Intent(this, AccountSettingsActivity::class.java))
        }

        textGoBack.setOnClickListener{
            // Navigate to LoginActivity
            startActivity(Intent(this, WelcomeActivity::class.java))
        }
    }
}
