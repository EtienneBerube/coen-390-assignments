package com.example.assignment3.helpers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assignment3.models.Assignment
import com.example.assignment3.models.Course

@Database(entities = arrayOf(Course::class, Assignment::class), version = 2)
abstract class CourseDatabase : RoomDatabase() {

    companion object Creator {
        fun create(context: Context): CourseDatabase {
            return Room.databaseBuilder(
                context,
                CourseDatabase::class.java, "Course-DB"
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun courseDao(): CourseDao
    abstract fun assignmentDao(): AssignmentDao
}