package com.github.larsus.nvgd.model;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public class HeaderModel extends BaseModel {

    private String title;

    public HeaderModel(String title) {
        super(ModelType.HEADER);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}