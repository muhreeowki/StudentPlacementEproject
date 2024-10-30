package com.example.studentplacement.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.studentplacement.R;

import java.util.List;

public class SelectedStudentAdapter extends RecyclerView.Adapter<SelectedStudentAdapter.ViewHolder> {
    private List<SelectedStudent> studentsList;
    private OnItemClickListener onItemClickListener;

    public SelectedStudentAdapter(List<SelectedStudent> studentsList) {
        this.studentsList = studentsList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_selected_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SelectedStudent student = studentsList.get(position);
        holder.bind(student);
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvStudentName, tvStudentId, tvCompanyName, tvPackage, tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            tvStudentName = itemView.findViewById(R.id.tvStudentName);
            tvStudentId = itemView.findViewById(R.id.tvStudentId);
            tvCompanyName = itemView.findViewById(R.id.tvCompanyName);
            tvPackage = itemView.findViewById(R.id.tvPackage);
            tvDate = itemView.findViewById(R.id.tvDate);

            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }

        public void bind(SelectedStudent student) {
            tvStudentName.setText(student.getStudentName());
            tvStudentId.setText(student.getStudentId());
            tvCompanyName.setText(student.getCompanyName());
            tvPackage.setText(String.format("%.2f LPA", student.getPackageOffered()));
            tvDate.setText(student.getSelectionDate());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}