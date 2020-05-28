package com.example.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.app.entity.User
import com.example.app.widget.CodeView
import com.example.core.utils.CacheUtils
import com.example.core.utils.toast
import com.example.lesson.LessonActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    val usernameKey = "username"
    val passwordKey = "password"

    lateinit var et_username: EditText
    lateinit var et_password: EditText
    lateinit var et_code: EditText

    override fun onClick(v: View?) {
        when(v) {
            is CodeView -> code_view.updateCode()
            is Button -> login()
        }
    }

    private fun login() {
        val username = et_username.text.toString()
        val password = et_password.text.toString()
        val code = et_code.text.toString()

        val user = User(username, password, code)
        if (verify(user)) {
            CacheUtils.save(usernameKey, username)
            CacheUtils.save(passwordKey, password)
            startActivity(Intent(this, LessonActivity::class.java))
        }
    }

    private fun verify(user: User): Boolean {
        if (user.username?.length ?: 0 < 4) {
            toast("用户名不合法")
            return false
        }

        if(user.password?.length ?: 0 < 4) {
            toast("密码不合法")
            return false
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        et_code = findViewById(R.id.et_code)

        et_username.setText(CacheUtils.get(usernameKey))
        et_password.setText(CacheUtils.get(passwordKey))

        val btn_login: Button = findViewById(R.id.btn_login)
        val img_code: CodeView = findViewById(R.id.code_view)
        btn_login.setOnClickListener(this)
        img_code.setOnClickListener(this)
    }



}