package com.lazyfish.codeshare.entity;

public class UserApiTableItem {
    private int id;
    private int tableId;
    private String tableItemName;
    private String realItemName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getTableItemName() {
        return tableItemName;
    }

    public void setTableItemName(String tableItemName) {
        this.tableItemName = tableItemName;
    }

    public String getRealItemName() {
        return realItemName;
    }

    public void setRealItemName(String realItemName) {
        this.realItemName = realItemName;
    }
}
