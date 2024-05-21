package dam_a52174.boardgamemeetup.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.auth.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
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
    }
}