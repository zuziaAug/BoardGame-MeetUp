package dam_a52174.boardgamemeetup.auth

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dam_a52174.boardgamemeetup.data.Player

class UserRepository {
    private val auth: FirebaseAuth = Firebase.auth

    fun createPlayer(email: String, password: String, name: String, callback: (Player?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        val player = Player(uid = it.uid, name = name, email = it.email ?: "")
                        callback(player)
                    } ?: callback(null)
                } else {
                    callback(null)
                }
            }
    }

    fun loginPlayer(email: String, password: String, callback: (Player?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        val player = Player(uid = it.uid, name = it.displayName ?: "", email = it.email ?: "")
                        callback(player)
                    } ?: callback(null)
                } else {
                    callback(null)
                }
            }
    }

    fun getCurrentPlayer(callback: (Player?) -> Unit) {
        val user = auth.currentUser
        user?.let {
            val player = Player(uid = it.uid, name = it.displayName ?: "", email = it.email ?: "")
            callback(player)
        } ?: callback(null)
    }
}
