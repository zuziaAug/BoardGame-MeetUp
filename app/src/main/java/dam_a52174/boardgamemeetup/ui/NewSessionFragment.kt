package dam_a52174.boardgamemeetup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.data.BoardGameSession

class NewSessionFragment : Fragment() {

    private lateinit var db: FirebaseFirestore
    private lateinit var editTextGameName: EditText
    private lateinit var editTextPlace: EditText
    private lateinit var buttonAddSession: Button
    private var nextId: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_session, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = FirebaseFirestore.getInstance()

        editTextGameName = view.findViewById(R.id.editTextGameName)
        editTextPlace = view.findViewById(R.id.editTextPlace)
        buttonAddSession = view.findViewById(R.id.buttonAddSession)

        // Get the next available ID for the new board game session
        getNextId()

        buttonAddSession.setOnClickListener {
            val name = editTextGameName.text.toString()
            val place = editTextPlace.text.toString()

            if (name.isEmpty() || place.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
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
                    nextId = highestSession.id + 1
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(), "Error fetching highest ID: $exception", Toast.LENGTH_SHORT).show()
            }
    }

    private fun addSessionToDatabase(session: BoardGameSession) {
        // Add the session to Firestore with a custom document ID (the same as the session ID)
        db.collection("BoardGameSessions")
            .document(session.id.toString()) // Set the document ID explicitly
            .set(session)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Session added successfully", Toast.LENGTH_SHORT).show()
                // Optionally, navigate back or clear fields after adding the session
                clearFields()
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Error adding session: $e", Toast.LENGTH_SHORT).show()
            }
    }

    private fun clearFields() {
        editTextGameName.text.clear()
        editTextPlace.text.clear()
    }
}
