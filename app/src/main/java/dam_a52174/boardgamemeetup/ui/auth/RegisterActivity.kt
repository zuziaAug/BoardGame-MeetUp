package dam_a52174.boardgamemeetup.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import dam_a52174.boardgamemeetup.R
import dam_a52174.boardgamemeetup.ui.MainActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var nameField: EditText
    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Find views
        nameField = findViewById(R.id.nameField)
        emailField = findViewById(R.id.emailField)
        passwordField = findViewById(R.id.passwordField)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val textLogin = findViewById<View>(R.id.textLoginHere)
        val textGoBack = findViewById<TextView>(R.id.textGoBack)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Set click listener for register button
        registerButton.setOnClickListener {
            createUser()
        }

        // Set click listener for textLogin
        textLogin.setOnClickListener {
            // Navigate to LoginActivity when "Login here" text is clicked
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // Finish RegisterActivity to prevent going back to it
        }

        // Set click listener for "Go back" text
        textGoBack.setOnClickListener {
            // Navigate to RegisterActivity when "Go back" text is clicked
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun createUser() {
        val name = nameField.text.toString().trim()
        val email = emailField.text.toString().trim()
        val password = passwordField.text.toString().trim()

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(applicationContext, "Please enter your name", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Please enter email address", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "Please enter password", Toast.LENGTH_SHORT).show()
            return
        }

        // Create user with email and password
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    Toast.makeText(this@RegisterActivity, "Registration successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.e("RegisterActivity", "Registration failed", task.exception)
                    Toast.makeText(baseContext, "Registration failed. ${task.exception?.message}",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}
