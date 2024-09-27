import java.util.Scanner;
public class SolveMDP {
    public static void main(String[] args) throws Exception {
        // r = 100, 3, 0, -3
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the value of r: ");
        int r = reader.nextInt();
        System.out.println("\nEnter the start state (or -1 to skip): ");
        int startState = reader.nextInt();
        System.out.println("\nChoose the algorithm:\n1- ValueIteration \n2- PolicyIteration ");
        int policyiteration = reader.nextInt();
        System.out.println("\n");
        if(policyiteration==1){
            ValueIteration valueIteration = new ValueIteration();
            valueIteration.initialization();
            while(true){
                valueIteration.iteration(r);
                if(valueIteration.checkConvergence()){
                    getPolicy(startState,valueIteration.actions,valueIteration.values, r);
                    break;
                }
            }
        }
        else if(policyiteration==2){
            PolicyIteration policyIteration = new PolicyIteration();
            policyIteration.initialization();
            while(true){
                policyIteration.policyEvaluation(r);
                if(!policyIteration.checkConvergenceOfValues()){
                    continue;
                }
                policyIteration.policyExtraction(r);
                if(policyIteration.checkConvergenceOfPolicy()){
                    getPolicy(startState,policyIteration.actions,policyIteration.values, r);
                    break;
                }
            }
        }
        else
            System.out.println("Not Valid!!");
        reader.close();
    }
    private static void getPolicy(int currentState, char [] policy ,double[] values, int r){
        int utility = 0;
            while (true && currentState!=-1) {
                if(policy[currentState]=='X' && currentState==2){
                    System.out.println("Exit");
                    utility +=r;
                    utility++;
                    break;
                }
                if(policy[currentState]=='X' && currentState==8){
                    System.out.println("Exit");
                    utility +=10;
                    utility++;
                    break;
                }
                if(policy[currentState]=='U'){
                    System.out.print("Up > ");
                    utility -=1;
                    currentState++;
                }
                if(policy[currentState]=='D'){
                    System.out.print("Down > ");
                    utility -=1;
                    currentState--;
                }
                if(policy[currentState]=='R'){
                    System.out.print("Right > ");
                    utility -=1;
                    currentState+=3;
                }
                if(policy[currentState]=='L'){
                    System.out.print("Left > ");
                    utility -=1;
                    currentState-=3;
                }
            }
            if(currentState!=-1)
                System.out.println("Total Utility: "+utility);
            System.out.println("\nGeneral policy: ");
            for(int i = 0; i<9; i++){
                System.out.print(values[3*(i%3)+(2-(i/3))]+" ");
                System.out.print(policy[3*(i%3)+(2-(i/3))]+"     ");
                if(i%3==2)
                    System.out.println("\n");
            }

        }
}