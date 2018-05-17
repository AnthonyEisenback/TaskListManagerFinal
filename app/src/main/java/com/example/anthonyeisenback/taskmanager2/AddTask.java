package com.example.anthonyeisenback.taskmanager2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTask extends Fragment implements View.OnClickListener, IncompleteAdapter.AdapterCallback{

    private ActivityCallback activityCallback;
    private List<TaskCreator> taskCreatorList;

    @BindView(R.id.add_name_to_game_editText)
    protected EditText taskNameEdittext;

    @BindView(R.id.description_edit)
    protected EditText descriptionEdittext;

    private TaskDatabase taskDatabase;
    private IncompleteAdapter incompleteAdapter;
    @Override
    public void onStart() {
        super.onStart();
        taskDatabase = ((TaskApplication) getActivity().getApplicationContext()).getDatabase();
        incompleteAdapter = new IncompleteAdapter(taskDatabase.taskDAO().getTasks(), this);


    }

    public static AddTask newInstance() {

        Bundle args = new Bundle();

        AddTask fragment = new AddTask();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_task_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.add_task_to_list_floating_button)
    protected void addTask() {
        String taskName = taskNameEdittext.getText().toString();
        String description = descriptionEdittext.getText().toString();

        if (TextUtils.isEmpty(taskName) || TextUtils.isEmpty(description)) {
            Toast.makeText(getContext(), "Please Fill out All Fields", Toast.LENGTH_LONG).show();
        } else {
            addTaskToDatabase(taskName, description);
            activityCallback.addClicked();

        }
    }

    private void addTaskToDatabase(final String taskName, final String description) {
        TaskCreator taskCreator = new TaskCreator(taskName, description, false, new Date());
        taskDatabase.taskDAO().addTask(taskCreator);
    activityCallback.addClicked();
    }



    @Override
    public void onClick(View v) {

    }


    public void attachParent(ActivityCallback activityCallback) {

        this.activityCallback = activityCallback;
    }

    @Override
    public void rowClicked(TaskCreator taskCreator) {

    }

    @Override
    public void rowLongClicked(TaskCreator taskCreator) {

    }

    public interface ActivityCallback {
        void addClicked();

        Context context();
    }

}
