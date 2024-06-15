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
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.adapters.SessionAdapter
import dam_a52174.boardgamemeetup.data.BoardGameSession

class SessionDetailActivity : AppCompatActivity(){

    private lateinit var buttonPayment: FloatingActionButton
    private lateinit var buttonFavorite: FloatingActionButton
    private lateinit var listView: RecyclerView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var userEmailTextView: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session_detail)

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance()
        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance()

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        buttonPayment = findViewById(R.id.fab_payment)
        buttonFavorite = findViewById(R.id.fab_favorite)
        
        buttonPayment.setOnClickListener{
            Toast.makeText(this, "Feature to be implemented", Toast.LENGTH_SHORT).show()
        }

        buttonFavorite.setOnClickListener{
            Toast.makeText(this, "Feature to be implemented", Toast.LENGTH_SHORT).show()
        }

        // Setup toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Session detail"

        // Setup header view
        val headerView: View = navView.getHeaderView(0)
        userEmailTextView = headerView.findViewById(R.id.user_email)
        updateHeader(auth.currentUser)

        // Handle drawer toggle
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Handle navigation item clicks
        navView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            when (menuItem.itemId) {
                R.id.nav_login -> startActivity(Intent(this, LoginActivity::class.java))
                R.id.nav_logout -> {
                    auth.signOut()
                    drawerLayout.closeDrawer(navView)
                    recreate()
                }
                R.id.nav_games -> startActivity(Intent(this, GamesActivity::class.java))
                R.id.nav_sessions -> startActivity(Intent(this, SessionsActivity::class.java))
                R.id.nav_map -> startActivity(Intent(this, MapActivity::class.java))
                R.id.nav_about -> startActivity(Intent(this, AboutAppActivity::class.java))
                R.id.nav_language -> startActivity(Intent(this, LanguageActivity::class.java))
                R.id.nav_account_settings -> startActivity(Intent(this, AccountSettingsActivity::class.java))
            }
            true
        }

        // Update drawer menu based on login status
        updateDrawerMenu(auth.currentUser != null)

        // Retrieve the game ID from the intent
        val sessionId = intent.getIntExtra("SESSION_ID", -1)

        if (sessionId != -1) {
            fetchBoardGameSession(sessionId)
        } else {
            Toast.makeText(this, "Error loading session details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchBoardGameSession(id: Int) {
        db.collection("BoardGameSessions").document(id.toString())
            .get()
            .addOnSuccessListener { document ->
                val session = document.toObject(BoardGameSession::class.java)
                if (session != null) {
                    updateUI(session)
                } else {
                    Toast.makeText(this, "Session not found", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error getting session details: $exception", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateUI(session: BoardGameSession) {

        val detailDate = findViewById<TextView>(R.id.detailDate)
        detailDate.text = "Date: ${session.date}"

        val detailGame = findViewById<TextView>(R.id.detailGame)
        detailGame.text = "Game: ${session.gameName}"

        val detailPlace = findViewById<TextView>(R.id.detailPlace)
        detailPlace.text = "Place: ${session.placeName}"

        val detailDescription = findViewById<TextView>(R.id.detailDescription)
        detailDescription.text = session.description

        val detailPrice = findViewById<TextView>(R.id.detailPrice)
        detailPrice.text = "Price: ${session.price} euro"
    }

    override fun onStart() {
        super.onStart()
        updateDrawerMenu(auth.currentUser != null)
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

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(navView)) {
            drawerLayout.closeDrawer(navView)
        } else {
            super.onBackPressed()
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
                Log.w("SessionDetailActivity", "Error getting documents: ", exception)
            }
    }
}
