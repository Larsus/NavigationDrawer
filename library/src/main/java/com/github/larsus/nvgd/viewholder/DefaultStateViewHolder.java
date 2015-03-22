package com.github.larsus.nvgd.viewholder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.github.larsus.nvgd.R;
import com.github.larsus.nvgd.model.StateModel;

/**
 * @author Larsus
 * @version 1.0
 * @since 22.03.2015
 */
public class DefaultStateViewHolder extends BaseStateViewHolder<StateModel> {

    public DefaultStateViewHolder(View view, StateModel stateModel) {
        super(view, stateModel);
    }

    @Override
    protected void injectViews(View view) {
        checkBox = (CheckBox) view.findViewById(R.id.nvg_drawer_item_check_box);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                model.setState(isChecked);
            }
        });
    }

    @Override
    public void updateView() {
        checkBox.setChecked(model.getState());
        checkBox.setText(model.getTitle());

        if (model.getState()) {
            checkBox.setAlpha(1f);
        } else {
            checkBox.setAlpha(0.54f);
        }
    }
}
