package com.github.larsus.nvgd.model;

/**
 * @author Larsus
 * @version 1.0
 * @since 21.03.2015
 */
public class StateModel extends BaseModel {

    private boolean state;
    private String title;

    public StateModel(boolean state, String title) {
        super(ModelType.STATE);
        this.state = state;
        this.title = title;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
