package dam_a52174.boardgamemeetup.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.data.BoardGame

class NewGameFragment : Fragment() {

    private lateinit var db: FirebaseFirestore
    private lateinit var editTextGameName: EditText
    private lateinit var editTextMaxPlayers: EditText
    private lateinit var editTextDesigner: EditText
    private lateinit var editTextImageUrl: EditText
    private lateinit var buttonAddGame: Button
    private var nextId: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = FirebaseFirestore.getInstance()
        db.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()

        editTextGameName = view.findViewById(R.id.editTextGameName)
        editTextMaxPlayers = view.findViewById(R.id.editTextMaxPlayers)
        editTextDesigner = view.findViewById(R.id.editTextDesigner)
        editTextImageUrl = view.findViewById(R.id.editTextImageUrl)
        buttonAddGame = view.findViewById(R.id.buttonAddGame)

        // Get the next available ID for the new board game
        getNextId()

        buttonAddGame.setOnClickListener {
            val name = editTextGameName.text.toString()
            val maxPlayers = editTextMaxPlayers.text.toString().toIntOrNull() ?: 0
            val designer = editTextDesigner.text.toString()
            val imageUrl = editTextImageUrl.text.toString()

            if (name.isEmpty() || maxPlayers == 0 || designer.isEmpty() || imageUrl.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                val newBoardGame = BoardGame(nextId, name, maxPlayers, designer, imageUrl)
                addGameToDatabase(newBoardGame)
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
                    nextId = highestGame.id + 1
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(), "Error fetching highest ID: $exception", Toast.LENGTH_SHORT).show()
                Log.e("NewGameFragment", "Error fetching highest ID", exception)
            }
    }

    private fun addGameToDatabase(game: BoardGame) {
        db.collection("Games")
            .document(game.id.toString()) // Set the document ID explicitly
            .set(game)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Game added successfully", Toast.LENGTH_SHORT).show()
                // Navigate back to GamesActivity
                startActivity(Intent(requireContext(), GamesActivity::class.java))
                requireActivity().finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Error adding game: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.e("NewGameFragment", "Error adding game", e)
            }
    }
}
