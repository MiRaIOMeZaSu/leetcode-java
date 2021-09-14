package leetcode.editor._524_findLongestWord;

import java.util.ArrayList;
import java.util.List;

class Solution {
    char pivot = 'a';
    int[][] dp;
    String s;

    public String findLongestWord(String s, List<String> dictionary) {
        // 构造数组字典
        this.s = s;
        int size = s.length();
        dp = new int[size + 1][26];
        for (int i = size - 2; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                dp[i] = dp[i + 1];
            }
            dp[i][s.charAt(i + 1) - pivot] = i + 1;
        }
        // 构造完成，开始搜索
        String result = "";
        for (int i = 0; i < dictionary.size(); i++) {
            if (solve(dictionary.get(i), 0, 0)) {
                result = min(result, dictionary.get(i));
            }
        }
        return result;
    }

    private String min(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        if (aLen > bLen) {
            return a;
        } else if (aLen != bLen) {
            return b;
        }
        for (int i = 0; i < aLen; i++) {
            char chA = a.charAt(i);
            char chB = b.charAt(i);
            if (chA < chB) {
                return a;
            } else if (chA > chB) {
                return b;
            }
        }
        return a;
    }

    private boolean solve(String pat, int patIndex, int tableIndex) {
        if(patIndex >= pat.length()){
            return true;
        }
        boolean result = false;
        int a = dp[tableIndex][pat.charAt(patIndex) - pivot];
        if (a != 0) {
            result |= solve(pat, patIndex + 1, a);
        }
        if (!result && tableIndex != 0) {
            int b = dp[tableIndex][s.charAt(tableIndex - 1) - pivot];
            if (b != 0) {
                return solve(pat, patIndex, b);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> list = new ArrayList<>();
        String[] arr = new String[]{"trole","fsss","v","hsjoj","see","pafi","vgi","j","ri","fpgz","skaey","jhdw","o","sg","pmcad","i","efb","u","rg","zw","n","tuov","rcnpp","kedny","rngkz","nyz","pskwx","mcwmt","fru","n","wp","g","y","wj","az","gnc","d","vt","lmof","hqsfd","qsm","mctii","zhdut","yzjjc","stc","uqs","accv","djf","ip","ttfal","qcirl","cmohs","bcuyp","wzyvb","xiczg","ruhxi","i","tktav","iylen","yswex","uqno","gc","zf","lkfwo","jfrx","pxoq","vx","gwrtw","enyta","ph","gvo","mqp","cfjxc","x","jaje","ywz","tp","ep","kz","opbg","cxx","x","kyk","c","angk","qwc","moms","w","tau","ihs","gzc","u","smjz","spzc","fa","pahl","ythdj","if","ysby","hoffq","bz","urhyz","najuh","ngv","n","qp","g","j","pec","bn","rbe","pd","ipi","j","vbqql","z","datoo","zcomh","ipcrd","tdla","unfmm","jns","b","l","coq","hbatq","yvnb","ckb","lkdle","mwlw","lzfbe","vkhuf","l","wqdlw","my","wkd","sy","is","o","am","mfsay","hp","yvidd","a","bh","qo","tnspj","yodt","rixez","z","i","jknid","qlp","j","lcp","ca","teg","mwvfi","fd","ftcr","nywp","egedk","o","ebk","vaps","crmpx","hjs","xzd","b","w","ftev","phjrs","ig","awaz","drn","nlzq","wkvdm","artn","e","e","fx","aa","x","taey","ghje","cu","me","kr","igc","mswdd","xka","m","ln","gbs","uokwz","esu","q","ta","dn","z","clrl","xi","juqlz","tricb","mzr","yv","nl","eii","uvpdb","zw","xluy","hgwvx","amfz","fw","t","fmat","m","hkmw","avaxy","is","tam","xlfvl","zrdpe","krjc","nu","hzj","mlo","agn","fkkf","aho","hok","mgy","tuj","hwijt","gqk","pkva","ekwn","fehjy","jwitn","eoq","giza","cyfoo","tzmt","zwia","lfrgg","fjd","aoft","ukmoo","qs","i","ous","v","sm","r","w","c","t","jhfsg","wi","rv","ma","inkpp","czv","tsxo","aoa","gll","i","tpi","xiqx","dvnwr","ixo","fczan","ezbm","qxs","qpa","skmi","wzdu","k","y","daxq","kzjlf","wclth","uienm","ioc","thxf","orci","kiyu","vulfs","vwq","stkz","z","lhfeq","dfwei","c","r","g","az","gvpbr","y","shb","uf","htlh","z","pdt","y","mgs","o","wsv","il","n","nuvm","uxug","zjmnb","svrl","xt","uj","bfcz","uuoy","huppe","aalsb","uszw","yq","sgfq","xpv","w","cxsq","znqy","su","drenf","vfy","e","usw","icplr","cpzl","jgfd","vkq","nzxey","wn","se","dr","qd","dbpj","mmel","va","f","ep","v","ubd","lesd","ni","azigv","oqwm","bioph","sx","qhfn","pdin","r","e","o","jjqt","crb","o","cbzsc","oyd","esa","r","uj","jhm","ojzl","zjtxh","j","lcoyu","abrio","olfsh","vwkxj","ldte","ofanj","asaqk","ntfql","ktqs","gokey","nihcl","ckt","jcbsp","jarpw","q","y","anaen","dvy","guvm","gfoaf","onzta","unm","ojjv","rwx","vze","hcskq","bcj","xas","rk","c","jtzwb","ta","pnofe","q","h","ikklq","h","k","gzuyr","a","eyva","kcc","qovhl","eljgk","wvcrk","lg","yqnr","xyj","sr","r","k","pamn","xwi","gn","xs","acy","w","tnpf","skab","dw","u","ipzt","dd","kajei","pfgnn","g","zcvo","q","cpvv","nwq","taciz","kp","zars","ll","phk","qbzff","cqn","gi","aypo","qnb","qwwb","oqdbx","caxx","slbg","rjswh","r","xaaja","d","qwamw","mjpir","rf","eqknd","ttihx","g","qa","gpwq","okckd","bn","pgzn","dwwc","vmkh","w","meup","xoj","p","kyyxb"};
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        System.out.println(solution.findLongestWord("fsiorqycydwhnljzzomfsmuxgjayjjysigirawjxdekjbbtykc", list));
    }
}