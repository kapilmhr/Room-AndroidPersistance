package app.frantic.persistanceroom;

import android.app.AlertDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    StudentAdapter adapter;
    RecyclerView recyclerView;
    StudentDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        database = StudentDatabase.getDatabase(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudentAdapter(new ArrayList<Student>(),this);
        recyclerView.setAdapter(adapter);
        LiveData<List<Student>> studentList = database.daoAccess().fetchAllStudents();
        studentList.observe(this,new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> students) {
                adapter.addItems(students);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddStudentActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onClick(final Student student) {
        CharSequence[] charSequences = new CharSequence[]{"Delete", "Update"};
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setItems(charSequences, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i==0){
                    new DeleteTask().execute(student);
                }else {
                    Intent intent = new Intent(getApplicationContext(),UpdateActivity.class);
                    intent.putExtra("student",student);
                    startActivity(intent);
                }
            }
        });
        alert.create();
        alert.show();
    }

    public class DeleteTask extends AsyncTask<Student,Void,Void>{


        @Override
        protected Void doInBackground(Student... students) {
            database.daoAccess().deleteStudent(students[0]);
            return null;
        }
    }
}
