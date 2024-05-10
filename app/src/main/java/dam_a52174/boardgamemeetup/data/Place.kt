package dam_a52174.boardgamemeetup.data

import com.google.android.gms.maps.model.LatLng

data class Place(
    val id: Int,
    val name: String,
    val location: LatLng
)