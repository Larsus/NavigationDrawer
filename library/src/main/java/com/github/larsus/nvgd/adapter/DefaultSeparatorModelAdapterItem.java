package com.github.larsus.nvgd.adapter;

import com.github.larsus.nvgd.R;
import com.github.larsus.nvgd.model.SeparatorModel;
import com.github.larsus.nvgd.viewholder.DefaultSeparatorViewHolder;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public final class DefaultSeparatorModelAdapterItem extends ModelAdapterItem<SeparatorModel> {
    public DefaultSeparatorModelAdapterItem() {
        super(new SeparatorModel(), DefaultSeparatorViewHolder.class, R.layout.nvgd_drawer_list_item_separator);
    }
}
