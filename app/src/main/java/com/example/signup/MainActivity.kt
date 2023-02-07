package com.example.signup

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet


class MainActivity : AppCompatActivity() {
    private val sharedPrefFile = "SharedPreferenceFile"
    var person:Person = Person()
    fun passwordToggle(){
        var visibility: Boolean = false
        val passBtn = findViewById<ImageButton>(R.id.password_visibility)
        val password = findViewById<EditText>(R.id.password_editText)
        passBtn.setOnClickListener {
            if(visibility == false) {
                passBtn.setBackgroundResource(R.drawable.ic_baseline_visibility_off_24)
                password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                visibility = true
            }else{
                passBtn.setBackgroundResource(R.drawable.ic_baseline_visibility_24)
                password.transformationMethod = PasswordTransformationMethod.getInstance()
                visibility = false
            }
        }
    }

    fun signUp(signUpPrefs: SignUpPrefs, editTextIds: Map<String, EditText>, buttonIds: Map<String, Button>){
        var viewsExist = false
        val layout = findViewById<View>(R.id.constraint_lay) as ConstraintLayout
        val nameTxtView = TextView(this)
        nameTxtView.setId(View.generateViewId())
        val emailTxtView = TextView(this)
        emailTxtView.setId(View.generateViewId())
        val passwordTxtView = TextView(this)
        passwordTxtView.setId(View.generateViewId())
        buttonIds["signUp"]?.setOnClickListener {

            person.name = editTextIds["name"]?.text.toString()
            person.email = editTextIds["email"]?.text.toString()
            person.password = editTextIds["password"]?.text.toString()
            person.phone = editTextIds["phone"]?.text.toString()
            signUpPrefs.setSignUpPreferences(signUpPrefs.getSharedPrefObj(),person)
            if(!viewsExist){
                nameTxtView.layoutParams= ConstraintLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                nameTxtView.gravity = Gravity.CENTER_HORIZONTAL
                nameTxtView.setText(person?.name)
                nameTxtView.setTextSize(20F)
                nameTxtView.setTextColor(Color.WHITE)
                layout.addView(nameTxtView)
                val nameConstraintSet = ConstraintSet()
                nameConstraintSet.clone(layout)
                nameConstraintSet.connect(nameTxtView.id,ConstraintSet.TOP,R.id.username_editText,ConstraintSet.BOTTOM,20)
                nameConstraintSet.connect(nameTxtView.id,ConstraintSet.START,layout.id,ConstraintSet.START)
                nameConstraintSet.connect(nameTxtView.id,ConstraintSet.END,layout.id,ConstraintSet.END)
                nameConstraintSet.applyTo(layout)


                emailTxtView.layoutParams= ConstraintLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                emailTxtView.gravity = Gravity.CENTER_HORIZONTAL
                emailTxtView.setText(person?.email)
                emailTxtView.setTextSize(20F)
                emailTxtView.setTextColor(Color.WHITE)
                layout.addView(emailTxtView)
                val emailConstraintSet = ConstraintSet()
                emailConstraintSet.clone(layout)
                emailConstraintSet.connect(emailTxtView.id,ConstraintSet.TOP,R.id.email_editText,ConstraintSet.BOTTOM,20)
                emailConstraintSet.connect(emailTxtView.id,ConstraintSet.START,layout.id,ConstraintSet.START)
                emailConstraintSet.connect(nameTxtView.id,ConstraintSet.END,layout.id,ConstraintSet.END)
                emailConstraintSet.applyTo(layout)

                viewsExist = true
            }else{
                layout.removeView(nameTxtView)
                layout.removeView(emailTxtView)
                viewsExist = false
            }
        }

    }
    fun displaySigUpPref(person: Person, editTextIds:Map<String,EditText>){
        editTextIds["name"]?.setText(person.name)
        editTextIds["email"]?.setText(person.email)
        editTextIds["password"]?.setText(person.password)
        editTextIds["phone"]?.setText(person.phone)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        passwordToggle()
        val name = findViewById<EditText>(R.id.username_editText)
        val email = findViewById<EditText>(R.id.email_editText)
        val password = findViewById<EditText>(R.id.password_editText)
        val phone = findViewById<EditText>(R.id.phone_editText)
        val signUp = findViewById<Button>(R.id.sign_up_btn)
        val editTextIds: Map<String,EditText> = mapOf(
            "name" to name,
            "email" to email,
            "password" to password,
            "phone" to phone)
        val buttonIds: Map<String,Button> = mapOf("signUp" to signUp)
        //create signUpPreference Object
        val signUpPrefs:SignUpPrefs = SignUpPrefs(sharedPrefFile, applicationContext)
        //Fill signup data in editFields
        displaySigUpPref(signUpPrefs.getSignUpPreferences(),editTextIds)
        //signUp include setting preferences
        signUp(signUpPrefs,editTextIds,buttonIds)

    }

//    //TO START A NEW ACTIVITY
//    fun signUpStartAcctivity(){
//        val signUpBtn = findViewById<Button>(R.id.sign_up_btn)
//        signUpBtn.setOnClickListener {
//
//        }
//    }

}