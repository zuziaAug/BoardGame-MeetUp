package dam_a52174.boardgamemeetup.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import dam_a52174.boardgamemeetup.R

class HomeActivity : BottomNavActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewId)

        // Set up button click listeners
        setupButtonListeners()
    }

    // Function to set up button listeners
    private fun setupButtonListeners() {
        // Find the buttons from the layout
        val buttonGames = findViewById<Button>(R.id.button_games)
        val buttonSessions = findViewById<Button>(R.id.button_sessions)
        val buttonMap = findViewById<Button>(R.id.button_map)

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
    }

    override val contentViewId: Int
        get() = R.layout.activity_home

    override val navigationMenuItemId: Int
        get() = R.id.navigation_home
}
