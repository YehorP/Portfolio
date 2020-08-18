public class Main {

    public static void main(String[] args) {
        int arr[]={1,1,1,4,3,0,0,0,8,8,8
        };
                //state -1-not detected,0-falling,1-growing
                int maxLen=0,maxSum=0,sumTmp=0,lenTmp=0,state=-1,repeated=0;
            for (int i = 0; i < arr.length; i++) {
                repeated=i-1>-1 && arr[i]==arr[i-1]?repeated+1:1;
                lenTmp++;
                sumTmp += arr[i];
                if (i==arr.length-1){
                    if (lenTmp>maxLen){
                        maxLen=lenTmp;
                        maxSum=sumTmp;
                    }
                }
                else if(arr[i+1]>arr[i] && (state==0 || state==-1) ){
                    if(lenTmp>maxLen){
                        maxLen=lenTmp;
                        maxSum=sumTmp;
                    }
                    lenTmp=state==-1?lenTmp:repeated;
                    sumTmp=state==-1?sumTmp:arr[i]*repeated;
                    state=1;
                }
                    else if(arr[i+1]<arr[i] && (state==1 || state==-1)){
                    if(lenTmp>maxLen){
                        maxLen=lenTmp;
                        maxSum=sumTmp;
                    }
                    lenTmp=state==-1?lenTmp:repeated;
                    sumTmp=state==-1?sumTmp:arr[i]*repeated;
                    state=0;
                }
            }
        System.out.println(maxLen+" "+maxSum);
    }
}