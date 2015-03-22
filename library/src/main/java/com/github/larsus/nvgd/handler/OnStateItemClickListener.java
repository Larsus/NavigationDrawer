package com.github.larsus.nvgd.handler;

import com.github.larsus.nvgd.model.StateModel;

import java.util.List;

/**
 * @author Larsus
 * @version 1.0
 * @since 22.03.2015
 */
public interface OnStateItemClickListener<T extends StateModel> {
    void OnStateItemClick(T model, List<? extends StateModel> models);
}
