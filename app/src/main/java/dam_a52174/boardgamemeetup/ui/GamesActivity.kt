package dam_a52174.boardgamemeetup.ui

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.data.MockData

class GamesActivity : BottomNavActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    var listView = findViewById<RecyclerView>(R.id.gamesRecyclerView)
    listView.adapter = GameAdapter(pkGameList = MockData.games, context = this)
    }

    override val contentViewId: Int
        get() = R.layout.activity_games
    override val navigationMenuItemId: Int
        get() = R.id.navigation_games
}