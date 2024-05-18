package dam_a52174.boardgamemeetup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import dam_a52174.boardgamemeetup.R

class NewSessionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_session, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextGameName = view.findViewById<EditText>(R.id.editTextGameName)
        val editTextPlace = view.findViewById<EditText>(R.id.editTextPlace)
        val buttonCreateSession = view.findViewById<Button>(R.id.buttonCreateSession)

        buttonCreateSession.setOnClickListener {
            val gameName = editTextGameName.text.toString()
            val placeName = editTextPlace.text.toString()

            // Handle the logic to create a new session
            // For example, add the new session to the list and update the RecyclerView

            // If you need to close the fragment, use the fragment manager
            parentFragmentManager.popBackStack()
        }
    }
}
