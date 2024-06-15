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
import dam_a52174.boardgamemeetup.data.BoardGameSession

class NewSessionActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var editTextGameName: EditText
    private lateinit var editTextPlace: EditText
    private lateinit var buttonCreateSession: Button
    private var nextId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_session)

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance()
        db.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()

        editTextGameName = findViewById(R.id.editTextGameName)
        editTextPlace = findViewById(R.id.editTextPlace)
        buttonCreateSession = findViewById(R.id.buttonCreateSession)

        // Get the next available ID for the new board game session
        getNextId()

        buttonCreateSession.setOnClickListener {
            val name = editTextGameName.text.toString()
            val place = editTextPlace.text.toString()

            if (name.isEmpty() || place.isEmpty()) {
                Toast.makeText(this@NewSessionActivity, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                val newBoardGameSession = BoardGameSession(nextId, name, place)
                addSessionToDatabase(newBoardGameSession)
            }
        }
    }

    private fun getNextId() {
        db.collection("BoardGameSessions")
            .orderBy("id", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .limit(1)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val highestSession = documents.first().toObject(BoardGameSession::class.java)
                    nextId = highestSession?.id ?: 0 + 1
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this@NewSessionActivity, "Error fetching highest ID: $exception", Toast.LENGTH_SHORT).show()
                Log.e("NewSessionActivity", "Error fetching highest ID", exception)
            }
    }

    private fun addSessionToDatabase(session: BoardGameSession) {
        // Add the session to Firestore with a custom document ID (the same as the session ID)
        db.collection("BoardGameSessions")
            .document(session.id.toString()) // Set the document ID explicitly
            .set(session)
            .addOnSuccessListener {
                Toast.makeText(this@NewSessionActivity, "Session added successfully", Toast.LENGTH_SHORT).show()
                // Navigate back to SessionsActivity
                startActivity(Intent(this@NewSessionActivity, SessionsActivity::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this@NewSessionActivity, "Error adding session: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.e("NewSessionActivity", "Error adding session", e)
            }
    }
}
