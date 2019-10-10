package com.example.assignment3.helpers

import androidx.room.*
import com.example.assignment3.models.Assignment
import com.example.assignment3.models.Course

@Dao
interface CourseDao {
    @Query("SELECT * FROM course")
    fun getAll(): MutableList<Course>

    @Query("SELECT * FROM course WHERE id IN (:courseIds)")
    fun loadAllByIds(courseIds: IntArray): MutableList<Course>

    @Query("SELECT * FROM course WHERE name LIKE :first LIMIT 1")
    fun findByName(first: String): Course

    @Query("SELECT * FROM course WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): Course

    @Query("SELECT * FROM course WHERE code LIKE :code LIMIT 1")
    fun findByCode(code: String): Course

    @Query("SELECT * FROM assignment WHERE courseId = :courseId")
    fun getAllAssignmentsForCourse(courseId : Int): MutableList<Assignment>

    @Transaction
    fun getAverageForCourse(course : Course): Double{
        val assignments = getAllAssignmentsForCourse(course.id)

        if (assignments.isEmpty())
            return 0.0
        else
            return assignments.sumByDouble { it.grade } / assignments.size
    }

    @Insert
    fun insertAll(vararg course: Course)

    @Delete
    fun delete(user: Course)

    @Query("DELETE FROM course WHERE name = :name")
    fun deleteByName(name: String)


}