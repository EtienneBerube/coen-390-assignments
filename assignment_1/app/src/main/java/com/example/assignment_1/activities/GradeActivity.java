package com.example.assignment_1.activities;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.assignment_1.R;
import com.example.assignment_1.components.CourseAdapter;
import com.example.assignment_1.helpers.CourseStore;
import com.example.assignment_1.models.Course;

public class GradeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private CourseStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        setupUI();
        setupLogic();
        setupRandomData();
    }

    private void setupRandomData() {

        store = CourseStore.getInstance();

        if(store.getCourses().size() == 0) {

            int number = (int) Math.random() * 5 + 1;

            for (int i = 1; i <= number; i++) {
                store.getCourses().add(new Course("Course " + 1));
            }
        }

    }

    private void setupLogic() {

    }

    private void setupUI() {

        recyclerView = findViewById(R.id.grade_activity_recycler);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        adapter = new CourseAdapter(store.getCourses());
        recyclerView.setAdapter(adapter);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

}
