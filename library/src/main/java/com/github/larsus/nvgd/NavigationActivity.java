package com.github.larsus.nvgd;

import android.os.Bundle;

import com.github.larsus.nvgd.activity.BaseActivity;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public abstract class NavigationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, true);
    }
}
