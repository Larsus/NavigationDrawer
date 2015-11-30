package com.github.larsus.nvgd.viewholder;

import android.view.View;
import android.widget.CheckBox;

import com.github.larsus.nvgd.model.StateModel;

/**
 * @author Larsus
 * @version 1.0
 * @since 21.03.2015
 */
public abstract class BaseStateViewHolder<TModel extends StateModel> extends BaseViewHolder<TModel> {

    protected CheckBox checkBox;

    protected BaseStateViewHolder(View view, TModel stateModel) {
        super(view, stateModel);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        checkBox.setOnClickListener(onClickListener);
    }
}
