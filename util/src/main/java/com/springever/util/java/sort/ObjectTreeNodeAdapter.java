package com.springever.util.java.sort;

import com.lhbank.commons.beanutils.PropertyUtils;

/**
 */
public class ObjectTreeNodeAdapter<E> implements ITreeNodeAdapter<E> {


    /**
     * id的key名称
     */
    private String idKey = "id";
    /**
     * pid的key名称
     */
    private String pidKey = "pid";

    public ObjectTreeNodeAdapter() {
    }

    public ObjectTreeNodeAdapter(String idKey, String pidKey) {
        this.idKey = idKey;
        this.pidKey = pidKey;
    }

    @Override
    public String getId(Object node) {
        try {
            return null == node ? null : PropertyUtils.getProperty(node, idKey) == null ? null : PropertyUtils.getProperty(node, idKey).toString();
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public String getPid(Object node) {
        try {
            return null == node ? null : PropertyUtils.getProperty(node, pidKey) == null ? null : PropertyUtils.getProperty(node, pidKey).toString();
        } catch (Exception e) {
        }
        return null;
    }

    private static final ITreeNodeAdapter<Object> DEFAULT = new ObjectTreeNodeAdapter<Object>();

    /**
     * 得到默认Object树节点适配器
     * @return 默认Map树节点适配器
     */
    public static ITreeNodeAdapter<Object> getDefaultInstance() {
        return DEFAULT;
    }
}
