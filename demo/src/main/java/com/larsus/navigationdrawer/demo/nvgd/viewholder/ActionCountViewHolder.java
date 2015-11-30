package com.larsus.navigationdrawer.demo.nvgd.viewholder;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.larsus.nvgd.viewholder.BaseActionViewHolder;
import com.larsus.navigationdrawer.demo.R;
import com.larsus.navigationdrawer.demo.nvgd.model.ActionCountModel;

/**
 * @author Alexander Evdokimov (Larsus)
 * @version 1.0
 * @since 30.11.2015
 */
public class ActionCountViewHolder extends BaseActionViewHolder<ActionCountModel> {
    protected ImageView mIcon;
    protected TextView mTitle;
    protected TextView mCounter;

    public ActionCountViewHolder(View view, ActionCountModel model) {
        super(view, model);
    }

    private void setIcon(int resId) {
        if(resId == 0) {
            this.mIcon.setVisibility(View.GONE);
        } else {
            this.mIcon.setVisibility(View.VISIBLE);
            this.mIcon.setImageResource(resId);
        }

    }

    private void setTitle(String title) {
        this.mTitle.setText(title);
    }

    private void setCount(int count) {
        this.mCounter.setText(String.valueOf(count));
    }

    protected void injectViews(View view) {
        this.mIcon = (ImageView)view.findViewById(R.id.nvg_drawer_item_icon);
        this.mTitle = (TextView)view.findViewById(R.id.nvg_drawer_item_title);
        this.mCounter = (TextView)view.findViewById(R.id.nvg_drawer_item_count);
    }

    public void updateView() {
        this.setIcon(this.model.getIcon());
        this.setTitle(this.model.getTitle());
        this.setCount(this.model.getCount());
        if(this.model.isSelected()) {
            this.mIcon.setAlpha(1.0F);
            this.mTitle.setAlpha(1.0F);
            this.mCounter.setAlpha(1.0F);
        } else {
            this.mIcon.setAlpha(0.54F);
            this.mTitle.setAlpha(0.87F);
            this.mCounter.setAlpha(0.87F);
        }

    }

    public void selectView(View view) {
        view.setBackgroundResource(com.github.larsus.nvgd.R.drawable.nvgd_selected_item);
        this.mIcon.setColorFilter(Color.parseColor("#DD2C00"), PorterDuff.Mode.SRC_ATOP);
        this.mTitle.setTextColor(Color.parseColor("#DD2C00"));
        this.mCounter.setTextColor(Color.parseColor("#DD2C00"));
    }

    public void deSelectView(View view) {
        view.setBackgroundResource(com.github.larsus.nvgd.R.drawable.nvgd_unselected_item);
        this.mIcon.clearColorFilter();
        this.mTitle.setTextColor(Color.parseColor("#000000"));
        this.mCounter.setTextColor(Color.parseColor("#000000"));
    }
}