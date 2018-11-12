package com.ss.rh.entity;

import java.util.ArrayList;
import java.util.List;

public class OrderExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    public OrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order
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
     * This method corresponds to the database table order
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
     * This method corresponds to the database table order
     *
     * @mbggenerated Mon Nov 12 15:17:07 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order
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
     * This class corresponds to the database table order
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

        public Criteria andRepairIdIsNull() {
            addCriterion("repairId is null");
            return (Criteria) this;
        }

        public Criteria andRepairIdIsNotNull() {
            addCriterion("repairId is not null");
            return (Criteria) this;
        }

        public Criteria andRepairIdEqualTo(Integer value) {
            addCriterion("repairId =", value, "repairId");
            return (Criteria) this;
        }

        public Criteria andRepairIdNotEqualTo(Integer value) {
            addCriterion("repairId <>", value, "repairId");
            return (Criteria) this;
        }

        public Criteria andRepairIdGreaterThan(Integer value) {
            addCriterion("repairId >", value, "repairId");
            return (Criteria) this;
        }

        public Criteria andRepairIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("repairId >=", value, "repairId");
            return (Criteria) this;
        }

        public Criteria andRepairIdLessThan(Integer value) {
            addCriterion("repairId <", value, "repairId");
            return (Criteria) this;
        }

        public Criteria andRepairIdLessThanOrEqualTo(Integer value) {
            addCriterion("repairId <=", value, "repairId");
            return (Criteria) this;
        }

        public Criteria andRepairIdIn(List<Integer> values) {
            addCriterion("repairId in", values, "repairId");
            return (Criteria) this;
        }

        public Criteria andRepairIdNotIn(List<Integer> values) {
            addCriterion("repairId not in", values, "repairId");
            return (Criteria) this;
        }

        public Criteria andRepairIdBetween(Integer value1, Integer value2) {
            addCriterion("repairId between", value1, value2, "repairId");
            return (Criteria) this;
        }

        public Criteria andRepairIdNotBetween(Integer value1, Integer value2) {
            addCriterion("repairId not between", value1, value2, "repairId");
            return (Criteria) this;
        }

        public Criteria andEquipInfoIsNull() {
            addCriterion("equipInfo is null");
            return (Criteria) this;
        }

        public Criteria andEquipInfoIsNotNull() {
            addCriterion("equipInfo is not null");
            return (Criteria) this;
        }

        public Criteria andEquipInfoEqualTo(String value) {
            addCriterion("equipInfo =", value, "equipInfo");
            return (Criteria) this;
        }

        public Criteria andEquipInfoNotEqualTo(String value) {
            addCriterion("equipInfo <>", value, "equipInfo");
            return (Criteria) this;
        }

        public Criteria andEquipInfoGreaterThan(String value) {
            addCriterion("equipInfo >", value, "equipInfo");
            return (Criteria) this;
        }

        public Criteria andEquipInfoGreaterThanOrEqualTo(String value) {
            addCriterion("equipInfo >=", value, "equipInfo");
            return (Criteria) this;
        }

        public Criteria andEquipInfoLessThan(String value) {
            addCriterion("equipInfo <", value, "equipInfo");
            return (Criteria) this;
        }

        public Criteria andEquipInfoLessThanOrEqualTo(String value) {
            addCriterion("equipInfo <=", value, "equipInfo");
            return (Criteria) this;
        }

        public Criteria andEquipInfoLike(String value) {
            addCriterion("equipInfo like", value, "equipInfo");
            return (Criteria) this;
        }

        public Criteria andEquipInfoNotLike(String value) {
            addCriterion("equipInfo not like", value, "equipInfo");
            return (Criteria) this;
        }

        public Criteria andEquipInfoIn(List<String> values) {
            addCriterion("equipInfo in", values, "equipInfo");
            return (Criteria) this;
        }

        public Criteria andEquipInfoNotIn(List<String> values) {
            addCriterion("equipInfo not in", values, "equipInfo");
            return (Criteria) this;
        }

        public Criteria andEquipInfoBetween(String value1, String value2) {
            addCriterion("equipInfo between", value1, value2, "equipInfo");
            return (Criteria) this;
        }

        public Criteria andEquipInfoNotBetween(String value1, String value2) {
            addCriterion("equipInfo not between", value1, value2, "equipInfo");
            return (Criteria) this;
        }

        public Criteria andFaultInfoIsNull() {
            addCriterion("faultInfo is null");
            return (Criteria) this;
        }

        public Criteria andFaultInfoIsNotNull() {
            addCriterion("faultInfo is not null");
            return (Criteria) this;
        }

        public Criteria andFaultInfoEqualTo(String value) {
            addCriterion("faultInfo =", value, "faultInfo");
            return (Criteria) this;
        }

        public Criteria andFaultInfoNotEqualTo(String value) {
            addCriterion("faultInfo <>", value, "faultInfo");
            return (Criteria) this;
        }

        public Criteria andFaultInfoGreaterThan(String value) {
            addCriterion("faultInfo >", value, "faultInfo");
            return (Criteria) this;
        }

        public Criteria andFaultInfoGreaterThanOrEqualTo(String value) {
            addCriterion("faultInfo >=", value, "faultInfo");
            return (Criteria) this;
        }

        public Criteria andFaultInfoLessThan(String value) {
            addCriterion("faultInfo <", value, "faultInfo");
            return (Criteria) this;
        }

        public Criteria andFaultInfoLessThanOrEqualTo(String value) {
            addCriterion("faultInfo <=", value, "faultInfo");
            return (Criteria) this;
        }

        public Criteria andFaultInfoLike(String value) {
            addCriterion("faultInfo like", value, "faultInfo");
            return (Criteria) this;
        }

        public Criteria andFaultInfoNotLike(String value) {
            addCriterion("faultInfo not like", value, "faultInfo");
            return (Criteria) this;
        }

        public Criteria andFaultInfoIn(List<String> values) {
            addCriterion("faultInfo in", values, "faultInfo");
            return (Criteria) this;
        }

        public Criteria andFaultInfoNotIn(List<String> values) {
            addCriterion("faultInfo not in", values, "faultInfo");
            return (Criteria) this;
        }

        public Criteria andFaultInfoBetween(String value1, String value2) {
            addCriterion("faultInfo between", value1, value2, "faultInfo");
            return (Criteria) this;
        }

        public Criteria andFaultInfoNotBetween(String value1, String value2) {
            addCriterion("faultInfo not between", value1, value2, "faultInfo");
            return (Criteria) this;
        }

        public Criteria andSoundIsNull() {
            addCriterion("sound is null");
            return (Criteria) this;
        }

        public Criteria andSoundIsNotNull() {
            addCriterion("sound is not null");
            return (Criteria) this;
        }

        public Criteria andSoundEqualTo(String value) {
            addCriterion("sound =", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundNotEqualTo(String value) {
            addCriterion("sound <>", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundGreaterThan(String value) {
            addCriterion("sound >", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundGreaterThanOrEqualTo(String value) {
            addCriterion("sound >=", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundLessThan(String value) {
            addCriterion("sound <", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundLessThanOrEqualTo(String value) {
            addCriterion("sound <=", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundLike(String value) {
            addCriterion("sound like", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundNotLike(String value) {
            addCriterion("sound not like", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundIn(List<String> values) {
            addCriterion("sound in", values, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundNotIn(List<String> values) {
            addCriterion("sound not in", values, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundBetween(String value1, String value2) {
            addCriterion("sound between", value1, value2, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundNotBetween(String value1, String value2) {
            addCriterion("sound not between", value1, value2, "sound");
            return (Criteria) this;
        }

        public Criteria andImgIsNull() {
            addCriterion("img is null");
            return (Criteria) this;
        }

        public Criteria andImgIsNotNull() {
            addCriterion("img is not null");
            return (Criteria) this;
        }

        public Criteria andImgEqualTo(String value) {
            addCriterion("img =", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotEqualTo(String value) {
            addCriterion("img <>", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThan(String value) {
            addCriterion("img >", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThanOrEqualTo(String value) {
            addCriterion("img >=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThan(String value) {
            addCriterion("img <", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThanOrEqualTo(String value) {
            addCriterion("img <=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLike(String value) {
            addCriterion("img like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotLike(String value) {
            addCriterion("img not like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgIn(List<String> values) {
            addCriterion("img in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotIn(List<String> values) {
            addCriterion("img not in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgBetween(String value1, String value2) {
            addCriterion("img between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotBetween(String value1, String value2) {
            addCriterion("img not between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table order
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
     * This class corresponds to the database table order
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