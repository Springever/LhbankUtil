package com.springever.util.java.sort;

import java.util.*;

/**
 * 按树结构排序工具类
 */
public class TreeSortUtils {

    /**
     * 按树结构进行排序
     * @param list 要排序的列表
     * @param startWith 开始记录的id
     * @param <E> 节点类型
     * @return 排序后的列表
     */
    public static <E> List<E> sort4Tree(List<E> list, String startWith) {
        return sort4Tree(true, list, startWith);
    }

    /**
     * 按树结构进行排序
     *
     * @param addRoot 是否包含根节点
     * @param list 要排序的列表
     * @param startWith 开始记录的id
     * @param <E> 节点类型
     * @return 排序后的列表
     */
    @SuppressWarnings("unchecked")
    public static <E> List<E> sort4Tree(boolean addRoot, List<E> list, String startWith) {
        if(null == list || list.isEmpty()) {
            return new ArrayList<E>(0);
        }
        if(list.get(0) instanceof Map) {
            return sort4Tree(addRoot, list, startWith, (ITreeNodeAdapter<E>) MapTreeNodeAdapter.getDefaultInstance());
        } else {
            return sort4Tree(addRoot, list, startWith, (ITreeNodeAdapter<E>) ObjectTreeNodeAdapter.getDefaultInstance());
        }
    }

    /**
     * 按树结构进行排序
     * @param list 要排序的列表
     * @param startWith 开始记录的id
     * @param <E> 节点类型
     * @return 排序后的列表
     */
    public static <E> List<E> sort4Tree(List<E> list, String startWith, ITreeNodeAdapter<E> adapter) {
        return sort4Tree(true, list, startWith, adapter);
    }

    /**
     * 按树结构进行排序
     * @param addRoot 是否包含根节点
     * @param list 要排序的列表
     * @param startWith 开始记录的id
     * @param <E> 节点类型
     * @return 排序后的列表
     */
    public static <E> List<E> sort4Tree(boolean addRoot, List<E> list, String startWith, ITreeNodeAdapter<E> adapter) {
        if(null == list || list.isEmpty()) {
            return new ArrayList<E>(0);
        }
        final Map<String, List<String>> pid2IdsMap = new HashMap<String, List<String>>();   // 父-子列表
        final Map<String, Integer> id2IdxMap = new LinkedHashMap<String, Integer>();    // id-列表位置，并记录所有ID

        for (int i = 0; i < list.size(); i++) {
            E n = list.get(i);
            String id = adapter.getId(n);
            if(null == id) {
                continue;
            }
            id2IdxMap.put(id, i);
        }
        for (E n : list) {
            String id = adapter.getId(n);
            if(null == id) {
                continue;
            }
            String pid = adapter.getPid(n);
            if(null == pid || !id2IdxMap.containsKey(pid)) {   // 将父ID为null或不在列表中的值为空串处理
                pid = "";
            }
            if(id.equals(pid)) {    // 对于父为自己的，忽略处理，避免死循环
                continue;
            }
            List<String> ids = pid2IdsMap.get(pid);
            if(null == ids) {
                ids = new ArrayList<String>();
                pid2IdsMap.put(pid, ids);
            }
            ids.add(id);
        }
        final Set<String> hasAdd = new HashSet<String>();
        final Deque<String> deque = new LinkedList<String>();
        if(null == startWith) {
            startWith = "";
        }
        deque.add(startWith);
        final List<E> rsList = new ArrayList<E>();
        String cur;
        while (null != (cur = deque.pollFirst())) {
            if(hasAdd.contains(cur)) {
                continue;
            }
            Integer idx = id2IdxMap.get(cur);
            if (null != idx) {
                if(addRoot || !startWith.equals(cur)) {
                    rsList.add(list.get(idx));
                }
                hasAdd.add(cur);
            }
            List<String> ids = pid2IdsMap.get(cur);
            if(null != ids) {
                for (int i = ids.size() - 1; i >= 0; i--) {
                    deque.offerFirst(ids.get(i));
                }
            }
        }
        return rsList;
    }
}