package ua.com.smiddle.scm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import ua.com.smiddle.common.model.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Added by Andrii Osadchuk on 23.01.2017 at 17:04.
 * Project: SmiddleCampaignManager
 */
@Entity
@Table(name = "CM_RESULT_VARIANTS")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
public class ResultVariant extends BaseEntity {
    public static final long serialVersionUID = -1L;
    @Column(name = "VALUE", nullable = false)
    private String value;
    @Column(name = "CODE", nullable = true, updatable = false)
    private String code;
    @Column(name = "IS_FOR_INIT", nullable = false)
    private boolean forInit;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESULT_CODE_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_CM_RES_VARIANT_CM_RES_CODE"))
    private ResultCode resultCode;


    //Constructors
    public ResultVariant() {
    }

    public ResultVariant(String value, String code, boolean forInit, ResultCode resultCode) {
        this.value = value;
        this.code = code;
        this.forInit = forInit;
        this.resultCode = resultCode;
    }


    //Getters & setters
    public boolean isForInit() {
        return forInit;
    }

    public void setForInit(boolean forInit) {
        this.forInit = forInit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResultVariant{");
        sb.append("id=").append(id);
        sb.append(", forInit=").append(forInit);
        sb.append(", value='").append(value).append('\'');
        sb.append(code != null ? (", code='" + code + '\'') : "");
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ResultVariant that = (ResultVariant) o;
        return forInit == that.forInit &&
                Objects.equals(value, that.value) &&
                Objects.equals(code, that.code) &&
                Objects.equals(resultCode, that.resultCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value, code, forInit, resultCode);
    }
}
