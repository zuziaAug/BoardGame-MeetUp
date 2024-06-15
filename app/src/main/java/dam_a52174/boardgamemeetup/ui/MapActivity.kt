package dam_a52174.boardgamemeetup.ui

import android.os.Bundle
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.nav.BottomNavGuestActivity


class MapActivity : BottomNavGuestActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override val contentViewId: Int
        get() = R.layout.activity_map

    override val navigationMenuItemId: Int
        get() = R.id.navigation_map
}