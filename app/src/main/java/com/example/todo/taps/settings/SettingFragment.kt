package com.example.todo.taps.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import com.example.todo.databinding.FragmentSettingBinding
import java.util.Locale

class SettingFragment : Fragment() {
    lateinit var binding: FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
             {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0){
                    val localListCompat = LocaleListCompat.create(Locale("en"))
                    AppCompatDelegate.setApplicationLocales(localListCompat)
                }else if (position == 1){
                   val localListCompat = LocaleListCompat.create(Locale("ar"))
                   AppCompatDelegate.setApplicationLocales(localListCompat)}
            }

             override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        binding.modeSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }else if (position == 1){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)}

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }

}