package dam_a52174.boardgamemeetup.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.auth.LoginActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Setup BottomNavigationView
        bottomNavigationView = findViewById(R.id.navigation)
        bottomNavigationView.selectedItemId = R.id.navigation_home // Set the correct item as selected
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Handle navigation to HomeActivity (already here)
                    true
                }
                R.id.navigation_games -> {
                    // Handle navigation to GamesActivity (already here)
                    startActivity(Intent(this, GamesActivity::class.java))
                    true
                }
                R.id.navigation_sessions -> {
                    // Handle navigation to SessionsActivity
                    startActivity(Intent(this, SessionsActivity::class.java))
                    true
                }
                else -> false
            }
        }

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
        val buttonWelcome = findViewById<Button>(R.id.button_welcome_page)
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

        buttonWelcome.setOnClickListener {
            // Navigate to MainActivity (Welcome page)
            startActivity(Intent(this, MainActivity::class.java))
        }

        buttonLogout.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
