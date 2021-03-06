package org.dbflute.erflute.editor.model.diagram_contents.not_element.sequence;

import java.math.BigDecimal;

import org.dbflute.erflute.editor.model.ObjectModel;
import org.dbflute.erflute.editor.model.WithSchemaModel;

/**
 * @author modified by jflute (originated in ermaster)
 */
public class Sequence extends WithSchemaModel implements ObjectModel {

    private static final long serialVersionUID = -4492787972500741281L;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private String description;
    private Integer increment;
    private Long minValue;
    private BigDecimal maxValue;
    private Long start;
    private Integer cache;
    private boolean cycle;
    private boolean order;
    private String dataType;
    private int decimalSize;

    // ===================================================================================
    //                                                                       Determination
    //                                                                       =============
    public boolean isEmpty() { // basically for persistent
        return isNullOrEmpty(description) && increment == null && minValue == null && maxValue == null // all settings
                && start == null && cache == null && !cycle && !order && isNullOrEmpty(dataType) && decimalSize == 0;
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    @Override
    public String getObjectType() {
        return "sequence";
    }

    public Integer getCache() {
        return cache;
    }

    public void setCache(Integer cache) {
        this.cache = cache;
    }

    public boolean isCycle() {
        return cycle;
    }

    public void setCycle(boolean cycle) {
        this.cycle = cycle;
    }

    public Integer getIncrement() {
        return increment;
    }

    public void setIncrement(Integer increment) {
        this.increment = increment;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    public Long getMinValue() {
        return minValue;
    }

    public void setMinValue(Long minValue) {
        this.minValue = minValue;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getDecimalSize() {
        return decimalSize;
    }

    public void setDecimalSize(int decimalSize) {
        this.decimalSize = decimalSize;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }
}
