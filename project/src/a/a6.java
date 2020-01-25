package a;

public class a6 {
	static int[][] a = new int[3][3];
	public static void main(String[] args) {
		  if(dfs(0)){
		        for(int i=0;i<3;i++){
		            for(int j=0;j<3;j++){
		              System.out.print(a[i][j]+" ");  
		            }
		            System.out.println();
		        }
		    }
	}
	//判断九宫格内每个数字各不相同
	static boolean test(int x,int y,int n){
	    a[x][y]=n;
	    for(int i=0;i<3;i++){
	        for(int j=0;j<3;j++){
	            if(a[i][j]==n){
	                if(i!=x||j!=y)
	                    return false;
	            }
	        }
	    }
	    return true;
	}
	
	//判断每行每列之和相等
	static boolean test2(int[][] a){
	    int sum=0;
	    for(int i=0;i<a.length;i++){
	        int sum1=a[0][i]+a[1][i]+a[2][i];
	        int sum2=a[i][0]+a[i][1]+a[i][2];
	        if(sum1!=sum2)
	            return false;
	        if(sum==0)
	            sum=sum1;
	        if(sum1!=sum||sum2!=sum)
	            return false;
	    }
	    return true;
	}
	
	static boolean dfs(int depth){
	    if(depth>=9){
	        if(test2(a))
	            return true;
	        else
	            return false;
	    }
	    int x,y;
	    x=depth/3;
	    y=depth%3;
	 
	    if(a[x][y]!=0)
	        return dfs(depth+1);
	 
	    for(int i=1;i<10;i++){
	        a[x][y]=i;
	        if(test(x,y,i)){
	            if(dfs(depth+1))
	                return true;
	            }
	        a[x][y]=0;
	        }
	        return false;
	    }
}


