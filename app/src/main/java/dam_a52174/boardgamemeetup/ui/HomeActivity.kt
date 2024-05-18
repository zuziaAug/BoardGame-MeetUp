package dam_a52174.boardgamemeetup.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.auth.LoginActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Set up button click listeners
        setupButtonListeners()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // Finish current activity to prevent user from going back
        }
    }

    // Function to set up button listeners
    private fun setupButtonListeners() {
        // Find the buttons from the layout
        val buttonGames = findViewById<Button>(R.id.button_games)
        val buttonSessions = findViewById<Button>(R.id.button_sessions)
        val buttonMap = findViewById<Button>(R.id.button_map)
        val buttonLogout = findViewById<Button>(R.id.logoutButton)

        // Set up click listeners
        buttonGames.setOnClickListener {
            // Navigate to GamesActivity
            startActivity(Intent(this, GamesActivity::class.java))
        }

        buttonSessions.setOnClickListener {
            // Navigate to SessionsActivity
            startActivity(Intent(this, SessionsActivity::class.java))
        }

        buttonMap.setOnClickListener {
            // Navigate to MapActivity
            startActivity(Intent(this, MapActivity::class.java))
        }

        buttonLogout.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
