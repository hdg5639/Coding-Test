class Solution {
    public double solution(int[] numbers) {
        double result = 0;
        int sum = 0;
        for(int number : numbers){
            result = result + (double)number;
            sum++;
        }
        return result/sum;
    }
}