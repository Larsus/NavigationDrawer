package com.github.larsus.nvgd.viewholder;

import android.view.View;
import android.widget.CheckBox;

import com.github.larsus.nvgd.handler.OnStateItemClickListener;
import com.github.larsus.nvgd.model.StateModel;

/**
 * @author Larsus
 * @version 1.0
 * @since 21.03.2015
 */
public abstract class BaseStateViewHolder<T extends StateModel> extends BaseViewHolder<T> {

    protected CheckBox checkBox;

    protected BaseStateViewHolder(View view, T stateModel) {
        super(view, stateModel);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        checkBox.setOnClickListener(onClickListener);
    }
}
