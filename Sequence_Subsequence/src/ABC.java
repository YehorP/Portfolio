public class ABC {
    public static void main(String[] args) {
        // write your code here
//        int growStart=0,growtmp=0,fallStart=0,falltmp=0,maxLenGrow=0,maxLenFall=0,maxGrowStart=0,maxFallStart=0,sumgrow=0,sumfall=0,maxSumGrow=0,maxSumFall=0;
//        int arr[]={65,87,47,5,12,74,25,32,78,44,40,77,85,4,29,57,55,79,31,63,84,66,62,41,52,36,82,86,
//                6,98,63,65,14,57,75,14,74,15,41,88,27,75,6,78,98,78,22,77,68,74,92,47,30,44,40,52,
//                70,66,17,60,47,97,34,37,23,69,56,57,3,45,7,76,18,35,24,73,47,77,1,84,92,54,18,98,
//                84,36,66,71,92,13,77,28,75,24,46,67,4,63,82,1
//        };
//        for (int i=1;i<arr.length;i++){
//            sumfall+=arr[falltmp];
//            sumgrow+=arr[growtmp];
//            if (arr[i]>arr[falltmp]){
//                if ((falltmp-fallStart)+1>maxLenFall) {
//                    maxLenFall = (falltmp - fallStart) + 1;
//                    maxFallStart = fallStart;
//                    maxSumFall=sumfall;
//                }
//                 fallStart=i;
//                 sumfall=0;
//            }
//            if (arr[i]<arr[growtmp]){
//                if ((growtmp-growStart)+1>maxLenGrow) {
//                    maxLenGrow = (growtmp - growStart) + 1;
//                    maxGrowStart = growStart;
//                    maxSumGrow=sumgrow;
//                }
//                growStart=i;
//                sumgrow=0;
//            }
//                falltmp++;
//                growtmp++;
//        }
//
//        System.out.println("max len f " + maxLenFall+" start "+maxFallStart+" sum "+maxSumFall);
//        System.out.println("max len g "+maxLenGrow+" start "+maxGrowStart+" sum "+maxSumGrow);
//            System.out.println(maxLenGrow==maxLenFall?maxGrowStart<maxFallStart?maxLenGrow+" "+maxSumGrow:maxLenFall+" "+maxSumFall:maxLenGrow>maxLenFall?maxLenGrow+" "+maxSumGrow:maxLenFall+" "+maxSumFall);
//
        ////-------------------------------------------2
//        int growStart=0,growtmp=0,fallStart=0,falltmp=0,maxLen=0,maxStart=0,sum=0,sumgrow=0,sumfall=0;
//        int arr[]={65,87,47,5,12,74,25,32,78,44,40,77,85,4,29,57,55,79,31,63,84,66,62,41,52,36,82,86,
//                6,98,63,65,14,57,75,14,74,15,41,88,27,75,6,78,98,78,22,77,68,74,92,47,30,44,40,52,
//                70,66,17,60,47,97,34,37,23,69,56,57,3,45,7,76,18,35,24,73,47,77,1,84,92,54,18,98,
//                84,36,66,71,92,13,77,28,75,24,46,67,4,63,82,1
//        };
//        for (int i=1;i<arr.length;i++){
//            sumfall+=arr[falltmp];
//            sumgrow+=arr[growtmp];
//            if (arr[i]>arr[falltmp]){
//                if ((falltmp-fallStart)+1>maxLen) {
//                    maxLen = (falltmp - fallStart) + 1;
//                    maxStart = fallStart;
//                    sum=sumfall;
//                }
//                fallStart=i;
//                sumfall=0;
//            }
//            if (arr[i]<arr[growtmp]){
//                if ((growtmp-growStart)+1>maxLen) {
//                    maxLen= (growtmp - growStart) + 1;
//                    maxStart = growStart;
//                    sum=sumgrow;
//                }
//                growStart=i;
//                sumgrow=0;
//            }
//            falltmp++;
//            growtmp++;
//        }
//        System.out.println(maxLen+" "+sum);
//    }

        //    public static void main(String[] args) {
//        int arr[]={76,23,95
//        };
//                int growLenTmp=0,fallLenTmp=0,maxLen=1,sum=arr.length==1?arr[0]:0,sumgrow=0,sumfall=0;
//
//            for (int i = 0; i < arr.length; i++) {
//                growLenTmp++;
//                fallLenTmp++;
//                sumfall += arr[i];
//                sumgrow += arr[i];
//                if (i==arr.length-1) {
//                    if (fallLenTmp>maxLen){
//                        maxLen=fallLenTmp;
//                        sum=sumfall;
//                    }else if(growLenTmp>maxLen){
//                        maxLen=growLenTmp;
//                        sum=sumgrow;
//                    }
//                }
//                else {
//                    if (arr[i + 1] > arr[i]) {
//                        if (fallLenTmp > maxLen) {
//                            maxLen = fallLenTmp;
//                            sum = sumfall;
//                        }
//                        fallLenTmp = 0;
//                        sumfall = 0;
//                    }
//                    if (arr[i + 1] < arr[i]) {
//                        if (growLenTmp > maxLen) {
//                            maxLen = growLenTmp;
//                            sum = sumgrow;
//                        }
//                        growLenTmp = 0;
//                        sumgrow = 0;
//                    }
//                }
//            }
//        System.out.println(maxLen+" "+sum);
//    }
    }
}
