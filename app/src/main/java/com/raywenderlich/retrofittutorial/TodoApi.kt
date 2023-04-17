package com.raywenderlich.retrofittutorial

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TodoApi {
    @GET("/todos")
    //suspend keyword is used to do do work in another thread except main.
    suspend fun getTodos():Response<List<Todo>>

    //@POST("/createTodo")
    //fun createTodo(@Body todo: Todo): Response<CreateTodoResponse>
}