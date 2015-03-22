package com.github.larsus.nvgd.viewholder;

import android.view.View;
import android.widget.TextView;

import com.github.larsus.nvgd.R;
import com.github.larsus.nvgd.model.HeaderModel;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public class DefaultHeaderViewHolder extends BaseViewHolder<HeaderModel> {

    protected TextView mTitle;

    public DefaultHeaderViewHolder(View view, HeaderModel headerModel) {
        super(view, headerModel);
    }

    private void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    protected void injectViews(View view) {
        mTitle = (TextView) view.findViewById(R.id.nvg_drawer_item_title);
    }

    @Override
    public void updateView() {
        setTitle(model.getTitle());
    }
}
