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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.adapters.GameAdapter
import dam_a52174.boardgamemeetup.data.BoardGame

class GamesActivity : AppCompatActivity() {

    private lateinit var listView: RecyclerView
    private lateinit var buttonNewGame: FloatingActionButton
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var userEmailTextView: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games)

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance()
        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance()

        listView = findViewById(R.id.gamesRecyclerView)
        buttonNewGame = findViewById(R.id.buttonNewGame)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        // Setup toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Games"

        // Setup header view
        val headerView: View = navView.getHeaderView(0)
        userEmailTextView = headerView.findViewById(R.id.user_email)
        updateHeader(auth.currentUser)

        // Setup RecyclerView
        listView.layoutManager = GridLayoutManager(this, 1)
        fetchGamesFromFirestore()

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
                R.id.nav_favorites -> startActivity(Intent(this, FavoritesActivity::class.java))
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

        // Setup FloatingActionButton
        buttonNewGame.setOnClickListener {
            if (auth.currentUser != null) {
                startActivity(Intent(this, NewGameActivity::class.java))
            } else {
                Toast.makeText(this, "Please log in to use this feature", Toast.LENGTH_SHORT).show()
            }
        }
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
                Log.w("GamesActivity", "Error getting documents: ", exception)
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

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(navView)) {
            drawerLayout.closeDrawer(navView)
        } else {
            super.onBackPressed()
        }
    }
}
