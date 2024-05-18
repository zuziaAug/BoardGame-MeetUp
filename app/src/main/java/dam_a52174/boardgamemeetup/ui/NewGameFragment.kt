package dam_a52174.boardgamemeetup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import dam_a52174.boardgamemeetup.R

class NewGameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextGameName = view.findViewById<EditText>(R.id.editTextGameName)
        val editTextMaxPlayers = view.findViewById<EditText>(R.id.editTextMaxPlayers)
        val editTextDesigner = view.findViewById<EditText>(R.id.editTextDesigner)
        val editTextImageUrl = view.findViewById<EditText>(R.id.editTextImageUrl)
        val buttonAddGame = view.findViewById<Button>(R.id.buttonAddGame)

        buttonAddGame.setOnClickListener {
            val gameName = editTextGameName.text.toString()
            val maxPlayers = editTextMaxPlayers.text.toString().toIntOrNull() ?: 0
            val designer = editTextDesigner.text.toString()
            val imageUrl = editTextImageUrl.text.toString()

            // Handle the logic to create a new game
            // For example, add the new game to the list and update the RecyclerView

            // If you need to close the fragment, use the fragment manager
            parentFragmentManager.popBackStack()
        }
    }
}
