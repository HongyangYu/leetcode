/*
 * Created by Henry on 10/31/2017.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q609_Find_Duplicate_File_in_System {

    public List<List<String>> findDuplicate(String[] paths) {
        if(paths==null || paths.length==0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>(); // content : file list
        for(String path : paths) {
            String[] strs = path.split(" ");
            String folder = strs[0];
            for(int i=1; i<strs.length; i++) {
                String file = strs[i];
                int splitIdx = file.indexOf("(");
                String fileName = file.substring(0, splitIdx);
                String content = file.substring(splitIdx+1, file.length()-1);

                List<String> fileList = map.get(content);
                if(fileList == null) {
                    fileList = new ArrayList<>();
                    fileList.add(folder+"/"+fileName);
                    map.put(content, fileList);
                } else {
                    fileList.add(folder+"/"+fileName);
                }
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (List<String> fileList: map.values()) {
            if (fileList.size() > 1) res.add(fileList);
        }
        return res;
        // Java 8 Stream
        // return map.values().stream().filter(val -> val.size()>1).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        String[] paths = {"root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh) 5.txt(aaa)"};
        Q609_Find_Duplicate_File_in_System sol = new Q609_Find_Duplicate_File_in_System();
        List<List<String>> res = sol.findDuplicate(paths);
        System.out.println(res);
    }
}
