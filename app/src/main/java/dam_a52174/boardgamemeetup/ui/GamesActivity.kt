package dam_a52174.boardgamemeetup.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.data.MockData

class GamesActivity : BottomNavActivity() {

    private lateinit var listView: RecyclerView
    private lateinit var buttonNewGame: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games)

        listView = findViewById(R.id.gamesRecyclerView)
        listView.adapter = GameAdapter(pkGameList = MockData.games, context = this)

        buttonNewGame = findViewById(R.id.buttonNewGame)
        buttonNewGame.setOnClickListener {
            openFragment(NewGameFragment())
        }
    }

    private fun openFragment(fragment: Fragment) {
        // Hide the existing views
        listView.visibility = View.GONE
        buttonNewGame.visibility = View.GONE

        // Show the fragment container and load the fragment
        val fragmentContainer = findViewById<View>(R.id.fragment_container_games)
        fragmentContainer.visibility = View.VISIBLE

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_games, fragment)
            .addToBackStack(null)
            .commit()
    }

    override val contentViewId: Int
        get() = R.layout.activity_games

    override val navigationMenuItemId: Int
        get() = R.id.navigation_games
}
