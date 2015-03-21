package com.github.larsus.nvgd.model;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public class ActionModel extends BaseModel {

    private int icon;
    private String title;
    private boolean selected = false;

    public ActionModel(int icon, String title) {
        super(ModelType.ACTION);
        this.icon = icon;
        this.title = title;
    }

    public final int getIcon() {
        return icon;
    }

    public final void setIcon(int icon) {
        this.icon = icon;
    }

    public final String getTitle() {
        return title;
    }

    public final void setTitle(String title) {
        this.title = title;
    }

    public final boolean isSelected() {
        return selected;
    }

    public final void select() {
        this.selected = true;
    }

    public final void deSelect() {
        this.selected = false;
    }
}
