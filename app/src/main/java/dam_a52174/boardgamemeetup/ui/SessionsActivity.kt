package dam_a52174.boardgamemeetup.ui

import android.os.Bundle
import dam_a52174.boardgamemeetup.R

class SessionsActivity : BottomNavActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override val contentViewId: Int
        get() = R.layout.activity_sessions
    override val navigationMenuItemId: Int
        get() = R.id.navigation_sessions
}