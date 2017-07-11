package ua.com.smiddle.scm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import ua.com.smiddle.common.model.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Added by Andrii Osadchuk on 23.01.2017 at 16:58.
 * Project: SmiddleCampaignManager
 */
@Entity
@Table(name = "CM_RESULT_CODES",
        indexes = {@Index(name = "IDX_CM_RESULT_CODES_CODE", columnList = "CODE"),
                @Index(name = "IDX_CM_RESULT_CODES_DATA_TYPE", columnList = "DATA_TYPE")},
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_RESULTCODES_CAMPAIGNID_CODE", columnNames = {"CODE", "CAMPAIGN_ID"})})
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "eagerResultVariants",
                attributeNodes = {
                        @NamedAttributeNode("resultVariants")
                }
        )}
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "results"})
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
public class ResultCode extends BaseEntity {
    public static final long serialVersionUID = -1L;
    @Column(name = "CODE", nullable = false)
    private String code;
    @Column(name = "IS_FOR_PHONE", nullable = false)
    private boolean forPhone;
    @Column(name = "NAME", nullable = true)
    private String name;
    @Column(name = "COMENT", nullable = true)
    private String comment;
    @Column(name = "IS_CD_RES", nullable = false)
    private boolean cdRes;
    @Enumerated(EnumType.STRING)
    @Column(name = "DATA_TYPE", nullable = false)
    private DataType dataType;
    @Column(name = "IS_FOR_EXPORT", nullable = false)
    private boolean forExport;
    @Column(name = "IS_FOR_FILTER", nullable = false)
    private boolean forFilter;
    @Column(name = "FIELD_ORDER", nullable = false)
    private int fieldOrder;
    @Column(name = "IS_DELETED", nullable = false)
    private boolean deleted;
    @OneToMany(mappedBy = "resultCode", fetch = FetchType.LAZY)
    private Set<Result> results;
    @OneToMany(mappedBy = "resultCode", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    @OrderBy("forInit desc , value asc ")
    private Set<ResultVariant> resultVariants;
    @Column(name = "CAMPAIGN_ID", nullable = false)
    private long campaign;


    //Constructors
    public ResultCode() {
    }

    public ResultCode(Long id) {
        this.id = id;
    }

    public ResultCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    //Getters & setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isForPhone() {
        return forPhone;
    }

    public void setForPhone(boolean forPhone) {
        this.forPhone = forPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isCdRes() {
        return cdRes;
    }

    public void setCdRes(boolean cdRes) {
        this.cdRes = cdRes;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public boolean isForExport() {
        return forExport;
    }

    public void setForExport(boolean forExport) {
        this.forExport = forExport;
    }

    public boolean isForFilter() {
        return forFilter;
    }

    public void setForFilter(boolean forFilter) {
        this.forFilter = forFilter;
    }

    public int getFieldOrder() {
        return fieldOrder;
    }

    public void setFieldOrder(int order) {
        this.fieldOrder = order;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Set<Result> getResults() {
        return results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }

    public Set<ResultVariant> getResultVariants() {
        return resultVariants;
    }

    public void setResultVariants(Set<ResultVariant> resultVariants) {
        this.resultVariants = resultVariants;
    }

    public long getCampaign() {
        return campaign;
    }

    public void setCampaign(long campaign) {
        this.campaign = campaign;
    }


    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResultCode{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", forPhone=").append(forPhone);
        sb.append(", name='").append(name).append('\'');
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", cdRes=").append(cdRes);
        sb.append(", dataType=").append(dataType);
        sb.append(", forExport=").append(forExport);
        sb.append(", forFilter=").append(forFilter);
        sb.append(", fieldOrder=").append(fieldOrder);
        sb.append(", deleted=").append(deleted);
        sb.append('}');
        return sb.toString();
    }
}
