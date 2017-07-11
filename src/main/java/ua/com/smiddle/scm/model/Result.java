package ua.com.smiddle.scm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.DynamicInsert;
import ua.com.smiddle.common.model.BaseEntity;

import javax.persistence.*;

/**
 * Added by Andrii Osadchuk on 23.01.2017 at 17:12.
 * Project: SmiddleCampaignManager
 */
@Entity
@Table(name = "CM_RESULTS", indexes = @Index(name = "IDX_CM_RESULTS_LAST_RESULT", columnList = "LAST_RESULT"))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@DynamicInsert(value = true)
public class Result extends BaseEntity {
    public static final long serialVersionUID = -1L;
    @Column(name = "RESULT_PHONE", nullable = true)
    private String resultPhone;
    @Column(name = "RESULT_VALUE", nullable = false)
    private String resultValue;
    @Column(name = "RESULT_DATE", nullable = false)
    private long resultDate = System.currentTimeMillis();
    @Column(name = "LAST_RESULT", nullable = true)
    private int lastResult;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESULT_CODE_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_CM_RESULTS_CM_RESULT_CODES"))
    private ResultCode resultCode;


    //Constructors
    public Result() {
    }

    public Result(String resultValue) {
        this.resultValue = resultValue;
    }

    //Getters & setters
    public String getResultPhone() {
        return resultPhone;
    }

    public void setResultPhone(String resultPhone) {
        this.resultPhone = resultPhone;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public long getResultDate() {
        return resultDate;
    }

    public void setResultDate(long resultDate) {
        this.resultDate = resultDate;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }


    public int getLastResult() {
        return lastResult;
    }

    public void setLastResult(int lastResult) {
        this.lastResult = lastResult;
    }

    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("id=").append(id);
        sb.append(", resultPhone='").append(resultPhone).append('\'');
        sb.append(", resultValue='").append(resultValue).append('\'');
        sb.append(", lastResult=").append(lastResult);
        sb.append(", resultDate=").append(resultDate);
        //if (abonent != null) sb.append(", abonentId=").append(abonent.getClientId());
        sb.append('}');
        return sb.toString();
    }
}
