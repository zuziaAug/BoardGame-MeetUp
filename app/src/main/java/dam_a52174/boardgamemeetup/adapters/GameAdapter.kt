package dam_a52174.boardgamemeetup.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.data.BoardGame

class GameAdapter(
    private val pkGameList: List<BoardGame>,
    private val context: Context
) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTest = itemView.findViewById<AppCompatTextView>(R.id.idTest)
        val startersImageView = itemView.findViewById<AppCompatImageView>(R.id.gameImageView)
        val gameNameTextView = itemView.findViewById<AppCompatTextView>(R.id.gameNameTextView)
        val gameMaxPlayersTextView = itemView.findViewById<AppCompatTextView>(R.id.gameMaxPlayersTextView)
        val gameDesignerTextView = itemView.findViewById<AppCompatTextView>(R.id.gameDesignerTextView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_game, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = pkGameList[position]

        Glide.with(holder.itemView.context)
            .load(game.imageUrl)
            .error(R.drawable.warning)
            .into(holder.startersImageView)

        holder.gameNameTextView.text = game.name
        holder.gameMaxPlayersTextView.text = "Max players: " + game.maxPlayers.toString()
        holder.gameDesignerTextView.text = "Designer: " + game.designer
        holder.idTest.text = "ID: " + game.id
    }

    override fun getItemCount(): Int {
        return pkGameList.size
    }
}