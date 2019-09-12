package com.example.assignment_1.components;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment_1.R;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CourseInnerAdapter<T> extends RecyclerView.Adapter<CourseInnerAdapter.CourseInnerViewHolder>{
    private List<Map.Entry<String, T>> grades;

    public CourseInnerAdapter(Map<String, T> grades) {
        this.grades = grades.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).collect(Collectors.toList());
    }

    @NonNull
    @Override
    public CourseInnerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.course_grades_inner_layout, viewGroup, false);
        CourseInnerViewHolder viewHolder = new CourseInnerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseInnerViewHolder courseInnerViewHolder, int i) {
        courseInnerViewHolder.nameView.setText(grades.get(i).getKey());
        courseInnerViewHolder.gradeView.setText(String.format("%s", grades.get(i).getValue()));
    }

    @Override
    public int getItemCount() {
        return grades.size();
    }

    public static class CourseInnerViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView nameView;
        public TextView gradeView;
        public CourseInnerViewHolder(@NonNull View view) {
            super(view);
            nameView = view.findViewById(R.id.course_grade_inner_name);
            gradeView = view.findViewById(R.id.course_grade_inner_grade);
        }
    }
}
