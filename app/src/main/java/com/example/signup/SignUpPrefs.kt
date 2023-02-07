package com.example.signup

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.widget.EditText
class SignUpPrefs(filename:String,context: Context) {

    private val signUpFileName: String = filename
    private val signPrefs : SharedPreferences = context.getSharedPreferences(signUpFileName, Context.MODE_PRIVATE)
    fun getSharedPrefObj():SharedPreferences{
        return signPrefs
    }
    fun getSignUpPreferences( ):Person {

        return Person(signPrefs.getString("name",null),
            signPrefs.getString("email",null),
            signPrefs.getString("password",null),
            signPrefs.getString("phone",null))
//        val name = signPrefs.getString("name",null)
//        val email = signPrefs.getString("email",null)
//        val password = signPrefs.getString("password",null)
//        val phone = signPrefs.getString("phone",null)
//
//        val person:Person = Person(name,email,password,phone)
//        return person
    }
    fun setSignUpPreferences(signUpPrefs: SharedPreferences, person: Person){

        with(signUpPrefs.edit()){
            putString("name", person.name)
            putString("email", person.email)
            putString("password", person.password)
            putString("phone", person.phone)
            apply()
        }
    }


}