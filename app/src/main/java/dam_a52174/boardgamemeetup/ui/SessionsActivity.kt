package dam_a52174.boardgamemeetup.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.adapters.SessionAdapter
import dam_a52174.boardgamemeetup.data.BoardGameSession

class SessionsActivity : AppCompatActivity() {

    private lateinit var listView: RecyclerView
    private lateinit var buttonAddSession: FloatingActionButton
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var userEmailTextView: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sessions)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        listView = findViewById(R.id.sessionsRecyclerView)
        buttonAddSession = findViewById(R.id.buttonAddSession)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Sessions"

        val headerView: View = navView.getHeaderView(0)
        userEmailTextView = headerView.findViewById(R.id.user_email)
        updateHeader(auth.currentUser)

        listView.layoutManager = GridLayoutManager(this, 1)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            when (menuItem.itemId) {
                R.id.nav_login -> startActivity(Intent(this, LoginActivity::class.java))
                R.id.nav_logout -> {
                    auth.signOut()
                    drawerLayout.closeDrawer(navView)
                    startActivity(Intent(this, GamesActivity::class.java))
                }
                R.id.nav_games -> startActivity(Intent(this, GamesActivity::class.java))
                R.id.nav_sessions -> {} // Already in SessionsActivity
                R.id.nav_map -> startActivity(Intent(this, MapActivity::class.java))
                R.id.nav_about -> startActivity(Intent(this, AboutAppActivity::class.java))
                R.id.nav_language -> startActivity(Intent(this, LanguageActivity::class.java))
                R.id.nav_account_settings -> startActivity(Intent(this, AccountSettingsActivity::class.java))
            }
            true
        }

        updateDrawerMenu(auth.currentUser != null)

        buttonAddSession.setOnClickListener {
            if (auth.currentUser != null) {
                startActivity(Intent(this, NewSessionActivity::class.java))
            } else {
                Toast.makeText(this, "Please log in to use this feature", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        updateDrawerMenu(auth.currentUser != null)
        fetchSessionsFromFirestore()
    }

    override fun onResume() {
        super.onResume()
        fetchSessionsFromFirestore()
    }

    private fun updateHeader(user: FirebaseUser?) {
        if (user != null) {
            userEmailTextView.text = user.email
            userEmailTextView.visibility = View.VISIBLE
        } else {
            userEmailTextView.visibility = View.GONE
        }
    }

    private fun updateDrawerMenu(isLoggedIn: Boolean) {
        val menuRes = if (isLoggedIn) R.menu.menu_logged_in else R.menu.menu_logged_out
        navView.menu.clear()
        navView.inflateMenu(menuRes)
    }

    private fun fetchSessionsFromFirestore() {
        db.collection("BoardGameSessions")
            .get()
            .addOnSuccessListener { documents ->
                val sessionList = mutableListOf<BoardGameSession>()
                for (document in documents) {
                    val session = document.toObject(BoardGameSession::class.java)
                    sessionList.add(session)
                }
                setupRecyclerView(sessionList)
            }
            .addOnFailureListener { exception ->
                Log.w("SessionsActivity", "Error getting documents: ", exception)
            }
    }

    private fun setupRecyclerView(sessions: List<BoardGameSession>) {
        val adapter = SessionAdapter(sessions, context = this)
        listView.adapter = adapter
        adapter.setOnClickListener(object : SessionAdapter.OnClickListener {
            override fun onClick(position: Int, model: BoardGameSession) {
                val intent = Intent(this@SessionsActivity, SessionDetailActivity::class.java).apply {
                    putExtra("SESSION_ID", model.id)
                }
                startActivity(intent)
            }
        })
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(navView)) {
            drawerLayout.closeDrawer(navView)
        } else {
            super.onBackPressed()
        }
    }
}
