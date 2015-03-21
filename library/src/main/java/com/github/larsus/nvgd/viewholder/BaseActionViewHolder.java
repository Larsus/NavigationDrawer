package com.github.larsus.nvgd.viewholder;

import android.view.View;

import com.github.larsus.nvgd.model.ActionModel;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public abstract class BaseActionViewHolder<T extends ActionModel> extends BaseViewHolder<T> {

    protected BaseActionViewHolder(View view, T actionModel) {
        super(view, actionModel);
    }

    public abstract void selectView(View view);
    public abstract void deSelectView(View view);
}
