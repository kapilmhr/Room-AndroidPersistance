package app.frantic.persistanceroom;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddStudentActivity extends AppCompatActivity {
    EditText name,address,faculty;
    Button btn;
    StudentDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        database = StudentDatabase.getDatabase(this);
        name = (EditText) findViewById(R.id.ed_add_name);
        address = (EditText) findViewById(R.id.ed_add_address);
        faculty = (EditText) findViewById(R.id.ed_add_faculty);
        btn= (Button) findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new StudentAsync().execute();
            }
        });
    }

    public class StudentAsync extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            Student student = new Student();
            student.setName(name.getText().toString());
            student.setAddress(address.getText().toString());
            student.setFaculty(faculty.getText().toString());
            database.daoAccess().addStudent(student);
            finish();
            return null;
        }
    }
}
