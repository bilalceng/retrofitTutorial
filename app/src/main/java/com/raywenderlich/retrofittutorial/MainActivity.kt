package com.raywenderlich.retrofittutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.raywenderlich.retrofittutorial.databinding.ActivityMainBinding
import okio.IOException
import retrofit2.HttpException

const val TAG = "yardannnk"

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdaptor: TodoAdaptor
    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        setAdaptorToRecyclerView()

        lifecycleScope.launchWhenCreated {
            Log.d(TAG ,"IoException, you might not have internet connection ${Thread.currentThread().name}")
          binding.progressBar.isVisible  = true
            val response = try {
                RetrofitInstance.api.getTodos()
            }catch (e: IOException){
                Log.d(TAG ,"IoException, you might not have internet connection.")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.d(TAG , "HttpException, unExpected response.")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null){
                todoAdaptor.todos = response.body()!!
                binding.progressBar.isVisible = false
            }else{
                Log.d(TAG, "response is not successful.")
            }
        }

    }

    private fun setAdaptorToRecyclerView() {
        todoAdaptor = TodoAdaptor()
        binding.recyclerView.adapter = todoAdaptor


    }

}

