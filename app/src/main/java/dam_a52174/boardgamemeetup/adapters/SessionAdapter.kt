package dam_a52174.boardgamemeetup.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.data.BoardGameSession
import dam_a52174.boardgamemeetup.ui.SessionDetailActivity

class SessionAdapter(
    private val pkSessionList: List<BoardGameSession>,
    private val context: Context
) : RecyclerView.Adapter<SessionAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sessionGameNameTextView = itemView.findViewById<AppCompatTextView>(R.id.sessionGameNameTextView)
        val sessionPlaceTextView = itemView.findViewById<AppCompatTextView>(R.id.sessionPlaceTextView)
        val buttonSessionDetail = itemView.findViewById<ImageButton>(R.id.buttonSessionDetail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_session, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val session = pkSessionList[position]
        holder.sessionGameNameTextView.text = session.gameName
        holder.sessionPlaceTextView.text = "Place: " + session.placeName

        holder.buttonSessionDetail.setOnClickListener {
            // Navigate to SessionDetailActivity with session ID
            val intent = Intent(context, SessionDetailActivity::class.java).apply {
                putExtra("SESSION_ID", session.id)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return pkSessionList.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: BoardGameSession)
    }
}