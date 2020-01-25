package t1;

public class T4 {

    public static void main(String[] args) {
        int[] a = {90, 90, 67, 3, 3, 8, 43, 89, 90, 90};
        System.out.print("原数组为：");
        int k = 4;
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println();
        quickSelect(a, 0, a.length - 1, k);
        System.out.println();
        System.out.println("第"+k+"小的元素为");
        System.out.println(a[k - 1]);
    }

    private static void quickSelect(int[] a, int left, int right, int k) {
        if (right - left <= 1) {
            return;
        }
        int pivot = findPivot(a, left, right);
        int i = left;
        int j = right - 1;
        for (; ; ) {
            while (a[++i] < pivot) {}
            while (a[--j] > pivot) {}
            if(i < j){
                swap(a,i,j);
            }else{break;}
        }
        swap(a,i,right - 1);
        if(i == k){return;}
        else if(i > k){
            quickSelect(a,left,i - 1,k);
        }else{
            quickSelect(a,i + 1,right,k - i + 1);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    //  寻找支点的方法，将两个端点和中间位置的三个元素的中间元素值作为支点
    //  顺便让left小于支点元素小于right，可以减少一次比较次数
    private static int findPivot(int[] a, int left, int right) {
        int mid = (left + right) / 2;
        if (a[left] > a[mid]) {
            swap(a, left, mid);
        }
        if (a[right] < a[mid]) {
            swap(a, right, mid);
        }
        if (a[left] > a[right]) {
            swap(a, left, right);
        }
        //将支点元素移动到最右边元素的左边，这样可以减少一次比较次数
        swap(a, mid, right - 1);
        return a[right - 1];
    }
}

