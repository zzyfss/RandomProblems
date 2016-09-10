// zzy, 9/7/16
// Google online assessment
public class Solution {
    public void test() {
        String testA = 
                "dir1\n"
                + " dir11\n"
                + " dir12\n"
                + "  picture.jpeg\n"
                + "  dir121\n"
                + "  file1.txt\n"
                + "dir2\n"
                + " file2.gif";
        System.out.println(solution(testA));
    }
    
    public int solution(String s) {
       String[] input = s.split("\n");
       List<String> imagePaths = new ArrayList<>();
       getImagePaths(new ArrayList<String>(Arrays.asList(input)), 0, "/", imagePaths);
       int sum = 0;
       for(String path: imagePaths) {
           sum += path.length();
           // System.out.println(path);
       }
       return sum;
    }
    
    private void getImagePaths(List<String> input, int dirDepth, String path, List<String> imagePaths) {
        while(!input.isEmpty()) {
            String name = input.get(0);
            int numOfSpaces = numOfPrecedingSpaces(name);
            if(numOfSpaces < dirDepth) {
                return;
            }
            if(numOfSpaces == dirDepth) { 
                input.remove(0);
                if(isDir(name)) {
                    getImagePaths(input, dirDepth + 1, path  + name.trim() + "/", imagePaths);
                }
                else if(isImage(name)) {
                    imagePaths.add(path + name.trim());
                }
            }
        }
    }

    private int numOfPrecedingSpaces(String name) {
        return name.indexOf(name.trim());
    }
    
    private boolean isDir(String name) {
        return !name.contains(".");
    }
    
    private boolean isImage(String name) {
        String ext = getExtension(name);
        return ext.equals("jpeg") || ext.equals("png") || ext.equals("gif");
    }
    
    private String getExtension(String name) {
        return name.substring(name.indexOf('.') + 1);
    }
}
