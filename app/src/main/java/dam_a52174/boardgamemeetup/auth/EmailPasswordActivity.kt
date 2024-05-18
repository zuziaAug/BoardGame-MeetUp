package dam_a52174.boardgamemeetup.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.data.Player
import dam_a52174.boardgamemeetup.ui.HomeActivity

class EmailPasswordActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository
    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText
    private lateinit var nameField: EditText
    private lateinit var signInButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_email_password)

        userRepository = UserRepository()

        emailField = findViewById(R.id.emailField)
        passwordField = findViewById(R.id.passwordField)
        nameField = findViewById(R.id.nameField)
        signInButton = findViewById(R.id.signInButton)
        registerButton = findViewById(R.id.registerButton)

        signInButton.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.text.toString()
            signIn(email, password)
        }

        registerButton.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.text.toString()
            val name = nameField.text.toString()
            createAccount(email, password, name)
        }
    }

    public override fun onStart() {
        super.onStart()
        userRepository.getCurrentPlayer { player ->
            if (player != null) {
                // Navigate to HomeActivity
                navigateToHome()
            }
        }
    }

    private fun createAccount(email: String, password: String, name: String) {
        userRepository.createPlayer(email, password, name) { player ->
            if (player != null) {
                Log.d(TAG, "createUserWithEmail:success")
                // Navigate to HomeActivity
                navigateToHome()
            } else {
                Log.w(TAG, "createUserWithEmail:failure")
                Toast.makeText(
                    baseContext,
                    "Authentication failed.",
                    Toast.LENGTH_SHORT,
                ).show()
                updateUI(null)
            }
        }
    }

    private fun signIn(email: String, password: String) {
        userRepository.loginPlayer(email, password) { player ->
            if (player != null) {
                Log.d(TAG, "signInWithEmail:success")
                // Navigate to HomeActivity
                navigateToHome()
            } else {
                Log.w(TAG, "signInWithEmail:failure")
                Toast.makeText(
                    baseContext,
                    "Authentication failed.",
                    Toast.LENGTH_SHORT,
                ).show()
                updateUI(null)
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun updateUI(player: Player?) {
        if (player != null) {
            // Update UI with player information
            // Example: textViewUserName.text = player.name
            // Example: textViewUserEmail.text = player.email
            // Hide nameField since it's only for registration
            nameField.visibility = View.GONE
        } else {
            // Clear or reset UI elements for a signed-out user
            nameField.visibility = View.VISIBLE
        }
    }

    private fun reload() {
        userRepository.getCurrentPlayer { player ->
            if (player != null) {
                updateUI(player)
            }
        }
    }

    companion object {
        private const val TAG = "EmailPasswordActivity"
    }
}
