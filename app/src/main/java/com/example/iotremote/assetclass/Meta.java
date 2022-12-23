package com.example.iotremote.assetclass;

import com.google.gson.annotations.SerializedName;

public class Meta {
    @SerializedName("readOnly")
    private boolean readOnly;
    @SerializedName("label")
    private String label;

    public boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean value) {
        this.readOnly = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String value) {
        this.label = value;
    }
}
