package com.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="Table_Identity")
public class TableIdentity  implements Serializable {

    private static final long serialVersionUID = -6768369215687169543L;

    private String tableName;
    private String identityPrefix;
    private long nextIdentity;
    private String checkDigit;
    private int keyLength;

    @Id
    @Column(name = "Table_Name", length = 50)
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Column(name = "Identity_Prefix", nullable = true, length = 2)
    public String getIdentityPrefix() {
        return identityPrefix;
    }
    public void setIdentityPrefix(String identityPrefix) {
        this.identityPrefix = identityPrefix;
    }

    @Column(name = "Next_Identity", nullable = false)
    public long getNextIdentity() {
        return nextIdentity;
    }
    public void setNextIdentity(long nextIdentity) {
        this.nextIdentity = nextIdentity;
    }

    @Column(name = "Check_Digit", nullable = false, length = 1)
    public String getCheckDigit() {
        return checkDigit;
    }
    public void setCheckDigit(String checkDigit) {
        this.checkDigit = checkDigit;
    }

    @Column(name = "Key_Length", nullable = false)
    public int getKeyLength() {
        return keyLength;
    }
    public void setKeyLength(int keyLength) {
        this.keyLength = keyLength;
    }

}
