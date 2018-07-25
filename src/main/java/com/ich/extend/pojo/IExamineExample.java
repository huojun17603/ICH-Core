package com.ich.extend.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IExamineExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IExamineExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andHandleresultIsNull() {
            addCriterion("handleresult is null");
            return (Criteria) this;
        }

        public Criteria andHandleresultIsNotNull() {
            addCriterion("handleresult is not null");
            return (Criteria) this;
        }

        public Criteria andHandleresultEqualTo(Integer value) {
            addCriterion("handleresult =", value, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultNotEqualTo(Integer value) {
            addCriterion("handleresult <>", value, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultGreaterThan(Integer value) {
            addCriterion("handleresult >", value, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultGreaterThanOrEqualTo(Integer value) {
            addCriterion("handleresult >=", value, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultLessThan(Integer value) {
            addCriterion("handleresult <", value, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultLessThanOrEqualTo(Integer value) {
            addCriterion("handleresult <=", value, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultIn(List<Integer> values) {
            addCriterion("handleresult in", values, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultNotIn(List<Integer> values) {
            addCriterion("handleresult not in", values, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultBetween(Integer value1, Integer value2) {
            addCriterion("handleresult between", value1, value2, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultNotBetween(Integer value1, Integer value2) {
            addCriterion("handleresult not between", value1, value2, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandletimeIsNull() {
            addCriterion("handletime is null");
            return (Criteria) this;
        }

        public Criteria andHandletimeIsNotNull() {
            addCriterion("handletime is not null");
            return (Criteria) this;
        }

        public Criteria andHandletimeEqualTo(Date value) {
            addCriterion("handletime =", value, "handletime");
            return (Criteria) this;
        }

        public Criteria andHandletimeNotEqualTo(Date value) {
            addCriterion("handletime <>", value, "handletime");
            return (Criteria) this;
        }

        public Criteria andHandletimeGreaterThan(Date value) {
            addCriterion("handletime >", value, "handletime");
            return (Criteria) this;
        }

        public Criteria andHandletimeGreaterThanOrEqualTo(Date value) {
            addCriterion("handletime >=", value, "handletime");
            return (Criteria) this;
        }

        public Criteria andHandletimeLessThan(Date value) {
            addCriterion("handletime <", value, "handletime");
            return (Criteria) this;
        }

        public Criteria andHandletimeLessThanOrEqualTo(Date value) {
            addCriterion("handletime <=", value, "handletime");
            return (Criteria) this;
        }

        public Criteria andHandletimeIn(List<Date> values) {
            addCriterion("handletime in", values, "handletime");
            return (Criteria) this;
        }

        public Criteria andHandletimeNotIn(List<Date> values) {
            addCriterion("handletime not in", values, "handletime");
            return (Criteria) this;
        }

        public Criteria andHandletimeBetween(Date value1, Date value2) {
            addCriterion("handletime between", value1, value2, "handletime");
            return (Criteria) this;
        }

        public Criteria andHandletimeNotBetween(Date value1, Date value2) {
            addCriterion("handletime not between", value1, value2, "handletime");
            return (Criteria) this;
        }

        public Criteria andHandlernameIsNull() {
            addCriterion("handlername is null");
            return (Criteria) this;
        }

        public Criteria andHandlernameIsNotNull() {
            addCriterion("handlername is not null");
            return (Criteria) this;
        }

        public Criteria andHandlernameEqualTo(String value) {
            addCriterion("handlername =", value, "handlername");
            return (Criteria) this;
        }

        public Criteria andHandlernameNotEqualTo(String value) {
            addCriterion("handlername <>", value, "handlername");
            return (Criteria) this;
        }

        public Criteria andHandlernameGreaterThan(String value) {
            addCriterion("handlername >", value, "handlername");
            return (Criteria) this;
        }

        public Criteria andHandlernameGreaterThanOrEqualTo(String value) {
            addCriterion("handlername >=", value, "handlername");
            return (Criteria) this;
        }

        public Criteria andHandlernameLessThan(String value) {
            addCriterion("handlername <", value, "handlername");
            return (Criteria) this;
        }

        public Criteria andHandlernameLessThanOrEqualTo(String value) {
            addCriterion("handlername <=", value, "handlername");
            return (Criteria) this;
        }

        public Criteria andHandlernameLike(String value) {
            addCriterion("handlername like", value, "handlername");
            return (Criteria) this;
        }

        public Criteria andHandlernameNotLike(String value) {
            addCriterion("handlername not like", value, "handlername");
            return (Criteria) this;
        }

        public Criteria andHandlernameIn(List<String> values) {
            addCriterion("handlername in", values, "handlername");
            return (Criteria) this;
        }

        public Criteria andHandlernameNotIn(List<String> values) {
            addCriterion("handlername not in", values, "handlername");
            return (Criteria) this;
        }

        public Criteria andHandlernameBetween(String value1, String value2) {
            addCriterion("handlername between", value1, value2, "handlername");
            return (Criteria) this;
        }

        public Criteria andHandlernameNotBetween(String value1, String value2) {
            addCriterion("handlername not between", value1, value2, "handlername");
            return (Criteria) this;
        }

        public Criteria andHandlermarkIsNull() {
            addCriterion("handlermark is null");
            return (Criteria) this;
        }

        public Criteria andHandlermarkIsNotNull() {
            addCriterion("handlermark is not null");
            return (Criteria) this;
        }

        public Criteria andHandlermarkEqualTo(String value) {
            addCriterion("handlermark =", value, "handlermark");
            return (Criteria) this;
        }

        public Criteria andHandlermarkNotEqualTo(String value) {
            addCriterion("handlermark <>", value, "handlermark");
            return (Criteria) this;
        }

        public Criteria andHandlermarkGreaterThan(String value) {
            addCriterion("handlermark >", value, "handlermark");
            return (Criteria) this;
        }

        public Criteria andHandlermarkGreaterThanOrEqualTo(String value) {
            addCriterion("handlermark >=", value, "handlermark");
            return (Criteria) this;
        }

        public Criteria andHandlermarkLessThan(String value) {
            addCriterion("handlermark <", value, "handlermark");
            return (Criteria) this;
        }

        public Criteria andHandlermarkLessThanOrEqualTo(String value) {
            addCriterion("handlermark <=", value, "handlermark");
            return (Criteria) this;
        }

        public Criteria andHandlermarkLike(String value) {
            addCriterion("handlermark like", value, "handlermark");
            return (Criteria) this;
        }

        public Criteria andHandlermarkNotLike(String value) {
            addCriterion("handlermark not like", value, "handlermark");
            return (Criteria) this;
        }

        public Criteria andHandlermarkIn(List<String> values) {
            addCriterion("handlermark in", values, "handlermark");
            return (Criteria) this;
        }

        public Criteria andHandlermarkNotIn(List<String> values) {
            addCriterion("handlermark not in", values, "handlermark");
            return (Criteria) this;
        }

        public Criteria andHandlermarkBetween(String value1, String value2) {
            addCriterion("handlermark between", value1, value2, "handlermark");
            return (Criteria) this;
        }

        public Criteria andHandlermarkNotBetween(String value1, String value2) {
            addCriterion("handlermark not between", value1, value2, "handlermark");
            return (Criteria) this;
        }

        public Criteria andSourcegroupIsNull() {
            addCriterion("sourcegroup is null");
            return (Criteria) this;
        }

        public Criteria andSourcegroupIsNotNull() {
            addCriterion("sourcegroup is not null");
            return (Criteria) this;
        }

        public Criteria andSourcegroupEqualTo(Integer value) {
            addCriterion("sourcegroup =", value, "sourcegroup");
            return (Criteria) this;
        }

        public Criteria andSourcegroupNotEqualTo(Integer value) {
            addCriterion("sourcegroup <>", value, "sourcegroup");
            return (Criteria) this;
        }

        public Criteria andSourcegroupGreaterThan(Integer value) {
            addCriterion("sourcegroup >", value, "sourcegroup");
            return (Criteria) this;
        }

        public Criteria andSourcegroupGreaterThanOrEqualTo(Integer value) {
            addCriterion("sourcegroup >=", value, "sourcegroup");
            return (Criteria) this;
        }

        public Criteria andSourcegroupLessThan(Integer value) {
            addCriterion("sourcegroup <", value, "sourcegroup");
            return (Criteria) this;
        }

        public Criteria andSourcegroupLessThanOrEqualTo(Integer value) {
            addCriterion("sourcegroup <=", value, "sourcegroup");
            return (Criteria) this;
        }

        public Criteria andSourcegroupIn(List<Integer> values) {
            addCriterion("sourcegroup in", values, "sourcegroup");
            return (Criteria) this;
        }

        public Criteria andSourcegroupNotIn(List<Integer> values) {
            addCriterion("sourcegroup not in", values, "sourcegroup");
            return (Criteria) this;
        }

        public Criteria andSourcegroupBetween(Integer value1, Integer value2) {
            addCriterion("sourcegroup between", value1, value2, "sourcegroup");
            return (Criteria) this;
        }

        public Criteria andSourcegroupNotBetween(Integer value1, Integer value2) {
            addCriterion("sourcegroup not between", value1, value2, "sourcegroup");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(Integer value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(Integer value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(Integer value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(Integer value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(Integer value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<Integer> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<Integer> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(Integer value1, Integer value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(Integer value1, Integer value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceidIsNull() {
            addCriterion("sourceid is null");
            return (Criteria) this;
        }

        public Criteria andSourceidIsNotNull() {
            addCriterion("sourceid is not null");
            return (Criteria) this;
        }

        public Criteria andSourceidEqualTo(String value) {
            addCriterion("sourceid =", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidNotEqualTo(String value) {
            addCriterion("sourceid <>", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidGreaterThan(String value) {
            addCriterion("sourceid >", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidGreaterThanOrEqualTo(String value) {
            addCriterion("sourceid >=", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidLessThan(String value) {
            addCriterion("sourceid <", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidLessThanOrEqualTo(String value) {
            addCriterion("sourceid <=", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidLike(String value) {
            addCriterion("sourceid like", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidNotLike(String value) {
            addCriterion("sourceid not like", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidIn(List<String> values) {
            addCriterion("sourceid in", values, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidNotIn(List<String> values) {
            addCriterion("sourceid not in", values, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidBetween(String value1, String value2) {
            addCriterion("sourceid between", value1, value2, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidNotBetween(String value1, String value2) {
            addCriterion("sourceid not between", value1, value2, "sourceid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}