package com.github.larsus.nvgd.handler;

import com.github.larsus.nvgd.model.ActionModel;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public interface OnActionItemClickListener<T extends ActionModel> {
    void OnActionItemClick(T model);
}
