package com.example.assignment3.helpers

import androidx.room.*
import com.example.assignment3.models.Assignment
import com.example.assignment3.models.Course

@Dao
interface AssignmentDao {

    @Query("SELECT * FROM assignment")
    fun getAll(): MutableList<Assignment>

    @Query("SELECT * FROM assignment WHERE id IN (:assignmentIds)")
    fun loadAllByIds(assignmentIds: IntArray): MutableList<Assignment>

    @Query("SELECT * FROM assignment WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Assignment

    @Query("SELECT * FROM assignment WHERE courseId = :courseId")
    fun getAllAssignmentsForCourse(courseId: Int): MutableList<Assignment>

    @Transaction
    fun getAverageForAll(): Double{
        val assignments = getAll()

        if (assignments.isEmpty())
            return 0.0
        else
            return assignments.sumByDouble { it.grade } / assignments.size
    }

    @Insert
    fun insertAll(vararg course: Assignment)

    @Delete
    fun delete(user: Assignment)

    @Query("DELETE FROM Assignment WHERE name = :name")
    fun deleteByName(name: String)


}
