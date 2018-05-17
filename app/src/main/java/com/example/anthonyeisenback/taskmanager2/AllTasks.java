package com.example.anthonyeisenback.taskmanager2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.LENGTH_LONG;

public class AllTasks extends Fragment implements IncompleteAdapter.AdapterCallback{

    private IncompleteAdapter adapter;
    private TaskDatabase taskDatabase;
    @BindView(R.id.all_tasks)
    protected RecyclerView recyclerView;
    private List<TaskCreator> taskCreatorList;


    public static AllTasks newInstance() {

        Bundle args = new Bundle();

        AllTasks fragment = new AllTasks();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        taskDatabase = ((TaskApplication) getActivity().getApplicationContext()).getDatabase();
        adapter = new IncompleteAdapter(taskDatabase.taskDAO().getTasks(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        adapter.notifyDataSetChanged();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_task_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void rowClicked(final TaskCreator taskCreator) {
        if (taskCreator.isCompleted()) {
         removeFinished(taskCreator);

        } else {
            edit(taskCreator);
        }
    }

    @Override
    public void rowLongClicked(final TaskCreator taskCreator) {

    }

    private void removeFinished(final TaskCreator taskCreator) {


    }

    private void edit(final TaskCreator taskCreator) {

    }
}

