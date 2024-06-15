package dam_a52174.boardgamemeetup.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.data.BoardGameSession
import dam_a52174.boardgamemeetup.adapters.SessionAdapter
import dam_a52174.boardgamemeetup.nav.BottomNavActivity

class SessionsActivity : BottomNavActivity() {

    private lateinit var listView: RecyclerView
    private lateinit var buttonAddSession: Button
    private lateinit var bottomNavigationView: BottomNavigationView
    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

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

        // Setup RecyclerView
        listView = findViewById(R.id.sessionsRecyclerView)

        // Fetch data from Firestore and set up the RecyclerView
        fetchSessionsFromFirestore()

        buttonAddSession = findViewById(R.id.buttonAddSession)
        buttonAddSession.setOnClickListener {
            openFragment(NewSessionFragment())
        }
    }

    private fun fetchSessionsFromFirestore() {
        db.collection("Sessions")
            .get()
            .addOnSuccessListener { documents ->
                val sessionList = mutableListOf<BoardGameSession>()
                for (document in documents) {
                    val session = document.toObject(BoardGameSession::class.java)
                    sessionList.add(session)
                }
                listView.adapter = SessionAdapter(pkSessionList = sessionList, context = this)
            }
            .addOnFailureListener { exception ->
                Log.w("SessionsActivity", "Error getting documents: ", exception)
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
