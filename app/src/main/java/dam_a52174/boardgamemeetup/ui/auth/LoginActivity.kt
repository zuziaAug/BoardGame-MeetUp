package dam_a52174.boardgamemeetup.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.ui.HomeActivity
import dam_a52174.boardgamemeetup.ui.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Find views
        emailField = findViewById(R.id.emailField)
        passwordField = findViewById(R.id.passwordField)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val textRegisterHere = findViewById<TextView>(R.id.textRegisterHere)
        val textGoBack = findViewById<TextView>(R.id.textGoBack)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Set click listener for login button
        loginButton.setOnClickListener {
            loginUser()
        }

        // Set click listener for "Register here" text
        textRegisterHere.setOnClickListener {
            // Navigate to RegisterActivity when "Register here" text is clicked
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Set click listener for "Go back" text
        textGoBack.setOnClickListener {
            // Navigate to RegisterActivity when "Go back" text is clicked
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun loginUser() {
        val email = emailField.text.toString().trim()
        val password = passwordField.text.toString().trim()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Please enter email address", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "Please enter password", Toast.LENGTH_SHORT).show()
            return
        }

        // Sign in user with email and password
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this@LoginActivity, "Login successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@LoginActivity, "Login failed. ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
