package com.example.mvvvm.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.mvvvm.R
import com.example.mvvvm.databinding.MainActDataBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var binding: MainActDataBinding? = null

    val counterForTrigger = MutableLiveData<Int>(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.lifecycleOwner = this

        counterForTrigger.observe(this) {

            binding?.textView?.text = it.toString()
        }


        buttonClicks()

        binding?.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        counterForTrigger.value?.toLong()?.let { outState.putLong("param", it) };

        Log.e("COUNTER" , "end")

    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        counterForTrigger.value = savedInstanceState?.getLong("param")?.toInt()
    }

    private fun buttonClicks() {
        binding?.button?.setOnClickListener {
            counterForTrigger.value = counterForTrigger.value?.plus(1)

            Log.e("COUNTER" , counterForTrigger.value.toString())

        }
    }

}