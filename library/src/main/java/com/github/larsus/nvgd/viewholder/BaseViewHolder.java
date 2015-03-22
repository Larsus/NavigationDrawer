package com.github.larsus.nvgd.viewholder;

import android.view.View;

import com.github.larsus.nvgd.model.BaseModel;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public abstract class BaseViewHolder<T extends BaseModel> {

    protected T model;

    protected BaseViewHolder(View view, T model){
        this.model = model;
        injectViews(view);
        updateView();
    }

    protected abstract void injectViews(View view);

    public abstract void updateView();

}