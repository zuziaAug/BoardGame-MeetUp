package dam_a52174.boardgamemeetup.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.data.MockData

class SessionsActivity : BottomNavActivity() {

    private lateinit var listView: RecyclerView
    private lateinit var buttonAddSession: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sessions)

        listView = findViewById(R.id.sessionsRecyclerView)
        listView.adapter = SessionAdapter(pkSessionList = MockData.sessions, context = this)

        buttonAddSession = findViewById(R.id.buttonAddSession)
        buttonAddSession.setOnClickListener {
            openFragment(NewSessionFragment())
        }
    }

    private fun openFragment(fragment: Fragment) {
        // Hide the existing views
        listView.visibility = View.GONE
        buttonAddSession.visibility = View.GONE

        // Show the fragment container and load the fragment
        val fragmentContainer = findViewById<View>(R.id.fragment_container_sessions)
        fragmentContainer.visibility = View.VISIBLE

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_sessions, fragment)
            .addToBackStack(null)
            .commit()
    }

    override val contentViewId: Int
        get() = R.layout.activity_sessions

    override val navigationMenuItemId: Int
        get() = R.id.navigation_sessions
}
