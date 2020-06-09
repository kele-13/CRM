import org.junit.Test;

import java.util.Arrays;

public class test {


    @Test
    public void fanZhuan(){
        int[] arr = {1,2,3,4,5};

        for (int i = 0; i < arr.length; i++) {
            int target = arr[0];
            for (int j = i +1;j < arr.length; j++){
                if (arr[i] < arr[j]){
                    target = arr[j];
                    arr[j] = arr[i];
                    arr[i] = target;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }


    @Test
    public void findMedianSortedArrays() {
        int[] nums1 = {1,3,4,5,};
        int[] nums2 = {2,6,7};
        int[] arr = new int[nums1.length + nums2 .length];
        for(int i = 0; i <= arr.length;i++){
            nums1[i] = arr[i];
            for (int j = 0; j<arr.length;j++){
                 nums2[j] = arr[arr.length/2+1+j];
            }

            if(arr[i] > arr[i + 1]){
                int target = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = target;
            }
        }


        int middle = (arr[arr.length / 2] + arr[arr.length / 2 + 1])/2;

        System.out.println(middle);

    }

    @Test
    public void test() {
        int[] nums1 = {1, 3, 4, 5,};
        int[] nums2 = {2, 6, 7};
        int a = nums1.length;
        int b = nums2.length;
        int[] arr = new int[a + b];
        int[] nums3 = new int[a + b];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = nums1[i];
            for (int j = 0; j < b; j++) {
                arr[arr.length / 2 + 1 + j] = nums2[j];
            }
            nums3 = arr;
        }

        for (int k = 0; k < nums3.length; k++) {
            if (nums3[k] > nums3[k + 1]) {
                int target = nums3[k];
                nums3[k] = nums3[k + 1];
                nums3[k + 1] = target;
            }
        }

        int c = arr.length;
        int d = arr[c / 2];
        System.out.println(d);
        int e = arr[c / 2 + 1];
        System.out.println(e);
        int middle = (d + e) / 2;
    }
}
