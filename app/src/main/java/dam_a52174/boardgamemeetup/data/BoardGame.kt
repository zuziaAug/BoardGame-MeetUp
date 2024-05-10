package dam_a52174.boardgamemeetup.data

data class BoardGame(
    val id: Int,
    val name: String,
    val maxPlayers: Int,
    val designer: String,
    val imageUrl: String
)