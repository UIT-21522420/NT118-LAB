package com.example.lab3_02.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lab3_02.Student;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentsManager";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(StudentContract.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(StudentContract.SQL_DROP_TABLE);
        onCreate(db);
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StudentContract.COLUMN_NAME, student.getName());
        values.put(StudentContract.COLUMN_EMAIL, student.getEmail());
        values.put(StudentContract.COLUMN_MSSV, student.getMssv());
        values.put(StudentContract.COLUMN_KHOA, student.getKhoa());

        db.insert(StudentContract.TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                StudentContract.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(StudentContract.COLUMN_NAME));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(StudentContract.COLUMN_EMAIL));
                String mssv = cursor.getString(cursor.getColumnIndexOrThrow(StudentContract.COLUMN_MSSV));
                String nganhhoc = cursor.getString(cursor.getColumnIndexOrThrow(StudentContract.COLUMN_KHOA));

                Student student = new Student(name, mssv, email, nganhhoc);
                students.add(student);
            }
            cursor.close();
        }
        db.close();
        return students;
    }

    public void updateStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StudentContract.COLUMN_NAME, student.getName());
        values.put(StudentContract.COLUMN_EMAIL, student.getEmail());
        values.put(StudentContract.COLUMN_MSSV, student.getMssv());
        values.put(StudentContract.COLUMN_KHOA, student.getKhoa());

        String selection = StudentContract.COLUMN_MSSV + " = ?";
        String[] selectionArgs = { student.getMssv() };

        int count = db.update(
                StudentContract.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        db.close();
    }

    public void deleteStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();

        String selection = StudentContract.COLUMN_MSSV + " = ?";
        String[] selectionArgs = { student.getMssv() };

        int deletedRows = db.delete(
                StudentContract.TABLE_NAME,
                selection,
                selectionArgs
        );
        db.close();
    }
}
