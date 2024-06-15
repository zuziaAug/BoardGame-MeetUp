package dam_a52174.boardgamemeetup.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.data.BoardGame
import dam_a52174.boardgamemeetup.adapters.GameAdapter
import dam_a52174.boardgamemeetup.nav.BottomNavGuestActivity

class GamesGuestActivity : BottomNavGuestActivity() {

    private lateinit var listView: RecyclerView
    private lateinit var bottomNavigationView: BottomNavigationView
    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_guest)

        // Setup RecyclerView
        listView = findViewById(R.id.gamesGuestRecyclerView)

        // Fetch data from Firestore and set up the RecyclerView
        fetchGamesFromFirestore()

        // Setup BottomNavigationView
        bottomNavigationView = findViewById(R.id.navigation)
        bottomNavigationView.selectedItemId = R.id.navigation_games_guest // Set the correct item as selected
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_welcome -> {
                    // Handle navigation to Main Activity
                    startActivity(Intent(this, WelcomeActivity::class.java))
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

    private fun fetchGamesFromFirestore() {
        db.collection("Games")
            .get()
            .addOnSuccessListener { documents ->
                val gamesList = mutableListOf<BoardGame>()
                for (document in documents) {
                    val game = document.toObject(BoardGame::class.java)
                    gamesList.add(game)
                }
                listView.adapter = GameAdapter(pkGameList = gamesList, context = this)
            }
            .addOnFailureListener { exception ->
                Log.w("GamesGuestActivity", "Error getting documents: ", exception)
            }
    }

    override val contentViewId: Int
        get() = R.layout.activity_games_guest

    override val navigationMenuItemId: Int
        get() = R.id.navigation_games_guest
}
