package dam_a52174.boardgamemeetup.data

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class DatabaseData : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance()

        FirebaseFirestore.setLoggingEnabled(true)
        FirebaseFirestore.getInstance().firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()

        val games = listOf(
            BoardGame(
                1,
                "Dune: Imperium",
                4,
                "Paul Dennen",
                "https://cf.geekdo-images.com/UVUkjMV_Q2paVUIUP30Vvw__imagepagezoom/img/u7Pe8-CcrW792hF_WcGFEySolzI=/fit-in/1200x900/filters:no_upscale():strip_icc()/pic7664424.jpg"
            ),
            BoardGame(
                2,
                "Splendor Duel",
                2,
                "Bruno Cathala",
                "https://cf.geekdo-images.com/V1PyFDPNFY4bJFgreLPxmQ__imagepage/img/GnAJH3QrhGYdNvE_s5ZL5Lip3W8=/fit-in/900x600/filters:no_upscale():strip_icc()/pic6929347.jpg"
            ),
            BoardGame(
                3,
                "7 Wonders Duel",
                2,
                "Bruno Cathala",
                "https://cf.geekdo-images.com/zdagMskTF7wJBPjX74XsRw__imagepage/img/HdJ4d4O1P89V4UIhZnL3zoYnjow=/fit-in/900x600/filters:no_upscale():strip_icc()/pic2576399.jpg"
            ),
            BoardGame(
                4,
                "Brass: Birmingham",
                4,
                "Gavan Brown",
                "https://cf.geekdo-images.com/x3zxjr-Vw5iU4yDPg70Jgw__imagepage/img/-17KkOmxbTu2slJTabGrkO8ZW8s=/fit-in/900x600/filters:no_upscale():strip_icc()/pic3490053.jpg"
            ),
            BoardGame(
                5,
                "Terraforming Mars",
                5,
                "Jacob Fryxelius",
                "https://cf.geekdo-images.com/wg9oOLcsKvDesSUdZQ4rxw__imagepage/img/FS1RE8Ue6nk1pNbPI3l-OSapQGc=/fit-in/900x600/filters:no_upscale():strip_icc()/pic3536616.jpg"
            ),
            BoardGame(
                6,
                "Pandemic",
                4,
                "Matt Leacock",
                "https://cf.geekdo-images.com/S3ybV1LAp-8SnHIXLLjVqA__imagepage/img/kIBu-2Ljb_ml5n-S8uIbE6ehGFc=/fit-in/900x600/filters:no_upscale():strip_icc()/pic1534148.jpg"
            ),
            BoardGame(
                7,
                "Catan",
                4,
                "Klaus Teuber",
                "https://cf.geekdo-images.com/W3Bsga_uLP9kO91gZ7H8yw__imagepage/img/M_3Vg1j2HlNgkv7PL2xl2BJE2bw=/fit-in/900x600/filters:no_upscale():strip_icc()/pic2419375.jpg"
            ),
            BoardGame(
                8,
                "Wingspan",
                5,
                "Elizabeth Hargrave",
                "https://cf.geekdo-images.com/yLZJCVLlIx4c7eJEWUNJ7w__imagepage/img/uIjeoKgHMcRtzRSR4MoUYl3nXxs=/fit-in/900x600/filters:no_upscale():strip_icc()/pic4458123.jpg"
            ),
            BoardGame(
                9,
                "Azul",
                4,
                "Michael Kiesling",
                "https://cf.geekdo-images.com/aPSHJO0d0XOpQR5X-wJonw__imagepage/img/q4uWd2nXGeEkKDR8Cc3NhXG9PEU=/fit-in/900x600/filters:no_upscale():strip_icc()/pic6973671.png"
            ),
            BoardGame(
                10,
                "Gloomhaven",
                4,
                "Isaac Childres",
                "https://cf.geekdo-images.com/sZYp_3BTDGjh2unaZfZmuA__imagepage/img/pBaOL7vV402nn1I5dHsdSKsFHqA=/fit-in/900x600/filters:no_upscale():strip_icc()/pic2437871.jpg"
            )
        )

        val places = listOf(
            Place(1, "Pow Wow", LatLng(38.7623635686424, -9.16216713770185)),
            Place(2, "Defuse", LatLng(38.73237423089224, -9.14422852454052)),
            Place(3, "Gameplay", LatLng(38.75442176338318, -9.205533242371875)),
            Place(4, "Kult Games", LatLng(38.735669307565345, -9.14574019113742)),
            Place(5, "Games of the West", LatLng(38.738882905151655, -9.144452730862696))
        )

        val sessions = listOf(
            BoardGameSession(1, games[0].name, places[0].name, "15/07/2024", 0, "Prepare yourself " +
                    "for an exhilarating evening of strategic conquests and political intrigue at our \"Diune: Imperium Strategy Night\" " +
                    "board game session! Whether you're a seasoned diplomat or a novice tactician, this event promises " +
                    "thrilling gameplay and friendly competition. Dive into the world of Arrakis!"),
            BoardGameSession(2, games[1].name, places[1].name, "25/07/2024", 10, "Play session for ${games[1].name} at ${places[1].name}. Don't forget to sign up!"),
            BoardGameSession(3, games[2].name, places[2].name, "23/09/2024", 5, "Exciting game session featuring ${games[2].name} at ${places[2].name}. Don't miss out!"),
            BoardGameSession(4, games[3].name, places[1].name, "15/08/2024", 25, "Tournament of ${games[3].name} at ${places[1].name}. Deadline for applying: 1/08!"),
            BoardGameSession(5, games[4].name, places[3].name, "15/10/2024", 0, "Another free play session for ${games[4].name} at ${places[3].name}. Everyone is welcome!")
        )

        // Populate the database
        populateDatabaseGames(games)
        populateDatabasePlaces(places)
        populateDatabaseSessions(sessions)
        finish()
    }

    private fun populateDatabaseGames(games: List<BoardGame>) {
        for (game in games) {
            db.collection("Games")
                .document(game.id.toString())  // Use the game's ID as the document name
                .set(game)
                .addOnSuccessListener {
                    println("DocumentSnapshot successfully written!")
                }
                .addOnFailureListener { e ->
                    println("Error writing document: $e")
                }
        }
    }

    private fun populateDatabasePlaces(places: List<Place>) {
        for (place in places) {
            db.collection("Places")
                .document(place.id.toString())
                .set(place)
                .addOnSuccessListener {
                    println("DocumentSnapshot successfully written!")
                }
                .addOnFailureListener { e ->
                    println("Error writing document: $e")
                }
        }
    }

    private fun populateDatabaseSessions(sessions: List<BoardGameSession>) {
        for (session in sessions) {
            db.collection("BoardGameSessions")
                .document(session.id.toString())
                .set(session)
                .addOnSuccessListener {
                    println("DocumentSnapshot successfully written!")
                }
                .addOnFailureListener { e ->
                    println("Error writing document: $e")
                }
        }
    }
}
