package com.github.larsus.nvgd.viewholder;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.larsus.nvgd.R;
import com.github.larsus.nvgd.model.ActionModel;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public class DefaultActionViewHolder extends BaseActionViewHolder<ActionModel> {

    ImageView mIcon;
    TextView mTitle;

    public DefaultActionViewHolder(View view, ActionModel model) {
        super(view, model);
    }

    private void setIcon(int resId) {
        if (resId == 0) {
            mIcon.setVisibility(View.GONE);
        } else {
            mIcon.setVisibility(View.VISIBLE);
            mIcon.setImageResource(resId);
        }
    }

    private void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    protected void injectViews(View view) {
        mIcon = (ImageView) view.findViewById(R.id.nvg_drawer_item_icon);
        mTitle = (TextView) view.findViewById(R.id.nvg_drawer_item_title);
    }

    @Override
    public void updateView() {
        setIcon(model.getIcon());
        setTitle(model.getTitle());

        if (model.isSelected()) {
            mIcon.setAlpha(1f);
            mTitle.setAlpha(1f);
        } else {
            mIcon.setAlpha(0.54f);
            mTitle.setAlpha(0.87f);
        }
    }

    @Override
    public void selectView(View view) {
        view.setBackgroundResource(R.drawable.nvgd_selected_item);
        mIcon.setColorFilter(Color.parseColor("#DD2C00"), PorterDuff.Mode.SRC_ATOP);
        mTitle.setTextColor(Color.parseColor("#DD2C00"));
    }

    @Override
    public void deSelectView(View view) {
        view.setBackgroundResource(R.drawable.nvgd_unselected_item);
        mIcon.clearColorFilter();
        mTitle.setTextColor(Color.parseColor("#000000"));
    }
}
