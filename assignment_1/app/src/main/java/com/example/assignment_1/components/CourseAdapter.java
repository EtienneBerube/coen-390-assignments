package com.example.assignment_1.components;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment_1.R;
import com.example.assignment_1.models.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Course> courses;
    private boolean isLetter = false;
    private Context context;

    public CourseAdapter(List<Course> courses, Context context) {
        this.courses = courses;
        this.context = context;

        Log.d("adapter", "Courses size: " + courses.size());
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.course_grades_layout, viewGroup, false);
        CourseViewHolder viewHolder = new CourseViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder courseViewHolder, int i) {
        courseViewHolder.textView.setText(courses.get(i).getName());
        Log.d("adapter", "bind view holder for: " + courses.get(i).getName());

        if(courses.get(i).getGrades().size() == 0){
            courseViewHolder.error.setVisibility(View.VISIBLE);
            courseViewHolder.listView.setVisibility(View.INVISIBLE);
            courseViewHolder.averageView.setText("Average: -");
            return;
        }

        if(isLetter){
            CourseInnerAdapter<String> innerAdapter = new CourseInnerAdapter(courses.get(i).getGradesAsLetters());
            courseViewHolder.listView.setAdapter(innerAdapter);

            courseViewHolder.averageView.setText(String.format("Average: %s", courses.get(i).getAverageLetter()));
        }else {
            CourseInnerAdapter<Double> innerAdapter = new CourseInnerAdapter(courses.get(i).getGrades());
            courseViewHolder.listView.setAdapter(innerAdapter);

            courseViewHolder.averageView.setText(String.format("Average: %.2f", courses.get(i).getAverage()));

        }

        courseViewHolder.listView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setIsLetterGrade(boolean isLetterGrade){
        this.isLetter = isLetterGrade;
        super.notifyDataSetChanged();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public TextView error;
        public RecyclerView listView;
        public TextView averageView;
        public CourseViewHolder(@NonNull View view) {
            super(view);
            textView = view.findViewById(R.id.grade_activity_course_name);
            averageView = view.findViewById(R.id.grade_average);
            listView = view.findViewById(R.id.grade_activity_course_grades);
            error = view.findViewById(R.id.no_assignment_error);
        }
    }
}
