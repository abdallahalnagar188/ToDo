package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.taps.AddTodoBottomSheetFragment
import com.example.todo.taps.SettingFragment
import com.example.todo.taps.TodosListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        showFragment(TodosListFragment())
        initViews()
    }

    private fun initViews() {
        viewBinding.bottomNavView.setOnItemSelectedListener {item->
            if (item.itemId == R.id.btn_list){
                showFragment(TodosListFragment())
            }else if (item.itemId == R.id.btn_settings){
                showFragment(SettingFragment())
            }
            return@setOnItemSelectedListener true
        }
        viewBinding.bottomNavView.selectedItemId = R.id.btn_list

        viewBinding.floatingBtnAddTodo.setOnClickListener {
            val bottomSheetFragment = AddTodoBottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager,"Add Todo")
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().
        replace(R.id.fragment_container, fragment)
            .commit()

    }
}