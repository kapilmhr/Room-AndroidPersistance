package app.frantic.persistanceroom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by frantic on 12/21/17.
 */
@Database(entities = {Student.class},version = 1)
public abstract class StudentDatabase extends RoomDatabase {
    private static StudentDatabase INSTANCE;

    public static StudentDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), StudentDatabase.class, "student-db")
                            .build();
        }
        return INSTANCE;
    }
    public abstract DaoAccess daoAccess();
}
