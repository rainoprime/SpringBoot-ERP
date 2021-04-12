package com.liujinshui.common.utils;





import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {

    /**
     * 构建菜单树节点的层级关系
     * @param treeNodes     菜单集合
     * @param rootId        根节点编号1
     * @return
     */
    public static List<TreeNode> build(List<TreeNode> treeNodes,int rootId){
        //创建集合保存层级关系
        List<TreeNode> nodes = new ArrayList<>();
        //循环
        for (TreeNode n1 : treeNodes) {
            if(n1 .getPid()  == rootId){
                nodes.add(n1);
            }
            //如果当前节点对应的节点相等，则添加到子节点集合中
            for (TreeNode n2 : treeNodes) {
                if(n1.getId() == n2.getPid()){
                    n1.getChildren().add(n2);
                }
            }
        }
        return nodes;

    }

}
