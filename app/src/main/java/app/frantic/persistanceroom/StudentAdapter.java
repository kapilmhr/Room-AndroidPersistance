package app.frantic.persistanceroom;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by frantic on 12/21/17.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyHolder> {
    List<Student> studentList;
    OnItemClickListener onItemClickListener;

    public StudentAdapter(List<Student> studentList, OnItemClickListener onItemClickListener) {
        this.studentList = studentList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_student_item,null));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Student student = studentList.get(position);
        holder.name.setText(student.getName());
        holder.faculty.setText(student.getFaculty());
        holder.address.setText(student.getAddress());

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void addItems(List<Student> studentList) {
        this.studentList = studentList;
        notifyDataSetChanged();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name,faculty,address;
        public MyHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            faculty = itemView.findViewById(R.id.faculty);
            address = itemView.findViewById(R.id.address);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemClickListener.onClick(studentList.get(getAdapterPosition()));
                    return true;
                }
            });
        }
    }
}
