package dam_a52174.boardgamemeetup.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.CheckBox
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import dam_a52174.boardgamemeetup.R
import java.util.Locale

class LanguageActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var checkBoxEnglish: CheckBox
    private lateinit var checkBoxPortuguese: CheckBox
    private lateinit var checkBoxPolish: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        // Setup toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Language"

        // Setup drawer and navigation view
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

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
            when (menuItem.itemId) {
                R.id.nav_login -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    true
                }
                R.id.nav_games -> {
                    startActivity(Intent(this, GamesActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // Setup checkboxes
        checkBoxEnglish = findViewById(R.id.checkBoxEnglish)
        checkBoxPortuguese = findViewById(R.id.checkBoxPortuguese)
        checkBoxPolish = findViewById(R.id.checkBoxPolish)

        checkBoxEnglish.setOnClickListener {
            updateLocale("en")
            checkBoxPortuguese.isChecked = false
            checkBoxPolish.isChecked = false
        }

        checkBoxPortuguese.setOnClickListener {
            updateLocale("pt")
            checkBoxEnglish.isChecked = false
            checkBoxPolish.isChecked = false
        }

        checkBoxPolish.setOnClickListener {
            updateLocale("pl")
            checkBoxEnglish.isChecked = false
            checkBoxPortuguese.isChecked = false
        }
    }

    private fun updateLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        recreate() // Restart activity to apply new locale
    }

    // Handle navigation drawer icon click to open/close the drawer
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    drawerLayout.openDrawer(GravityCompat.START)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Optional: Close the drawer when back button is pressed, if drawer is open
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
