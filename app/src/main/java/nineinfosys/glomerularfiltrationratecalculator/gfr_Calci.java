package nineinfosys.glomerularfiltrationratecalculator;


import java.math.RoundingMode;
import java.text.DecimalFormat;

public class gfr_Calci {
    double result;
    double mgResult;
    double milimolResult;
double resultnext;
    public static final String RESULT_FORMAT = "#.#";

   public  double gfr(double value,double age,String gender,String unit){

       if(gender.equals("Male")){
           if(unit.equals("mg/dl")){
               mgResult=value;
               result = 163 *Math.pow(mgResult /0.9,-1.209) * Math.pow(0.993,age);

           }else{
               mgResult = value;
               milimolResult=mgResult*0.01131222;
               result = 163 *Math.pow(milimolResult /0.9,-1.209) * Math.pow(0.993,age);
              // resultQuadratic=Math.exp(1.911+5.249/value-2.114/value-0.00686*age-(0.205));
           }

       }else {
           if(unit.equals("mg/dl")){
               mgResult=value;
               result = 166 *Math.pow(mgResult/0.7,-1.209) * Math.pow(0.993,age);
           }else{
               mgResult = value;
               milimolResult=mgResult*0.01131222;
               result =  166 *Math.pow(milimolResult/0.7,-1.209) * Math.pow(0.993,age);
           }
       }
       DecimalFormat df = new DecimalFormat(RESULT_FORMAT);
       df.setRoundingMode(RoundingMode.HALF_UP);
       return  Double.parseDouble(df.format(result));
   }


    public  double gfrQuadratic(double value,double age,String gender,String unit){

        if(gender.equals("Male")){
            if(unit.equals("mg/dl")){
                mgResult=value;
                result = Math.exp(1.911+5.249/value - 2.114/(Math.pow(value,2))- 0.00686 * age );

            }
            else{
                mgResult = value;
                milimolResult=mgResult/0.01129;
                result=   Math.exp(1.911 + 5.249/value - 2.114/(Math.pow(value,2) - 0.00686 * age ));

            }

        }else {
            if(unit.equals("mg/dl")){
                mgResult=value;
                result= Math.exp(1.911 + (5.249/value) - (2.114/Math.pow(value,2)) - (0.00686)*(age)-0.205);
            }else{
              //  mgResult = value*88.57;
                milimolResult=mgResult/0.01129;
                result= Math.exp(1.911 + (5.249/value) - (2.114/Math.pow(value,2)) - (0.00686)*(age)-0.205);
            }
        }
        DecimalFormat df = new DecimalFormat(RESULT_FORMAT);
        df.setRoundingMode(RoundingMode.HALF_UP);
        return  Double.parseDouble(df.format(result));
    }
    //GFR = exp(1.911 + 5.249/SCr - 2.114/SCr2 - 0.00686 × Age - (0.205 if Female))

}


