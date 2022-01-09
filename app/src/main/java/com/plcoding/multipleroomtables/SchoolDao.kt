package com.plcoding.multipleroomtables

import androidx.lifecycle.LiveData
import androidx.room.*
import com.plcoding.multipleroomtables.entities.Grade
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
    suspend fun insertGrade(grade: Grade)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentSubjectCrossRef(crossRef: StudentSubjectCrossRef)


    @Query("SELECT * FROM Subject")
    fun getAllSubjects(): LiveData<List<Subject>>

    @Query("SELECT * FROM Student")
    fun getAllStudents(): LiveData<List<Student>>



    @Delete
    fun deleteSubject(subject: Subject)

    @Delete
    fun deleteStudent(student: Student)

    @Delete
    fun deleteGrade(grade: Grade)

    @Delete
    fun deleteStudentSubjectCrossRef(crossRef: StudentSubjectCrossRef)


    @Transaction
    @Query("SELECT * FROM subject WHERE subjectName=:subjectName")
    fun getStudentsOfSubject(subjectName: String): LiveData<List<SubjectWithStudents>>

    @Transaction
    @Query("SELECT * FROM student WHERE studentIndex=:studentIndex")
    fun getSubjectsOfStudent(studentIndex: Int): LiveData<List<StudentWithSubjects>>

    @Query("SELECT su.subjectName,su.start,su.duration FROM Subject su INNER JOIN StudentSubjectCrossRef ss ON ss.subjectName = su.subjectName INNER JOIN Student st ON ss.studentIndex = st.studentIndex WHERE st.studentIndex= :studentIndex")
    fun getSubjectNamesByStudentName(studentIndex: Int): LiveData<List<Subject>>

    @Query("SELECT st.studentIndex,st.studentName FROM Student st INNER JOIN StudentSubjectCrossRef ss ON ss.studentIndex = st.studentIndex INNER JOIN Subject su ON ss.subjectName = su.subjectName WHERE su.subjectName=:subjectName")
    fun getStudentNamesBySubjectName(subjectName: String): LiveData<List<Student>>

    @Query("SELECT * FROM Grade WHERE (Grade.subjectName=:subjectName AND Grade.studentIndex=:studentIndex)")
    fun getGradesByStudentIndexAndSubjectName(studentIndex: Int,subjectName: String): LiveData<List<Grade>>

    @Query("SELECT SUM(grade*weight/scaleEquation)/SUM(weight) FROM (SELECT * FROM Grade WHERE (Grade.subjectName=:subjectName AND Grade.studentIndex=:studentIndex))")
    fun getGradesAverageOfStudent(studentIndex: Int,subjectName: String): Float

    //thanks: https://stackoverflow.com/questions/67842648/android-room-dao-get-one-field-with-crossref
}