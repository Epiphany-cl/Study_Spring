public class Clazz {
    private Integer clazzId;
    private String className;


    public Clazz() {
    }

    public Clazz(Integer clazzId, String className) {
        this.clazzId = clazzId;
        this.className = className;
    }

    /**
     * 获取
     *
     * @return clazzId
     */
    public Integer getClazzId() {
        return clazzId;
    }

    /**
     * 设置
     *
     * @param clazzId
     */
    public void setClazzId(Integer clazzId) {
        this.clazzId = clazzId;
    }

    /**
     * 获取
     *
     * @return className
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置
     *
     * @param className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    public String toString() {
        return "Clazz{clazzId = " + clazzId + ", className = " + className + "}";
    }
}
