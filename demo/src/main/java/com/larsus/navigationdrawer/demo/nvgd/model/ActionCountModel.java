package com.larsus.navigationdrawer.demo.nvgd.model;

import com.github.larsus.nvgd.model.ActionModel;

/**
 * @author Alexander Evdokimov (Larsus)
 * @version 1.0
 * @since 30.11.2015
 */
public final class ActionCountModel extends ActionModel {

    private int count;

    public ActionCountModel(int icon, String title, int count) {
        super(icon, title);
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
