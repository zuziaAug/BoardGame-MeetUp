package dam_a52174.boardgamemeetup.data

import com.google.android.gms.maps.model.LatLng

object MockData {

    val BOARDGAMES_SIZE = 10
    val SESSIONS_SIZE = 10

    var games = listOf<BoardGame>(
        BoardGame(1, "Dune: Imperium", 4, "Paul Dennen", "https://cf.geekdo-images.com/UVUkjMV_Q2paVUIUP30Vvw__imagepagezoom/img/u7Pe8-CcrW792hF_WcGFEySolzI=/fit-in/1200x900/filters:no_upscale():strip_icc()/pic7664424.jpg"),
        BoardGame(2, "Splendor Duel", 2, "Bruno Cathala", "https://cf.geekdo-images.com/V1PyFDPNFY4bJFgreLPxmQ__imagepage/img/GnAJH3QrhGYdNvE_s5ZL5Lip3W8=/fit-in/900x600/filters:no_upscale():strip_icc()/pic6929347.jpg"),
        BoardGame(3, "7 Wonders Duel", 2, "Bruno Cathala", "https://cf.geekdo-images.com/zdagMskTF7wJBPjX74XsRw__imagepage/img/HdJ4d4O1P89V4UIhZnL3zoYnjow=/fit-in/900x600/filters:no_upscale():strip_icc()/pic2576399.jpg"),
        BoardGame(4, "Brass: Birmingham", 4, "Gavan Brown", "https://cf.geekdo-images.com/x3zxjr-Vw5iU4yDPg70Jgw__imagepage/img/-17KkOmxbTu2slJTabGrkO8ZW8s=/fit-in/900x600/filters:no_upscale():strip_icc()/pic3490053.jpg"),
        BoardGame(5, "Terraforming Mars", 5, "Jacob Fryxelius", "https://cf.geekdo-images.com/Vy-yZLxSS6ntOFjsDbho3g__imagepage/img/O3kmVQoV0KmoqotazMK9T--QxG8=/fit-in/900x600/filters:no_upscale():strip_icc()/pic3718275.jpg"),
        BoardGame(6, "Pandemic", 4, "Matt Leacock", "https://cf.geekdo-images.com/9jWKn7k1A3EXTdfbIOAy_w__imagepage/img/F6yAOZCItMTcF5F4Rlvk06Up73E=/fit-in/900x600/filters:no_upscale():strip_icc()/pic1534149.jpg"),
        BoardGame(7, "Catan", 4, "Klaus Teuber", "https://cf.geekdo-images.com/1VXThog8cSTOMx4yF9oElQ__imagepage/img/hGGcYxbpkrdbWa1uB9oF2l_kp3M=/fit-in/900x600/filters:no_upscale():strip_icc()/pic2582929.jpg"),
        BoardGame(8, "Wingspan", 5, "Elizabeth Hargrave", "https://cf.geekdo-images.com/sRLj12yHWhtJPSNp1Jb-1w__imagepage/img/0HD5akjBM41qzp6kPoHJ9TjoYoI=/fit-in/900x600/filters:no_upscale():strip_icc()/pic4458123.jpg"),
        BoardGame(9, "Azul", 4, "Michael Kiesling", "https://cf.geekdo-images.com/KOR6n4Q0UxTSDsPSKQeh1w__imagepage/img/HZKmzqT2E1TB3QtkBRpVIZcIt9M=/fit-in/900x600/filters:no_upscale():strip_icc()/pic3718274.jpg"),
        BoardGame(10, "Gloomhaven", 4, "Isaac Childres", "https://cf.geekdo-images.com/AC-mZrVdr5g4LCw-5_v57g__imagepage/img/Ri7t8VhGicqdNIZxH0o0Ok9v8hU=/fit-in/900x600/filters:no_upscale():strip_icc()/pic5963927.jpg")
    )

    var places = listOf<Place>(
        Place(1, "Pow Wow", LatLng(38.7623635686424, -9.16216713770185)),
        Place(2, "Defuse", LatLng(38.73237423089224, -9.14422852454052)),
        Place(3, "Gameplay", LatLng(38.75442176338318, -9.205533242371875)),
        Place(4, "Kult Games", LatLng(38.735669307565345, -9.14574019113742)),
        Place(5, "Games of the West ", LatLng(38.738882905151655, -9.144452730862696))
    )

    var players = listOf<Player>(
        Player(1, "Alice Smith", "alice.smith@example.com", "password1"),
        Player(2, "Bob Johnson", "bob.johnson@example.com", "password2"),
        Player(3, "Charlie Brown", "charlie.brown@example.com", "password3"),
        Player(4, "Dana White", "dana.white@example.com", "password4"),
        Player(5, "Ethan Green", "ethan.green@example.com", "password5")
    )

    var sessions = listOf<BoardGameSession>(
        BoardGameSession(1, games[0], listOf(players[0], players[1]), places[0]),
        BoardGameSession(2, games[1], listOf(players[1], players[2]), places[1]),
        BoardGameSession(3, games[2], listOf(players[2], players[3]), places[2]),
        BoardGameSession(4, games[3], listOf(players[0], players[3]), places[1]),
        BoardGameSession(5, games[4], listOf(players[4], players[0]), places[3])
    )
}
