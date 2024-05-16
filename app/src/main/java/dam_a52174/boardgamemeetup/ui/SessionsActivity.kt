package dam_a52174.boardgamemeetup.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.data.MockData

class SessionsActivity : BottomNavActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sessions)

        val listView = findViewById<RecyclerView>(R.id.sessionsRecyclerView)
        listView.adapter = SessionAdapter(pkSessionList = MockData.sessions, context = this)

        val buttonAddSession = findViewById<Button>(R.id.buttonAddSession)
        buttonAddSession.setOnClickListener {
            val intent = Intent(this, NewSessionActivity::class.java)
            startActivity(intent)
        }
    }

    override val contentViewId: Int
        get() = R.layout.activity_sessions
    override val navigationMenuItemId: Int
        get() = R.id.navigation_sessions
}