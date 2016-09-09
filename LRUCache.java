    public void test() {
        LRUCache cache = new LRUCache(3);
        cache.set(1, 1);
        cache.set(2, 2);
        cache.set(3, 3);
        cache.set(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        cache.set(5,5);
        System.out.println(cache.get(3));
        System.out.println(cache.get(5));
    }
    
    public class LRUCache {

       public class Node {
            int key;
            int value;
            Node prev;
            Node next;
        }
        
        private int size;
        
        private int capacity;
        
        private Node head;
        
        private Node tail;
        
        private Map<Integer, Node> keyNodeMap;
        
        public LRUCache(int capacity) {
            keyNodeMap = new HashMap<>(capacity);
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
            size = 0 ; 
            this.capacity = capacity;
        }

        

        public int get(int key) {
            if(keyNodeMap.containsKey(key)) {
                Node node = keyNodeMap.get(key);
                update(node);
                return node.value;
            };
            return -1;

        }

        public void set(int key, int value) {
            if(keyNodeMap.containsKey(key)) {
                Node node = keyNodeMap.get(key);
                update(node);
                node.value = value;
                return;
            }
            
            Node node = new Node();
            node.key = key;
            node.value = value;
            keyNodeMap.put(key, node);
            insertBefore(node, tail);
            
            if(++size > capacity) {
                removeLRUNode();
                size--;
            }
        }
        
       private void update(Node node) {
           remove(node);
           insertBefore(node, tail);
       }
       
       private void removeLRUNode() {
           Node node = head.next;
           remove(node);
           keyNodeMap.remove(node.key);
       }
       
       private void insertBefore(Node node, Node dest) {
           node.next = dest;
           node.prev = dest.prev;
           dest.prev.next = node; 
           dest.prev = node;
       }
       
       private void remove(Node node) {
           node.prev.next = node.next;
           node.next.prev = node.prev;
       }

    }
