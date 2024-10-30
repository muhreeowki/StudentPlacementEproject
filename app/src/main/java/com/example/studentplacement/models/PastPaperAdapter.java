package com.example.studentplacement.models;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentplacement.R;

import java.io.File;
import java.util.List;

public class PastPaperAdapter extends RecyclerView.Adapter<PastPaperAdapter.ViewHolder> {
    private List<PastPaper> papersList;
    private Context context;

    public PastPaperAdapter(Context context, List<PastPaper> papersList) {
        this.context = context;
        this.papersList = papersList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_past_paper, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PastPaper paper = papersList.get(position);
        holder.tvCompanyName.setText(paper.getCompanyName());
        holder.tvYear.setText(String.valueOf(paper.getYear()));

        holder.itemView.setOnClickListener(v -> {
            File paperFile = new File(paper.getPaperUrl());
            Uri paperUri = FileProvider.getUriForFile(context,
                    context.getApplicationContext().getPackageName() + ".provider", paperFile);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(paperUri, "application/" + paper.getPaperType());
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return papersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCompanyName, tvYear;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCompanyName = itemView.findViewById(R.id.tvCompanyName);
            tvYear = itemView.findViewById(R.id.tvYear);
        }
    }
}
