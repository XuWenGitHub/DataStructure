package search.linear;

public class LinearSearch {
    public static void main(String[] args) {
        int[] arr = {1,9,11,-1,34,89};
        System.out.println(linearSearch(arr,89));
    }
    public static int linearSearch(int[] arr,int value){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==value){
                return i;
            }
        }
        return -1;
    }
}
