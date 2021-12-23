package com.plcoding.multipleroomtables

import androidx.lifecycle.LiveData
import androidx.room.*
import com.plcoding.multipleroomtables.entities.Student
import com.plcoding.multipleroomtables.entities.Subject
import com.plcoding.multipleroomtables.entities.relations.*

@Dao
interface SchoolDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentSubjectCrossRef(crossRef: StudentSubjectCrossRef)

    @Query("SELECT * FROM Subject")
    fun getAllSubjects(): LiveData<List<Subject>>


    @Delete
    fun deleteSubject(subject: Subject)

    @Delete
    fun deleteStudent(student: Student)

//    @Transaction
//    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
//    suspend fun getSchoolAndDirectorWithSchoolName(schoolName: String): List<SchoolAndDirector>
//
//    @Transaction
//    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
//    suspend fun getSchoolWithStudents(schoolName: String): List<SchoolWithStudents>

    @Transaction
    @Query("SELECT * FROM subject WHERE subjectName=:subjectName")
    fun getStudentsOfSubject(subjectName: String): LiveData<List<SubjectWithStudents>>

    @Transaction
    @Query("SELECT * FROM student WHERE studentName=:studentName")
    suspend fun getSubjectsOfStudent(studentName: String): List<StudentWithSubjects>
}