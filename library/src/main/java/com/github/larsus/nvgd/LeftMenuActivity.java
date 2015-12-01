package com.github.larsus.nvgd;

import android.os.Bundle;

import com.github.larsus.nvgd.activity.BaseActivity;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public abstract class LeftMenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected final SaveItemPositionState getSaveItemPositionState() {
        return SaveItemPositionState.DO_NOT_SAVE;
    }
}
