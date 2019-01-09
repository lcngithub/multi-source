package com.bsoft.entity;

public class MappingRelation {
    private Long id;

    private String url;

    private String dataSource;

    private String method;

    private String dateFormat;

    private String remake;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake == null ? null : remake.trim();
    }

    @Override
    public String toString() {
        return "MappingRelation{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", dataSource='" + dataSource + '\'' +
                ", method='" + method + '\'' +
                ", dateFormat='" + dateFormat + '\'' +
                ", remake='" + remake + '\'' +
                '}';
    }
}