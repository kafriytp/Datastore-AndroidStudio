package com.eunidev.datastoreytp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.asLiveData
import com.eunidev.datastoreytp.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contactDatastore: ContactDatastore

    private var name = ""
    private var phoneNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contactDatastore = ContactDatastore(this)

        observeData()

        binding.buttonSaveMainActivity.setOnClickListener {
            if (binding.editTextNameMainActivity.text.isNullOrEmpty() or binding.editTextNumberMainActivity.text.isNullOrEmpty()) {
                Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show()
            } else {
                val sName = binding.editTextNameMainActivity.text.toString()
                val sPhoneNumber = binding.editTextNumberMainActivity.text.toString().toInt()

                GlobalScope.launch {
                    contactDatastore.setName(sName)
                    contactDatastore.setPhoneNumber(sPhoneNumber)
                }

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            }
        }

    }

    fun observeData() {
        contactDatastore.getName.asLiveData().observe(this, {
            name = it
            binding.editTextNameMainActivity.setText(name, TextView.BufferType.EDITABLE)
        })

        contactDatastore.getPhoneNumber.asLiveData().observe(this, {
            phoneNumber = it
            binding.editTextNumberMainActivity.setText(phoneNumber.toString(), TextView.BufferType.EDITABLE)
        })
    }
}