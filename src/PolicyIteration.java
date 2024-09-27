public class PolicyIteration {
    double [] values = new double[9];
    char [] actions = new char[9];
    double [] valuesTemp;
    char [] actionsTemp;

    public void initialization(){
        for(int i = 0; i<9 ; i++){
            values[i]=0;
            actions[i]='U';
        }
        actions[2] = 'X';
        actions[8] = 'X';
    }
    public void policyEvaluation(int r){
        valuesTemp = this.values.clone();
        for(int i = 0; i<9 ; i++){
            if(i == 2) continue;
            if(i == 8) break;
            if(actions[i]=='U'){
                if(i%3!=2){
                    if(i == 1)
                        values[i] = (0.8*(r+0.99*valuesTemp[i+1]));
                    else if(i == 7)
                        values[i] = (0.8*(10+0.99*valuesTemp[i+1]));
                    else
                        values[i] = (0.8*(-1+0.99*valuesTemp[i+1]));
                }
                else
                    values[i] = (0.8*(-1+0.99*valuesTemp[i]));
                if(i>=3){
                    if(i==5)
                        values[i] += (0.1*(r+0.99*valuesTemp[i-3]));
                    else
                        values[i] += (0.1*(-1+0.99*valuesTemp[i-3]));
                }
                else
                    values[i] += (0.1*(-1+0.99*valuesTemp[i]));
                if(i<6){
                    if(i==5)
                        values[i] += (0.1*(10+0.99*valuesTemp[i+3]));
                    else
                        values[i] += (0.1*(-1+0.99*valuesTemp[i+3]));
                }
                else
                    values[i] += (0.1*(-1+0.99*valuesTemp[i]));
            }
            if(actions[i]=='D'){
                if(i%3!=0)
                    values[i] = (0.8*(-1+0.99*valuesTemp[i-1]));
                else
                    values[i] = (0.8*(-1+0.99*valuesTemp[i]));
                if(i>=3){
                    if(i==5)
                        values[i] += (0.1*(r+0.99*valuesTemp[i-3]));
                    else
                        values[i] += (0.1*(-1+0.99*valuesTemp[i-3]));
                }
                else
                    values[i] += (0.1*(-1+0.99*valuesTemp[i]));
                if(i<6){
                    if(i==5)
                        values[i] += (0.1*(10+0.99*valuesTemp[i+3]));
                    else
                        values[i] += (0.1*(-1+0.99*valuesTemp[i+3]));
                }
                else
                    values[i] += (0.1*(-1+0.99*valuesTemp[i]));
            }
            if(actions[i]=='R'){
                if(i<6){
                    if(i==5)
                        values[i] = (0.8*(10+0.99*valuesTemp[i+3]));
                    else
                        values[i] = (0.8*(-1+0.99*valuesTemp[i+3]));
                }
                else
                    values[i] = (0.8*(-1+0.99*valuesTemp[i]));
                if(i%3!=0)
                    values[i] += (0.1*(-1+0.99*valuesTemp[i-1]));
                else
                    values[i] += (0.1*(-1+0.99*valuesTemp[i]));
                if(i%3!=2){
                    if(i==1)
                        values[i] += (0.1*(r+0.99*valuesTemp[i+1]));
                    else if(i==7)
                        values[i] += (0.1*(10+0.99*valuesTemp[i+1]));
                    else
                        values[i] += (0.1*(-1+0.99*valuesTemp[i+1]));
                }
                else
                    values[i]+= (0.1*(-1+0.99*valuesTemp[i]));
            }

            if(actions[i]=='L'){
                if(i>=3){
                    if(i==5)
                        values[i] = (0.8*(r+0.99*valuesTemp[i-3]));
                    else
                        values[i] = (0.8*(-1+0.99*valuesTemp[i-3]));
                }
                else
                    values[i] = (0.8*(-1+0.99*valuesTemp[i]));
                if(i%3!=0)
                    values[i] += (0.1*(-1+0.99*valuesTemp[i-1]));
                else
                    values[i] += (0.1*(-1+0.99*valuesTemp[i]));
                if(i%3!=2){
                    if(i==1)
                        values[i] += (0.1*(r+0.99*valuesTemp[i+1]));
                    else if(i==7)
                        values[i] += (0.1*(10+0.99*valuesTemp[i+1]));
                    else
                        values[i] += (0.1*(-1+0.99*valuesTemp[i+1]));
                }
                else
                    values[i] += (0.1*(-1+0.99*valuesTemp[i]));
            }
        }
    }
    public boolean checkConvergenceOfValues(){
        for(int i = 0; i<9 ; i++){
            if(Math.abs(values[i]-valuesTemp[i])>0.01)
                return false;
        }
        return true;
    }
    public void policyExtraction(int r){
        ValueIteration valueIteration = new ValueIteration();
        valueIteration.values = values;
        valueIteration.iteration(r);
        actionsTemp = actions.clone();
        actions = valueIteration.actions;
    }

    public boolean checkConvergenceOfPolicy(){
        for(int i = 0; i<9 ; i++){
            if(actions[i]!=actionsTemp[i])
                return false;
        }
        return true;
    }
}
