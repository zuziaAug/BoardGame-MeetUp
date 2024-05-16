package dam_a52174.boardgamemeetup.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.data.BoardGameSession

class SessionAdapter(
    private val pkSessionList: List<BoardGameSession>,
    private val context: Context
) : RecyclerView.Adapter<SessionAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sessionGameNameTextView = itemView.findViewById<AppCompatTextView>(R.id.sessionGameNameTextView)
        val sessionPlaceTextView = itemView.findViewById<AppCompatTextView>(R.id.sessionPlaceTextView)
        val sessionPlacesLeftTextView = itemView.findViewById<AppCompatTextView>(R.id.sessionPlacesLeftTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_session, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val session = pkSessionList[position]
        holder.sessionGameNameTextView.text = session.game.name
        holder.sessionPlaceTextView.text = "Place: " + session.place.name
        holder.sessionPlacesLeftTextView.text = "Places left: " + (session.game.maxPlayers - session.players.size).toString()
        //TO DO - places left
    }

    override fun getItemCount(): Int {
        return pkSessionList.size
    }
}