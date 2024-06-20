package dam_a52174.boardgamemeetup.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.data.BoardGame

class NewGameActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var editTextGameName: EditText
    private lateinit var editTextMaxPlayers: EditText
    private lateinit var editTextDesigner: EditText
    private lateinit var editTextImageUrl: EditText
    private lateinit var buttonAddGame: Button
    private var nextId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance()
        db.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()

        editTextGameName = findViewById(R.id.editTextGameName)
        editTextMaxPlayers = findViewById(R.id.editTextMaxPlayers)
        editTextDesigner = findViewById(R.id.editTextDesigner)
        editTextImageUrl = findViewById(R.id.editTextImageUrl)
        buttonAddGame = findViewById(R.id.buttonAddGame)

        // Get the next available ID for the new board game
        getNextId()

        buttonAddGame.setOnClickListener {
            val name = editTextGameName.text.toString()
            val maxPlayers = editTextMaxPlayers.text.toString().toIntOrNull() ?: 0
            val designer = editTextDesigner.text.toString()
            val imageUrl = editTextImageUrl.text.toString()

            if (name.isEmpty() || maxPlayers == 0 || designer.isEmpty() || imageUrl.isEmpty()) {
                Toast.makeText(this@NewGameActivity, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                val newBoardGame = BoardGame(nextId, name, maxPlayers, designer, imageUrl)
                addGameToDatabase(newBoardGame)
                startActivity(Intent(this, GamesActivity::class.java))
            }
        }
    }

    private fun getNextId() {
        db.collection("Games")
            .orderBy("id", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .limit(1)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val highestGame = documents.first().toObject(BoardGame::class.java)
                    nextId = highestGame?.id ?: 0 + 1
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this@NewGameActivity, "Error fetching highest ID: $exception", Toast.LENGTH_SHORT).show()
                Log.e("NewGameActivity", "Error fetching highest ID", exception)
            }
    }

    private fun addGameToDatabase(game: BoardGame) {
        db.collection("Games")
            .document(game.id.toString()) // Set the document ID explicitly
            .set(game)
            .addOnSuccessListener {
                Toast.makeText(this@NewGameActivity, "Game added successfully", Toast.LENGTH_SHORT).show()
                // Navigate back to GamesActivity
                startActivity(Intent(this@NewGameActivity, GamesActivity::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this@NewGameActivity, "Error adding game: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.e("NewGameActivity", "Error adding game", e)
            }
    }
}
