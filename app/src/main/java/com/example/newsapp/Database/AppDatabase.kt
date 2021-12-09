/**
 Classe para criar a instância do banco de dados e definir as entidades
 */
package com.example.newsapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.Article

@Database(entities = arrayOf(Article::class), version = 1)
abstract class AppDatabase: RoomDatabase() {

    //Para garantir as funções estáticas e não ser necessário declarar um objeto
    //para utilizá-las
    companion object{
        private val DB_NAME = "article"
        private var instance: AppDatabase? = null

        private fun create(context: Context): AppDatabase?{
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME).build()
        }
        //Validação se já existe a instance do banco de dados e evitar várias criações
        fun getInstance(context: Context): AppDatabase {
            if(instance == null) {
                instance = create(context)
            }
                return instance!!
            }
        }

    abstract fun ArticleDAO(): ArticleDAO
}