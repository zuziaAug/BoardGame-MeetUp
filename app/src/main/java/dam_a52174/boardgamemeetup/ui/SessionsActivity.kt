package dam_a52174.boardgamemeetup.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.data.MockData
import dam_a52174.boardgamemeetup.ui.adapters.SessionAdapter
import dam_a52174.boardgamemeetup.ui.nav.BottomNavActivity

class SessionsActivity : BottomNavActivity() {

    private lateinit var listView: RecyclerView
    private lateinit var buttonAddSession: Button
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sessions)

        // Setup BottomNavigationView
        bottomNavigationView = findViewById(R.id.navigation)
        bottomNavigationView.selectedItemId = R.id.navigation_sessions // Set the correct item as selected
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Handle navigation to HomeActivity
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.navigation_games -> {
                    // Handle navigation to GamesActivity
                    startActivity(Intent(this, GamesActivity::class.java))
                    true
                }
                R.id.navigation_sessions -> {
                    // Handle navigation to SessionsActivity (already here)
                    true
                }
                else -> false
            }
        }

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
