package com.example.cash_you.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cash_you.*;
import com.example.cash_you.data.BudgetManager;
import com.example.cash_you.models.Budget;
import com.example.cash_you.activities.ActivityManageBudgets;
import com.example.cash_you.viewholders.ViewHolderBudget;
import com.example.cash_you.viewholders.ViewHolderEntity;


public class AdapterManageBudgets extends AdapterManageEntity
{
    public AdapterManageBudgets(ActivityManageBudgets _parent) { super(_parent); }

    @Override
    public ViewHolderBudget onCreateViewHolder(ViewGroup vg, int viewType)
    {
        View itemView = LayoutInflater.from(vg.getContext()).inflate(R.layout.row_layout_text_underline, vg, false);
        return new ViewHolderBudget(parent, itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolderEntity holder, int position)
    {
        //Budget
        Budget br = BudgetManager.getInstance().GetBudgets().get(position);
        if (br != null) {
            holder.Entity = br;

            // Fields
            holder.title.setText(br.GetName());
            holder.subTitle.setText(br.GetDateFormatted(parent) + "\n" + br.GetPeriodFormatted(parent));

            if (holder.subTitle.getText() != null) { holder.subTitle.setVisibility(View.VISIBLE); }

            holder.secondaryIcon.setVisibility( (br.GetSelected() ? View.VISIBLE : View.GONE) );
        }
    }

    @Override public int getItemCount()
    {
        return BudgetManager.getInstance().GetBudgetCount();
    }

}
