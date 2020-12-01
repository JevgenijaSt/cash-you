package com.example.cash_you.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cash_you.*;
import com.example.cash_you.data.CategoryManager;
import com.example.cash_you.helpers.Helper;
import com.example.cash_you.models.Category;
import com.example.cash_you.activities.ActivityManageCategories;
import com.example.cash_you.viewholders.ViewHolderCategory;
import com.example.cash_you.viewholders.ViewHolderEntity;


public class AdapterManageCategories extends AdapterManageEntity
{
    public AdapterManageCategories(ActivityManageCategories _parent) { super(_parent); }


    @Override
    public ViewHolderCategory onCreateViewHolder(ViewGroup vg, int viewType)
    {
        View itemView = LayoutInflater.from(vg.getContext()).inflate(R.layout.row_layout_text_underline, vg, false);
        return new ViewHolderCategory(parent, itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolderEntity holder, int position) {
        Category cat = CategoryManager.getInstance().GetCategories().get(position);
        if (cat != null) {
            holder.Entity = cat;

            // Fields
            holder.title.setText(cat.GetTitle());
            holder.icon.setImageDrawable(Helper.getDrawable(R.drawable.ic_view_list_white_24dp));
            holder.icon.setColorFilter(cat.GetColor());
        }
    }

    @Override public int getItemCount()
    {
        return CategoryManager.getInstance().GetCategoriesCount();
    }

}
