package com.example.assignment_1.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
        setupRandomData();

        setupUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.empty_menu, menu);
        return true;
    }

    /**
     * Creates between 1 and 5 difference classes
     */
    private void setupRandomData() {

        store = new CourseStore();

        int number = (int) (Math.random() * 5) + 1;

        for (int i = 1; i <= number; i++) {
            store.getCourses().add(new Course("Course " + i));
        }

    }

    /**
     * Acts when a item in the action bar is pressed
     * @param item The selected item
     * @return if the action was performed
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // When the arrow is pressed -> go home
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.show_letter_grades:
                // When the checkbox is checked, toggle the way to see grades
                ((CourseAdapter) recyclerView.getAdapter()).setIsLetterGrade(!item.isChecked());
                item.setChecked(!item.isChecked());
                super.onOptionsItemSelected(item);
                return true;
            default:
                return true;
        }
    }

    private void setupUI() {

        recyclerView = findViewById(R.id.grade_activity_recycler);

        adapter = new CourseAdapter(store.getCourses(), this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

}
