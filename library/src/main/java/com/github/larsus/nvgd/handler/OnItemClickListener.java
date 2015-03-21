package com.github.larsus.nvgd.handler;

import com.github.larsus.nvgd.model.BaseModel;

/**
 * @author Larsus
 * @version 1.0
 * @since 22.03.2015
 */
public interface OnItemClickListener<T extends BaseModel> {
    void OnItemClick(T model);
}
