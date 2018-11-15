package com.ss.rh.entity;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    public AuthenticationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("userId =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("userId <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("userId >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("userId >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("userId <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("userId <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("userId in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("userId not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("userId between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("userId not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNull() {
            addCriterion("userType is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("userType is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(Integer value) {
            addCriterion("userType =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(Integer value) {
            addCriterion("userType <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(Integer value) {
            addCriterion("userType >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("userType >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(Integer value) {
            addCriterion("userType <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(Integer value) {
            addCriterion("userType <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<Integer> values) {
            addCriterion("userType in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<Integer> values) {
            addCriterion("userType not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(Integer value1, Integer value2) {
            addCriterion("userType between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("userType not between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNull() {
            addCriterion("openId is null");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNotNull() {
            addCriterion("openId is not null");
            return (Criteria) this;
        }

        public Criteria andOpenIdEqualTo(String value) {
            addCriterion("openId =", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotEqualTo(String value) {
            addCriterion("openId <>", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThan(String value) {
            addCriterion("openId >", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("openId >=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThan(String value) {
            addCriterion("openId <", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThanOrEqualTo(String value) {
            addCriterion("openId <=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLike(String value) {
            addCriterion("openId like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotLike(String value) {
            addCriterion("openId not like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIn(List<String> values) {
            addCriterion("openId in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotIn(List<String> values) {
            addCriterion("openId not in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdBetween(String value1, String value2) {
            addCriterion("openId between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotBetween(String value1, String value2) {
            addCriterion("openId not between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIsNull() {
            addCriterion("accessToken is null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIsNotNull() {
            addCriterion("accessToken is not null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenEqualTo(String value) {
            addCriterion("accessToken =", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotEqualTo(String value) {
            addCriterion("accessToken <>", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenGreaterThan(String value) {
            addCriterion("accessToken >", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenGreaterThanOrEqualTo(String value) {
            addCriterion("accessToken >=", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLessThan(String value) {
            addCriterion("accessToken <", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLessThanOrEqualTo(String value) {
            addCriterion("accessToken <=", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLike(String value) {
            addCriterion("accessToken like", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotLike(String value) {
            addCriterion("accessToken not like", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIn(List<String> values) {
            addCriterion("accessToken in", values, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotIn(List<String> values) {
            addCriterion("accessToken not in", values, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenBetween(String value1, String value2) {
            addCriterion("accessToken between", value1, value2, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotBetween(String value1, String value2) {
            addCriterion("accessToken not between", value1, value2, "accessToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenIsNull() {
            addCriterion("refreshToken is null");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenIsNotNull() {
            addCriterion("refreshToken is not null");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenEqualTo(String value) {
            addCriterion("refreshToken =", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenNotEqualTo(String value) {
            addCriterion("refreshToken <>", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenGreaterThan(String value) {
            addCriterion("refreshToken >", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenGreaterThanOrEqualTo(String value) {
            addCriterion("refreshToken >=", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenLessThan(String value) {
            addCriterion("refreshToken <", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenLessThanOrEqualTo(String value) {
            addCriterion("refreshToken <=", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenLike(String value) {
            addCriterion("refreshToken like", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenNotLike(String value) {
            addCriterion("refreshToken not like", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenIn(List<String> values) {
            addCriterion("refreshToken in", values, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenNotIn(List<String> values) {
            addCriterion("refreshToken not in", values, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenBetween(String value1, String value2) {
            addCriterion("refreshToken between", value1, value2, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenNotBetween(String value1, String value2) {
            addCriterion("refreshToken not between", value1, value2, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeIsNull() {
            addCriterion("expiryTime is null");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeIsNotNull() {
            addCriterion("expiryTime is not null");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeEqualTo(String value) {
            addCriterion("expiryTime =", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeNotEqualTo(String value) {
            addCriterion("expiryTime <>", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeGreaterThan(String value) {
            addCriterion("expiryTime >", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeGreaterThanOrEqualTo(String value) {
            addCriterion("expiryTime >=", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeLessThan(String value) {
            addCriterion("expiryTime <", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeLessThanOrEqualTo(String value) {
            addCriterion("expiryTime <=", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeLike(String value) {
            addCriterion("expiryTime like", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeNotLike(String value) {
            addCriterion("expiryTime not like", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeIn(List<String> values) {
            addCriterion("expiryTime in", values, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeNotIn(List<String> values) {
            addCriterion("expiryTime not in", values, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeBetween(String value1, String value2) {
            addCriterion("expiryTime between", value1, value2, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeNotBetween(String value1, String value2) {
            addCriterion("expiryTime not between", value1, value2, "expiryTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table authentication
     *
     * @mbggenerated do_not_delete_during_merge Mon Nov 12 15:17:07 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table authentication
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
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