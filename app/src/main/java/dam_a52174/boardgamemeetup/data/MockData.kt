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
        BoardGame(5, "Terraforming Mars", 5, "Jacob Fryxelius", "https://cf.geekdo-images.com/wg9oOLcsKvDesSUdZQ4rxw__imagepage/img/FS1RE8Ue6nk1pNbPI3l-OSapQGc=/fit-in/900x600/filters:no_upscale():strip_icc()/pic3536616.jpg"),
        BoardGame(6, "Pandemic", 4, "Matt Leacock", "https://cf.geekdo-images.com/S3ybV1LAp-8SnHIXLLjVqA__imagepage/img/kIBu-2Ljb_ml5n-S8uIbE6ehGFc=/fit-in/900x600/filters:no_upscale():strip_icc()/pic1534148.jpg"),
        BoardGame(7, "Catan", 4, "Klaus Teuber", "https://cf.geekdo-images.com/W3Bsga_uLP9kO91gZ7H8yw__imagepage/img/M_3Vg1j2HlNgkv7PL2xl2BJE2bw=/fit-in/900x600/filters:no_upscale():strip_icc()/pic2419375.jpg"),
        BoardGame(8, "Wingspan", 5, "Elizabeth Hargrave", "https://cf.geekdo-images.com/yLZJCVLlIx4c7eJEWUNJ7w__imagepage/img/uIjeoKgHMcRtzRSR4MoUYl3nXxs=/fit-in/900x600/filters:no_upscale():strip_icc()/pic4458123.jpg"),
        BoardGame(9, "Azul", 4, "Michael Kiesling", "https://cf.geekdo-images.com/aPSHJO0d0XOpQR5X-wJonw__imagepage/img/q4uWd2nXGeEkKDR8Cc3NhXG9PEU=/fit-in/900x600/filters:no_upscale():strip_icc()/pic6973671.png"),
        BoardGame(10, "Gloomhaven", 4, "Isaac Childres", "https://cf.geekdo-images.com/sZYp_3BTDGjh2unaZfZmuA__imagepage/img/pBaOL7vV402nn1I5dHsdSKsFHqA=/fit-in/900x600/filters:no_upscale():strip_icc()/pic2437871.jpg")
    )

    var places = listOf<Place>(
        Place(1, "Pow Wow", LatLng(38.7623635686424, -9.16216713770185)),
        Place(2, "Defuse", LatLng(38.73237423089224, -9.14422852454052)),
        Place(3, "Gameplay", LatLng(38.75442176338318, -9.205533242371875)),
        Place(4, "Kult Games", LatLng(38.735669307565345, -9.14574019113742)),
        Place(5, "Games of the West ", LatLng(38.738882905151655, -9.144452730862696))
    )

    var players = listOf<Player>(
        Player("cGJOLJxHMoOhQMslu0MMKMA3zez2", "Alice Smith", "alice.smith@example.com"),
        Player("opLOefrnXOZfeKo0IZzxot1N7EG2", "Bob Johnson", "bob.johnson@example.com"),
        Player("pdXuK7su8JM8P1kk10NRbh9YBpD5", "Charlie Brown", "charlie.brown@example.com"),
        Player("okLOefrnXOZfeKo0IZzxot1N7EG1", "Dana White", "dana.white@example.com"),
        Player("dGJOLJxHMoOhQMslu0MMKMA3zez7", "Ethan Green", "ethan.green@example.com")
    )

    var sessions = listOf<BoardGameSession>(
        BoardGameSession(1, games[0], listOf(players[0], players[1]), places[0]),
        BoardGameSession(2, games[1], listOf(players[1], players[2]), places[1]),
        BoardGameSession(3, games[2], listOf(players[2], players[3]), places[2]),
        BoardGameSession(4, games[3], listOf(players[0], players[3]), places[1]),
        BoardGameSession(5, games[4], listOf(players[4], players[0]), places[3])
    )
}
