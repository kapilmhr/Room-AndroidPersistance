package app.frantic.persistanceroom;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
    EditText name,address,faculty;
    Button btn;
    StudentDatabase database;
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        student = getIntent().getParcelableExtra("student");
        database = StudentDatabase.getDatabase(this);
        name = (EditText) findViewById(R.id.ed_add_name);
        address = (EditText) findViewById(R.id.ed_add_address);
        faculty = (EditText) findViewById(R.id.ed_add_faculty);
        name.setText(student.getName());
        address.setText(student.getAddress());
        faculty.setText(student.getFaculty());
        btn= (Button) findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new StudentAsync().execute(student);
            }
        });
    }
    public class StudentAsync extends AsyncTask<Student,Void,Void> {

        @Override
        protected Void doInBackground(Student... students) {
            Student student = students[0];
            student.setName(name.getText().toString());
            student.setAddress(address.getText().toString());
            student.setFaculty(faculty.getText().toString());
            database.daoAccess().updateStudent(student);
            finish();
            return null;
        }
    }
}
