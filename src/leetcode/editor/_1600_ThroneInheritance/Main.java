package leetcode.editor._1600_ThroneInheritance;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ThroneInheritance obj = new ThroneInheritance("king");
        obj.birth("king","clyde");
        obj.death("king");
        obj.birth("clyde","shannon");
        List<String> param_3 = obj.getInheritanceOrder();
        obj.birth("shannon","scott");
        obj.death("clyde");
        List<String> param_4 = obj.getInheritanceOrder();
    }
}
