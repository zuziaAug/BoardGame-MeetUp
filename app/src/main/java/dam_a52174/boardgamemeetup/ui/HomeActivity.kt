package dam_a52174.boardgamemeetup.ui

import android.os.Bundle
import dam_a52174.boardgamemeetup.R

class HomeActivity : BottomNavActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override val contentViewId: Int
        get() = R.layout.activity_home
    override val navigationMenuItemId: Int
        get() = R.id.navigation_home
}