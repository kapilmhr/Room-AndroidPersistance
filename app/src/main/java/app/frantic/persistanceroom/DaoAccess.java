package app.frantic.persistanceroom;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by frantic on 12/21/17.
 */
@Dao
public interface DaoAccess {

    @Insert
    void addStudent(Student student);

    @Query("SELECT * FROM Student")
    LiveData<List<Student>> fetchAllStudents();

    @Delete
    void deleteStudent(Student student);

    @Update
    void updateStudent(Student student);
}
