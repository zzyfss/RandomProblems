// zzy, 9/9/16
    @Test
    public void test() {
        Solution s = new Solution();
        assertEquals(s.decodeString("3[a]2[bc]"), "aaabcbc");
        assertEquals(s.decodeString("3[a2[c]]"), "accaccacc");
        assertEquals(s.decodeString("2[abc]3[cd]ef"), "abcabccdcdcdef");
    }
    
    public class Solution {

        public String decodeString(String s) {     
            return decode(s, 0).output;
        }
        
        private Item decode(String s, int index) {
            StringBuilder sb = new StringBuilder();
            while(index < s.length()) {
                char c = s.charAt(index);
                if(isDigit(c)) {
                    int bracketIdx = s.indexOf('[', index);
                    int repeat = Integer.valueOf(s.substring(index, bracketIdx));
                    Item item = decode(s, bracketIdx+1);
                    sb.append(buildString(repeat, item.output));
                    index = item.index;
                }
                else if (c == ']') {
                    return new Item(sb.toString(), index);
                }
                else {
                    sb.append(c);
                }
                index++;
            }
            return new Item(sb.toString(), index);
        }
        
        class Item {
            int index;
            String output;
            public Item(String output, int index) {
                this.index = index;
                this.output = output;
            }
        }

        private boolean isDigit(char c) {
            return c >= '0' && c <= '9';
        }
        
        private String buildString(int times, String s) {
            StringBuilder sb = new StringBuilder();
            while(times-- > 0) {
                sb.append(s);
            }
            return sb.toString();
        }
        
    }
