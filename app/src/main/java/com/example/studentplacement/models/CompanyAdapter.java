package com.example.studentplacement.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.studentplacement.R;

import java.util.List;

// CompanyAdapter.java
public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder> {
    private List<Company> companies;

    public CompanyAdapter(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public CompanyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item_company, parent, false);
        return new CompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompanyViewHolder holder, int position) {
        Company company = companies.get(position);
        holder.tvCompanyName.setText(company.getName());
        holder.tvJobDescription.setText(company.getDescription());
        holder.tvEligibility.setText("Requierments: " + company.getRequirements());
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    static class CompanyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCompanyName, tvJobDescription, tvEligibility;

        public CompanyViewHolder(View itemView) {
            super(itemView);
            tvCompanyName = itemView.findViewById(R.id.tvCompanyName);
            tvJobDescription = itemView.findViewById(R.id.tvJobDescription);
            tvEligibility = itemView.findViewById(R.id.tvEligibility);
        }
    }
}