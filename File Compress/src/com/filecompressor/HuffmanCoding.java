package com.filecompressor;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class HuffmanCoding {
   HashMap<Character,String> encoder;
   HashMap<String,Character> decoder;

   private static class Node implements Comparable<Node>{
       Character data;
       int cost; //frequency
       Node left;
       Node right;

       public Node(Character data,int cost){
           this.data = data;
           this.cost = cost;
           this.left = null;
           this.right = null;
       }

       @Override
       public int compareTo(Node other){
           return this.cost - other.cost;
       }
   }

   public HuffmanCoding(String feeder){
       HashMap<Character,Integer> fmap = new HashMap<>();
       for (int i = 0; i < feeder.length(); i++) {
           char ch = feeder.charAt(i);
           if (fmap.containsKey(ch)){
               int ov = fmap.get(ch);
               ov +=1;
               fmap.put(ch,ov);
           }else{
               fmap.put(ch,1);
           }
       }

       PriorityQueue<Node> pq = new PriorityQueue<>();
       Set<Map.Entry<Character,Integer>> entrySet = fmap.entrySet();
       for (Map.Entry<Character,Integer> entry: entrySet ){
           Node node = new Node(entry.getKey(), entry.getValue());
           pq.add(node);
       }

       while (pq.size() != 1){
           Node first = pq.remove();
           Node second = pq.remove();
           Node newNode = new Node('\0', first.cost + second.cost);
           newNode.left = first;
           newNode.right = second;
           pq.add(newNode);
       }
       Node ft = pq.remove();
       this.encoder = new HashMap<>();
       this.decoder = new HashMap<>();
       this.initEncoderDecoder(ft,"");
   }

   private void initEncoderDecoder(Node node,String osf){
       if (node == null)
           return;

       if (node.left == null && node.right == null){
           this.encoder.put(node.data,osf);
           this.decoder.put(osf,node.data);
       }
       initEncoderDecoder(node.left,osf + "1");
       initEncoderDecoder(node.right,osf + "0");
   }

   //This function will encode the given string data
   public String encode(String source){
       StringBuilder ans = new StringBuilder();
       for (int i = 0; i < source.length(); i++) {
           ans.append(encoder.get(source.charAt(i)));
       }
       return ans.toString();
   }

    //This function will decode the given string data
   public String decode(String codedString){
       String key = "";
       StringBuilder ans = new StringBuilder();
       for (int i = 0; i < codedString.length(); i++) {
           key = key + codedString.charAt(i);
           if (decoder.containsKey(key)){
               ans.append(decoder.get(key));
               key = "";
           }
       }
       return ans.toString();
   }

}
