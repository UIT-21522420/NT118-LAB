package com.example.lab3_02.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3_02.R;
import com.example.lab3_02.Student;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private Context context;
    private ArrayList<Student> arrStudent;
    public StudentAdapter(Context context, ArrayList<Student> arrStudent) {
        this.context = context;
        this.arrStudent = arrStudent;
    }

    public interface OnEditButtonClickListener {
        void onEditButtonClick(int position);

        void onDeleteButtonClick(int position);
    }

    private OnEditButtonClickListener editButtonClickListener;

    public void setOnEditButtonClickListener(OnEditButtonClickListener listener) {
        this.editButtonClickListener = listener;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvMssv, tvEmail, tvKhoa;
        ConstraintLayout clStudent;
        Button btnSua, btnXoa;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvMssv = itemView.findViewById(R.id.tvMssv);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvKhoa = itemView.findViewById(R.id.tvKhoa);
            clStudent = itemView.findViewById((R.id.item_student_parent));
            btnSua = itemView.findViewById(R.id.btnSua);
            btnXoa = itemView.findViewById(R.id.btnXoa);
        }
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View studentView = inflater.inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(studentView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Student student = arrStudent.get(position);
        holder.tvName.setText("Name: " + student.getName());
        holder.tvMssv.setText("MSSV: " + student.getMssv());
        holder.tvEmail.setText("Email: " + student.getEmail());
        holder.tvKhoa.setText("Khoa: " + student.getKhoa());

        if (position%2==0)
        {
            holder.clStudent.setBackgroundResource(R.color.white);
        }
        else
        {
            holder.clStudent.setBackgroundResource(R.color.light_blue);
        }

        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editButtonClickListener != null) {
                    editButtonClickListener.onEditButtonClick(position);
                }
            }
        });

        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if listener is set
                if (deleteButtonClickListener != null) {
                    // Call onDeleteButtonClick method of the listener
                    deleteButtonClickListener.onDeleteButtonClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrStudent.size();
    }

    public interface OnDeleteButtonClickListener {
        void onDeleteButtonClick(int position);
    }

    private OnDeleteButtonClickListener deleteButtonClickListener;

    public void setOnDeleteButtonClickListener(OnDeleteButtonClickListener listener) {
        this.deleteButtonClickListener = listener;
    }
}
