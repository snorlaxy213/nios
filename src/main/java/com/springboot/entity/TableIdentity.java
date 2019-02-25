package com.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="Table_Identity")
public class TableIdentity  implements Serializable {

    private static final long serialVersionUID = 2088076327213612639L;

    private String tableName;
    private String identityPrefix;
    private long nextIdentity;
    private int keyLength;

    public TableIdentity() {
    }

    @Id
    @Column(name = "Table_Name", length = 50)
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Column(name = "Identity_Prefix", nullable = true, length = 3)
    public String getIdentityPrefix() {
        return identityPrefix;
    }
    public void setIdentityPrefix(String identityPrefix) {
        this.identityPrefix = identityPrefix;
    }

    @Column(name = "Next_Identity", nullable = false, length = 3)
    public long getNextIdentity() {
        return nextIdentity;
    }
    public void setNextIdentity(long nextIdentity) {
        this.nextIdentity = nextIdentity;
    }

    @Column(name = "Key_Length", nullable = false, length = 3)
    public int getKeyLength() {
        return keyLength;
    }
    public void setKeyLength(int keyLength) {
        this.keyLength = keyLength;
    }

}
