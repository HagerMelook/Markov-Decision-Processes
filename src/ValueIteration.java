
// 2 5 8
// 1 4 7
// 0 3 6
public class ValueIteration {
    double [] values = new double[9];
    char [] actions = new char[9];
    double [] valuesTemp;
    public void initialization(){
        for(int i = 0; i<9 ; i++) values[i]=0;
    }
// Up > U, Down > D, Right > R, Left > L
    public void iteration(int r){
        double temp = 0;
        actions[2] = 'X';
        actions[8] = 'X';
        valuesTemp = this.values.clone();
        for(int i = 0; i<9 ; i++){
            if(i == 2) continue;
            if(i == 8) break;
            // Up
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
            actions[i]='U';
            // Down
            if(i%3!=0)
                temp = (0.8*(-1+0.99*valuesTemp[i-1]));
            else
                temp = (0.8*(-1+0.99*valuesTemp[i]));
            if(i>=3){
                if(i==5)
                    temp += (0.1*(r+0.99*valuesTemp[i-3]));
                else
                    temp += (0.1*(-1+0.99*valuesTemp[i-3]));
            }
            else
                temp += (0.1*(-1+0.99*valuesTemp[i]));
            if(i<6){
                if(i==5)
                    temp += (0.1*(10+0.99*valuesTemp[i+3]));
                else
                    temp += (0.1*(-1+0.99*valuesTemp[i+3]));
            }
            else
                temp += (0.1*(-1+0.99*valuesTemp[i]));
            if(temp>values[i]){
                values[i]=temp;
                actions[i]='D';
            }

            //Right
            if(i<6){
                if(i==5)
                    temp = (0.8*(10+0.99*valuesTemp[i+3]));
                else
                    temp = (0.8*(-1+0.99*valuesTemp[i+3]));
            }
            else
                temp = (0.8*(-1+0.99*valuesTemp[i]));
            if(i%3!=0)
                temp += (0.1*(-1+0.99*valuesTemp[i-1]));
            else
                temp += (0.1*(-1+0.99*valuesTemp[i]));
            if(i%3!=2){
                if(i==1)
                    temp += (0.1*(r+0.99*valuesTemp[i+1]));
                else if(i==7)
                    temp += (0.1*(10+0.99*valuesTemp[i+1]));
                else
                    temp += (0.1*(-1+0.99*valuesTemp[i+1]));
            }
            else
                temp += (0.1*(-1+0.99*valuesTemp[i]));
            if(temp>values[i]){
                values[i]=temp;
                actions[i]='R';
            }

            //Left
            if(i>=3){
                if(i==5)
                    temp = (0.8*(r+0.99*valuesTemp[i-3]));
                else
                    temp = (0.8*(-1+0.99*valuesTemp[i-3]));
            }
            else
                temp = (0.8*(-1+0.99*valuesTemp[i]));
            if(i%3!=0)
                temp += (0.1*(-1+0.99*valuesTemp[i-1]));
            else
                temp += (0.1*(-1+0.99*valuesTemp[i]));
            if(i%3!=2){
                if(i==1)
                    temp += (0.1*(r+0.99*valuesTemp[i+1]));
                else if(i==7)
                    temp += (0.1*(10+0.99*valuesTemp[i+1]));
                else
                    temp += (0.1*(-1+0.99*valuesTemp[i+1]));
            }
            else
                temp += (0.1*(-1+0.99*valuesTemp[i]));
            if(temp>values[i]){
                values[i]=temp;
                actions[i]='L';
            }
        }
    }
    // epsilon = 0.01
    public boolean checkConvergence(){
        for(int i = 0; i<9 ; i++){
            if(Math.abs(values[i]-valuesTemp[i])>0.01)
                return false;
        }
        return true;
    }
}