package com.kele.pojo;

/**
 * @author 12402
 */
public class SearchInfo {

    private Integer size = 10,page = 1;
    private String custName,custSource,custIndustry,custLevel;

    @Override
    public String toString() {
        return "SearchInfo{" +
                "size=" + size +
                ", page=" + page +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                '}';
    }

    public SearchInfo() {
    }

    public SearchInfo(Integer size, Integer page, String custName, String custSource, String custIndustry, String custLevel) {
        this.size = size;
        this.page = page;
        this.custName = custName;
        this.custSource = custSource;
        this.custIndustry = custIndustry;
        this.custLevel = custLevel;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }
}
