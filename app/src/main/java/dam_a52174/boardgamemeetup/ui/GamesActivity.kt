package dam_a52174.boardgamemeetup.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.data.MockData

class GamesActivity : BottomNavActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games)

        var listView = findViewById<RecyclerView>(R.id.gamesRecyclerView)
        listView.adapter = GameAdapter(pkGameList = MockData.games, context = this)

        val buttonNewGame = findViewById<Button>(R.id.buttonNewGame)
        buttonNewGame.setOnClickListener {
            val intent = Intent(this, NewGameActivity::class.java)
            startActivity(intent)
        }
    }

    override val contentViewId: Int
        get() = R.layout.activity_games
    override val navigationMenuItemId: Int
        get() = R.id.navigation_games
}