package com.github.larsus.nvgd.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.larsus.nvgd.R;
import com.github.larsus.nvgd.adapter.ActionModelAdapterItem;
import com.github.larsus.nvgd.adapter.ModelAdapter;
import com.github.larsus.nvgd.adapter.ModelAdapterItem;
import com.github.larsus.nvgd.viewholder.DefaultViewHolderBuilder;
import com.github.larsus.nvgd.viewholder.ViewHolderBuilder;

import java.util.List;

/**
 * @author Larsus
 * @version 1.0
 * @since 19.03.2015
 */
public abstract class BaseActivity extends ActionBarActivity {

    private static final String CURRENT_ITEM_POSITION = "nvgd.current.item.position";

    private DrawerLayout mDrawerLayout;

    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;
    private ModelAdapter mModelAdapter;

    private int mCurrentItemPosition = 0;

    protected void onCreate(Bundle savedInstanceState, boolean savePosition) {
        super.onCreate(savedInstanceState);

        setContentView(getActivityLayoutResourceId());

        mDrawerLayout = (DrawerLayout) findViewById(getDrawerLayoutResourceId());
        mDrawerList = (ListView) findViewById(getDrawerListResourceId());
        mDrawerList.setOnItemClickListener(new ActionItemClickListener());

        loadCurrentPosition(savedInstanceState);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        mModelAdapter = new ModelAdapter(this, getViewHolderBuilder(), getModelAdapterItems(), savePosition);
        mModelAdapter.selectItem(mCurrentItemPosition);

        mDrawerList.setAdapter(mModelAdapter);
        mDrawerList.setOnItemClickListener(new ActionItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.nvgd_drawer_open, R.string.nvgd_drawer_close){
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
                invalidateOptionsMenu();
                syncState();
            }
            public void onDrawerOpened(View v){
                super.onDrawerOpened(v);
                invalidateOptionsMenu();
                syncState();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    protected abstract int getActivityLayoutResourceId();

    protected int getDrawerLayoutResourceId() {
        return R.id.nvgd_drawer;
    }

    protected int getDrawerListResourceId() {
        return R.id.nvgd_drawer_items_list;
    }

    private void loadCurrentPosition(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            setCurrentPosition(savedInstanceState.getInt(CURRENT_ITEM_POSITION));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_ITEM_POSITION, mCurrentItemPosition);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: {
                if (mDrawerLayout.isDrawerOpen(mDrawerList)){
                    mDrawerLayout.closeDrawer(mDrawerList);
                } else {
                    mDrawerLayout.openDrawer(mDrawerList);
                }
                return true;
            }
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        if (drawerOpen) {
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            super.onBackPressed();
        }
    }

    public void setDefaultPosition(int position){
        this.mCurrentItemPosition = position;
    }

    private void setCurrentPosition(int position){
        this.mCurrentItemPosition = position;
    }

    protected abstract List<? super ModelAdapterItem> getModelAdapterItems();

    protected ViewHolderBuilder getViewHolderBuilder() {
        return new DefaultViewHolderBuilder();
    }

    private class ActionItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (mModelAdapter == null || !(mModelAdapter.getItem(position) instanceof ActionModelAdapterItem)) return;

            ActionModelAdapterItem actionModelAdapterItem = (ActionModelAdapterItem) mModelAdapter.getItem(position);
            actionModelAdapterItem.onActionItemClick(mModelAdapter);

            if (mCurrentItemPosition != position && mModelAdapter.selectItem(position)) {
                setCurrentPosition(position);
            }

            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }
}
