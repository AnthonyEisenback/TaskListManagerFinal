package com.example.anthonyeisenback.taskmanager2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IncompleteAdapter extends RecyclerView.Adapter<IncompleteAdapter.TaskViewHolder> {

    private List<TaskCreator> taskCreatorList;
    private TaskCreator taskCreator;
    private AdapterCallback adapterCallback;
    private AddTask addTask = new AddTask();
    protected TextView taskDescription;

    protected TextView taskName;

    protected TextView dateText;


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.object_item, parent, false);
        return new TaskViewHolder(itemView);
    }

    public IncompleteAdapter(List<TaskCreator> taskCreatorList, AdapterCallback adapterCallback) {
        this.taskCreatorList = taskCreatorList;
        this.adapterCallback = adapterCallback;
    }



    @Override
    public void onBindViewHolder(@NonNull IncompleteAdapter.TaskViewHolder holder, int position) {
        holder.bind(taskCreatorList.get(position));

    }



    @Override
    public int getItemCount() {
        return taskCreatorList.size();

    }

    public void updateList(List<TaskCreator> list) {
        taskCreatorList = list;
        notifyDataSetChanged();
    }


    public class TaskViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.description_textview)
        protected TextView taskDescription;

        @BindView(R.id.task_name)
        protected TextView taskName;

        @BindView(R.id.date_textview)
        protected TextView dateText;


        public TaskViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public View.OnClickListener onClick(final TaskCreator taskCreator) {
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapterCallback.rowClicked(taskCreator);
                }
            };
        }

        public View.OnLongClickListener onLongClick(final TaskCreator taskCreator) {
            return new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    adapterCallback.rowLongClicked(taskCreator);
                    return false;
                }
            };
        }



        public void bind(TaskCreator taskCreator) {
            taskDescription.setText(taskCreator.getTaskDescription());
            taskName.setText(taskCreator.getTaskName());
            adapterCallback.rowClicked(taskCreator);
            adapterCallback.rowLongClicked(taskCreator);
        }




    }


    public interface AdapterCallback {
        Context getContext();
        void rowClicked(TaskCreator taskCreator);
        void rowLongClicked(TaskCreator taskCreator);
    }
}
