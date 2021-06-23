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

/**
 * <br>createBy Gw
 * <br>createTime: 2021/6/21 15:04
 */
class MainActivity : AppCompatActivity() {

    companion object {
        private const val usernameKey = "username"
        private const val passwordKey = "password"
    }
    var username:String
    set(value) = CacheUtils.save(usernameKey, value)
    get() = CacheUtils.get(usernameKey)

    var password:String
    set(value) = CacheUtils.save(passwordKey, value)
    get() = CacheUtils.get(passwordKey)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_username.setText(username)
        et_password.setText(password)

        btn_login.setOnClickListener { login() }
        code_view.setOnClickListener { code_view.updateCode() }
    }

    private fun login() {
        val username:String = et_username.text.toString()
        val password:String = et_password.text.toString()
        val code:String = et_code.text.toString()

        val user = User(username, password, code)
        if (verify(user)) {
            this.username = username
            this.password = password
            startActivity(Intent(this, LessonActivity::class.java))
        }
    }

    private fun verify(user:User):Boolean {
        if(user.username?.length?:0 < 4){
            toast("用户名不合法")
            return false
        }

        if(user.password?.length?:0 < 4){
            toast("密码不合法")
            return false
        }

        return true
    }
}