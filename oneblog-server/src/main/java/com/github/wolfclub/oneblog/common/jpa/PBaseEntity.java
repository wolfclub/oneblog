package com.github.wolfclub.oneblog.common.jpa;

import com.github.wolfclub.oneblog.commons.query.OrderSort;

import javax.persistence.Transient;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public class PBaseEntity extends PVersionedStandardEntity{
    private static final long serialVersionUID = 6409344126075050598L;

    @Transient
    private String keyword;
    @Transient
    private int pageNum = 1;
    @Transient
    private int pageSize = 10;

    @Transient
    private Map<String, Object> filter;
    @Transient
    private List<OrderSort> sorts;
    @Transient
    private List<String> fetchParts;

    public String getKeyword() {
        return keyword;
    }

    public PBaseEntity setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public int getPageNum() {
        return pageNum;
    }

    public PBaseEntity setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PBaseEntity setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Map<String, Object> getFilter() {
        return filter;
    }

    public PBaseEntity setFilter(Map<String, Object> filter) {
        this.filter = filter;
        return this;
    }

    public List<OrderSort> getSorts() {
        return sorts;
    }

    public PBaseEntity setSorts(List<OrderSort> sorts) {
        this.sorts = sorts;
        return this;
    }

    public List<String> getFetchParts() {
        return fetchParts;
    }

    public PBaseEntity setFetchParts(List<String> fetchParts) {
        this.fetchParts = fetchParts;
        return this;
    }

    /**
     * 返回按delimiter分隔的排序字符串
     * @param delimiter
     * @return 没有排序返回null
     */
    public String getSortString(String delimiter){
        if(this.sorts != null && this.sorts.size()>0){
            StringJoiner stringJoiner = new StringJoiner(delimiter);
            for(OrderSort orderSort: this.sorts){
                StringBuilder orderBy = new StringBuilder();
                if(OrderSort.ASC.equalsIgnoreCase(orderSort.getDirection())){
                    orderBy.append(orderSort.getProperty()).append(" ").append(OrderSort.ASC);
                }else{
                    orderBy.append(orderSort.getProperty()).append(" ").append(OrderSort.DESC);
                }
                stringJoiner.add(orderBy.toString());
            }
            String sortString = stringJoiner.toString();
            if(sortString.length()>0){
                return sortString;
            }
        }
        return null;
    }
}
