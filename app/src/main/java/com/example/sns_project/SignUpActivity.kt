package com.example.sns_project

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_signup)

        auth = Firebase.auth

        val signUpButton = findViewById<Button>(R.id.signUpButton)
        signUpButton.setOnClickListener {
            val signUpNickname = findViewById<EditText>(R.id.signUpNickname).text.toString()
            val signUpEmail = findViewById<EditText>(R.id.signUpNickname).text.toString()
            val signUpBirth = findViewById<EditText>(R.id.signUpNickname).text.toString()
            val signUpPw = findViewById<EditText>(R.id.signUpNickname).text.toString()
            val signUpCheckPw = findViewById<EditText>(R.id.signUpNickname).text.toString()

            if(signUpNickname == null || signUpEmail == null || signUpBirth == null || signUpPw==null || signUpCheckPw==null){
                Toast.makeText(
                    baseContext, "Please Fill the all blanks",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else if(signUpPw != signUpCheckPw){
                Toast.makeText(
                    baseContext, "Password is not correct",
                    Toast.LENGTH_SHORT
                ).show()
            }
            // 중복된 회원가입이 아닌지 파이어베이스 스토리지로 검사
            
            else{
                createAccount(signUpEmail, signUpPw)
                startActivity(Intent(this, SignInActivity::class.java))
            }
        }
    }

        private fun createAccount(email: String, password : String) { //계정 생성 함수.
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext, "Success to Sign Up. Welcome!",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish() // 가입창 종료
                    } else {
                        Toast.makeText(
                            this, "Failed to Sign Up. Try Again!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}

