package leetcode.editor._1600_ThroneInheritance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class ListNode {
    TreeNode node;
    ListNode next;
    ListNode prevs;

    ListNode(TreeNode n) {
        node = n;
    }
}

class TreeNode {
    String name = "";
    TreeNode father;
    ListNode childrenHead = new ListNode(null);
    ListNode childrenTail = new ListNode(null);
    boolean isDead = false;

    TreeNode(String n) {
        name = n;
        childrenHead.next = childrenTail;
        childrenTail.prevs = childrenHead;
    }

    void childDead(String deadChild, HashMap<String, ListNode> table) {
        ListNode listNode = table.get(deadChild);
        TreeNode dead = listNode.node;
        dead.isDead = true;
        if (dead.childrenHead.next != dead.childrenTail) {
            // 链表连接操作
            ListNode curr = dead.childrenHead.next;
            while (curr != dead.childrenTail) {
                curr.node.father = this;
                curr = curr.next;
            }
            listNode.prevs.next = dead.childrenHead.next;
            dead.childrenHead.next.prevs = listNode.prevs;
            listNode.next.prevs = dead.childrenTail.prevs;
            dead.childrenTail.prevs.next = listNode.next;
        } else {
            listNode.prevs.next = listNode.next;
            listNode.next.prevs = listNode.prevs;
        }
        table.remove(deadChild);
    }

    void childBirth(TreeNode child, HashMap<String, ListNode> table) {
        ListNode listChild = new ListNode(child);
        table.put(child.name, listChild);
        ListNode prevs = childrenTail.prevs;
        prevs.next = listChild;
        listChild.prevs = prevs;
        listChild.next = childrenTail;
        childrenTail.prevs = listChild;
    }
}

class ThroneInheritance {
    HashMap<String, TreeNode> map = new HashMap<>();
    HashMap<String, ListNode> table = new HashMap<>();
    TreeNode king;

    public ThroneInheritance(String kingName) {
        king = new TreeNode(kingName);
        map.put(kingName, king);
    }

    public void birth(String parentName, String childName) {
        TreeNode child = new TreeNode(childName);
        map.put(childName, child);
        TreeNode father = map.get(parentName);
        child.father = father;
        father.childBirth(child, table);
    }

    public void death(String name) {
        if (name.equals(king.name)) {
            king.isDead = true;
            return;
        }
        TreeNode father = map.get(name).father;
        father.childDead(name, table);
    }

    public List<String> getInheritanceOrder() {
        List<String> ret = new ArrayList<>();
        solve(king, ret);
        return ret;
    }

    private void solve(TreeNode node, List<String> ret) {
        if (!node.isDead) {
            ret.add(node.name);
        }
        ListNode curr = node.childrenHead.next;
        while (curr != node.childrenTail) {
            solve(curr.node, ret);
            curr = curr.next;
        }
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.birth(parentName,childName);
 * obj.death(name);
 * List<String> param_3 = obj.getInheritanceOrder();
 */