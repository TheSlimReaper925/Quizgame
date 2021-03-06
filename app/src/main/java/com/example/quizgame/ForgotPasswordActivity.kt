package com.example.quizgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        auth = FirebaseAuth.getInstance()

        sendBtn.setOnClickListener {
            val email: String = emailInput.text.toString()

            if (TextUtils.isEmpty(email)) {

                Toast.makeText(this, "გთხოვთ ჩაწერეთ თქვენი იმეილი!", Toast.LENGTH_LONG).show()

            } else {

                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "ლინკი შეცვლისთვის წარმატებით გაიგზავნა", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            Toast.makeText(this, "იმეილზე ლინკის გაგზავნა ვერ მოხერხდა", Toast.LENGTH_LONG)
                                .show()
                        }
                    })
            }
        }
    }
}