package dam_a52174.boardgamemeetup.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import dam_a52174.boardgamemeetup.R

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        // Animation + delay last for 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            animation()
        }, 1000)

        // after 3,2 seconds start MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            startMainActivity()
        }, 3200)
    }

    private fun animation() {
        val animationView = findViewById<ImageView>(R.id.start_animation)
        animationView.visibility = View.VISIBLE

        val spinAnimator = ObjectAnimator.ofFloat(animationView, "rotation", 0f, 360f).apply {
            duration = 2000 // 2 seconds for one rotation
            repeatCount = 1
            interpolator = AccelerateInterpolator()
        }

        val scaleAnimatorX = ObjectAnimator.ofFloat(animationView, "scaleX", 1f, 0f).apply {
            duration = 2000
            interpolator = AccelerateInterpolator()
        }

        val scaleAnimatorY = ObjectAnimator.ofFloat(animationView, "scaleY", 1f, 0f).apply {
            duration = 2000
            interpolator = AccelerateInterpolator()
        }

        spinAnimator.start()
        scaleAnimatorX.start()
        scaleAnimatorY.start()
    }

    private fun startMainActivity() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish() // Close StartActivity
    }
}