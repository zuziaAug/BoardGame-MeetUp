package dam_a52174.boardgamemeetup.data

data class BoardGameSession(
    val id: Int,
    val game: BoardGame,
    val players: List<Player>,
    val place: Place
)