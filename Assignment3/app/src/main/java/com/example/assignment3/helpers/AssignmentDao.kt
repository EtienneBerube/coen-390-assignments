package com.example.assignment3.helpers

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.assignment3.models.Assignment

@Dao
interface AssignmentDao {

    @Query("SELECT * FROM assignment")
    fun getAll(): List<Assignment>

    @Query("SELECT * FROM assignment WHERE id IN (:assignmentIds)")
    fun loadAllByIds(assignmentIds: IntArray): List<Assignment>

    @Query("SELECT * FROM assignment WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Assignment

    @Query("SELECT * FROM assignment WHERE courseId = :courseId")
    fun getAllAssignmentsForCourse(courseId: Int): List<Assignment>

    @Insert
    fun insertAll(vararg course: Assignment)

    @Delete
    fun delete(user: Assignment)

    @Query("DELETE FROM Assignment WHERE name = :name")
    fun deleteByName(name: String)


}
