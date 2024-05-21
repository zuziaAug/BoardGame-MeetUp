package dam_a52174.boardgamemeetup.ui.guest

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.data.MockData
import dam_a52174.boardgamemeetup.ui.GameAdapter
import dam_a52174.boardgamemeetup.ui.MainActivity
import dam_a52174.boardgamemeetup.ui.MapActivity

class GamesGuestActivity : BottomNavGuestActivity() {

    private lateinit var listView: RecyclerView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_guest)

        // Setup RecyclerView
        listView = findViewById(R.id.gamesGuestRecyclerView)
        listView.adapter = GameAdapter(pkGameList = MockData.games, context = this)

        // Setup BottomNavigationView
        bottomNavigationView = findViewById(R.id.navigation)
        bottomNavigationView.selectedItemId = R.id.navigation_games_guest // Set the correct item as selected
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_welcome -> {
                    // Handle navigation to Main Activity
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.navigation_games_guest -> {
                    // Handle navigation to GamesGuest Activity (already here)
                    true
                }
                R.id.navigation_map -> {
                    // Handle navigation to Map Activity
                    startActivity(Intent(this, MapActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    override val contentViewId: Int
        get() = R.layout.activity_games_guest

    override val navigationMenuItemId: Int
        get() = R.id.navigation_games_guest
}
