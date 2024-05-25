package dam_a52174.boardgamemeetup.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dam_a52174.boardgamemeetup.R

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupButtonListeners()
    }
    private fun setupButtonListeners() {
        // Find the buttons from the layout
        val buttonCheckGames = findViewById<Button>(R.id.buttonCheckGames)
        val buttonCheckMap = findViewById<Button>(R.id.buttonCheckMap)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val buttonSettings = findViewById<ImageButton>(R.id.buttonSettings)

        // Set up click listeners
        buttonCheckGames.setOnClickListener {
            // Navigate to GamesActivity
            startActivity(Intent(this, GamesGuestActivity::class.java))
        }

        buttonCheckMap.setOnClickListener {
            // Navigate to MapActivity
            startActivity(Intent(this, MapActivity::class.java))
        }

        buttonLogin.setOnClickListener{
            // Navigate to LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
        }

        buttonSettings.setOnClickListener{
            // Navigate to SettingsFragment
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}