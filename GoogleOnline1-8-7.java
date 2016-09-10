// zzy, 9/6/16
    public void test() {
        int X = 233614;
        System.out.println(new Solution().solution(X));
    }
    
    class Solution {
        public int solution(int X) {
            int min = Integer.MAX_VALUE;
            String input = String.valueOf(X);
            for(int i=0; i < input.length() - 1; i++) {
                String outcome;
                if(input.charAt(i) > input.charAt(i+1)) {
                    outcome = remove(input, i+1);
                }
                else{
                    outcome = remove(input, i);
                }
                //System.out.println(outcome);
                int outcomeInt = Integer.valueOf(outcome);
                if(min > outcomeInt) {
                    min = outcomeInt;
                }
            }
           return Integer.valueOf(min);
        }
        
        private String remove(String s, int index) {
            String leftHalf = s.substring(0, index);
            String rightHalf = "";
            if(index < s.length() - 1) {
                rightHalf = s.substring(index+1, s.length());
            }
            return leftHalf + rightHalf;
        }
        
    }



