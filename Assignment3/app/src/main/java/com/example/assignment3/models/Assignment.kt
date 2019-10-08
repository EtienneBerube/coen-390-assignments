package com.example.assignment3.models

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(indices = [Index("courseId")],
    foreignKeys = arrayOf(
    ForeignKey(
    onDelete = CASCADE,
    entity = Course::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("courseId"))))
data class Assignment(@PrimaryKey(autoGenerate = true)
                      val id: Int,
                      @ColumnInfo(name = "name") val name: String,
                      @ColumnInfo(name = "grade") val grade: Double,
                      @ColumnInfo(name = "courseId") val courseId: String) {

    fun getGradeAsLetter(): String {
        return if (grade >= 90) {
            "A+"
        } else if (grade >= 85) {
            "A"
        } else if (grade >= 80) {
            "A-"
        } else if (grade >= 77) {
            "B+"
        } else if (grade >= 73) {
            "B"
        } else if (grade >= 70) {
            "B-"
        } else if (grade >= 67) {
            "C+"
        } else if (grade >= 63) {
            "C"
        } else if (grade >= 60) {
            "C-"
        } else if (grade >= 57) {
            "D+"
        } else if (grade >= 53) {
            "D"
        } else if (grade >= 50) {
            "D-"
        } else {
            "F"
        }
    }
}