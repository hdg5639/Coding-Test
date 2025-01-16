class Solution {
    public int solution(int n) {
        int answer = 0;
        if((n % 2) == 0){
            while(n != 0){
                answer = answer + n;
                n = n-2;
            }
        } else{
            n--;
            while(n != 0){
                answer = answer + n;
                n = n-2;
            }
        }
        return answer;
    }
}