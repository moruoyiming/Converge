package com.algorithm.demo.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的所有路径 · Binary Tree Paths
 * 宽度优先搜索
 * 深度优先搜索/回溯法
 * 分治法
 * 脸书
 * 苹果
 * 谷歌
 * 二叉树遍历
 * 二叉树
 * 描述
 * 给一棵二叉树，找出从根节点到叶子节点的所有路径。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入：{1,2,3,#,5}
 * 输出：["1->2->5","1->3"]
 * 解释：
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * 样例 2:
 * <p>
 * 输入：{1,2}
 * 输出：["1->2"]
 * 解释：
 * 1
 * /
 * 2
 */
public class 二叉树的所有路径 {

    /**
     * @param root the root of the binary tree
     * @return all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        List<String> leftPaths = binaryTreePaths(root.left);
        List<String> rightPaths = binaryTreePaths(root.right);
        for (String path : leftPaths) {
            paths.add(root.val + "->" + path);
        }
        for (String path : rightPaths) {
            paths.add(root.val + "->" + path);
        }
        if (paths.size() == 0) {
            paths.add("" + root.val);
        }
        return paths;
    }
    
    public List<String> binaryTreePaths2(TreeNode root){
        List<String> paths = new ArrayList<>();
        constructPaths(root,"",paths);
        return paths;
    }

    private void constructPaths(TreeNode root, String path, List<String> paths) {
        if(root != null){
            StringBuffer sb = new StringBuffer(path);
            sb.append(Integer.valueOf(root.val));
            if(root.left == null && root.right == null){
                paths.add(sb.toString());
            }else{
               sb.append("->");
               constructPaths(root.left,sb.toString(),paths);
               constructPaths(root.right,sb.toString(),paths);
            }
        }
    }
}
