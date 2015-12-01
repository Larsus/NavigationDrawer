package com.github.larsus.nvgd.handler;

import com.github.larsus.nvgd.model.StateModel;

import java.util.List;

/**
 * @author Larsus
 * @version 1.0
 * @since 22.03.2015
 */
public interface OnStateItemClickListener<TModel extends StateModel> {
    void OnStateItemClick(TModel model, List<? extends StateModel> models);
}
