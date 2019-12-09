package com.xinghuofirst.kill.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PageableEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty(
        access = Access.WRITE_ONLY
    )
    private Integer pageNo;
    @JsonProperty(
        access = Access.WRITE_ONLY
    )
    private Integer pageSize;
    @JsonProperty(
        access = Access.WRITE_ONLY
    )
    private Integer start;
    @JsonProperty(
        access = Access.WRITE_ONLY
    )
    private Map<String, String> orders;
    @JsonProperty(
        access = Access.WRITE_ONLY
    )
    private Map<String, String> likes;
    @JsonProperty(
        access = Access.WRITE_ONLY
    )
    private Map<String, Map<String, Date>> dates;
    @JsonProperty(
        access = Access.WRITE_ONLY
    )
    private Map<String, List<Object>> includes;

    public Integer getStart() {
        if (this.pageNo != null && this.pageSize != null) {
            this.start = (this.pageNo - 1) * this.pageSize;
            return this.start;
        } else {
            return null;
        }
    }

    public Integer getPageNo() {
        return this.pageNo;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Map<String, String> getOrders() {
        return this.orders;
    }

    public Map<String, String> getLikes() {
        return this.likes;
    }

    public Map<String, Map<String, Date>> getDates() {
        return this.dates;
    }

    public Map<String, List<Object>> getIncludes() {
        return this.includes;
    }

    public PageableEntity setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public PageableEntity setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public PageableEntity setStart(Integer start) {
        this.start = start;
        return this;
    }

    public PageableEntity setOrders(Map<String, String> orders) {
        this.orders = orders;
        return this;
    }

    public PageableEntity setLikes(Map<String, String> likes) {
        this.likes = likes;
        return this;
    }

    public PageableEntity setDates(Map<String, Map<String, Date>> dates) {
        this.dates = dates;
        return this;
    }

    public PageableEntity setIncludes(Map<String, List<Object>> includes) {
        this.includes = includes;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageableEntity)) {
            return false;
        } else {
            PageableEntity other = (PageableEntity)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label95: {
                    Object this$pageNo = this.getPageNo();
                    Object other$pageNo = other.getPageNo();
                    if (this$pageNo == null) {
                        if (other$pageNo == null) {
                            break label95;
                        }
                    } else if (this$pageNo.equals(other$pageNo)) {
                        break label95;
                    }

                    return false;
                }

                Object this$pageSize = this.getPageSize();
                Object other$pageSize = other.getPageSize();
                if (this$pageSize == null) {
                    if (other$pageSize != null) {
                        return false;
                    }
                } else if (!this$pageSize.equals(other$pageSize)) {
                    return false;
                }

                Object this$start = this.getStart();
                Object other$start = other.getStart();
                if (this$start == null) {
                    if (other$start != null) {
                        return false;
                    }
                } else if (!this$start.equals(other$start)) {
                    return false;
                }

                label74: {
                    Object this$orders = this.getOrders();
                    Object other$orders = other.getOrders();
                    if (this$orders == null) {
                        if (other$orders == null) {
                            break label74;
                        }
                    } else if (this$orders.equals(other$orders)) {
                        break label74;
                    }

                    return false;
                }

                label67: {
                    Object this$likes = this.getLikes();
                    Object other$likes = other.getLikes();
                    if (this$likes == null) {
                        if (other$likes == null) {
                            break label67;
                        }
                    } else if (this$likes.equals(other$likes)) {
                        break label67;
                    }

                    return false;
                }

                Object this$dates = this.getDates();
                Object other$dates = other.getDates();
                if (this$dates == null) {
                    if (other$dates != null) {
                        return false;
                    }
                } else if (!this$dates.equals(other$dates)) {
                    return false;
                }

                Object this$includes = this.getIncludes();
                Object other$includes = other.getIncludes();
                if (this$includes == null) {
                    if (other$includes != null) {
                        return false;
                    }
                } else if (!this$includes.equals(other$includes)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof PageableEntity;
    }

    @Override
    public int hashCode() {
        int result = 1;
        Object $pageNo = this.getPageNo();
        result = result * 59 + ($pageNo == null ? 43 : $pageNo.hashCode());
        Object $pageSize = this.getPageSize();
        result = result * 59 + ($pageSize == null ? 43 : $pageSize.hashCode());
        Object $start = this.getStart();
        result = result * 59 + ($start == null ? 43 : $start.hashCode());
        Object $orders = this.getOrders();
        result = result * 59 + ($orders == null ? 43 : $orders.hashCode());
        Object $likes = this.getLikes();
        result = result * 59 + ($likes == null ? 43 : $likes.hashCode());
        Object $dates = this.getDates();
        result = result * 59 + ($dates == null ? 43 : $dates.hashCode());
        Object $includes = this.getIncludes();
        result = result * 59 + ($includes == null ? 43 : $includes.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "PageableEntity(pageNo=" + this.getPageNo() + ", pageSize=" + this.getPageSize() + ", start=" + this.getStart() + ", orders=" + this.getOrders() + ", likes=" + this.getLikes() + ", dates=" + this.getDates() + ", includes=" + this.getIncludes() + ")";
    }

    public PageableEntity() {
    }

    @ConstructorProperties({"pageNo", "pageSize", "start", "orders", "likes", "dates", "includes"})
    public PageableEntity(Integer pageNo, Integer pageSize, Integer start, Map<String, String> orders, Map<String, String> likes, Map<String, Map<String, Date>> dates, Map<String, List<Object>> includes) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.start = start;
        this.orders = orders;
        this.likes = likes;
        this.dates = dates;
        this.includes = includes;
    }
}
