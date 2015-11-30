package com.github.larsus.nvgd.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Evdokimov (Larsus)
 * @version 1.0
 * @since 30.11.2015
 */
public class ModelAdapterItems {

    private final List<? super ModelAdapterItem> modelAdapterItems;

    public ModelAdapterItems() {
        modelAdapterItems = new ArrayList<>();
    }

    public <TModelAdapterItem extends ModelAdapterItem> ModelAdapterItems add(TModelAdapterItem modelAdapterItem) {
        modelAdapterItems.add(modelAdapterItem);
        return this;
    }

    public List<? super ModelAdapterItem> getModelAdapterItems() {
        return modelAdapterItems;
    }
}
