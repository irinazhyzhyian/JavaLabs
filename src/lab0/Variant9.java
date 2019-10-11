package lab0;

import java.util.Objects;

public class Variant9
{
    public static class Change
    {
        double a;
        double b;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Change change = (Change) o;
            return Double.compare(change.a, a) == 0 &&
                    Double.compare(change.b, b) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }

        public Change(double k, double l)
        {
            if(k>=l){
                this.a=l;
                this.b=k;
            }
            else {
                this.a=k;
                this.b=l;
            }
        }
    }
        /**
         *
         * @param a is integer number, a>=0
         * @param b is integer number, b>=0
         * @return square root of the sum a and b
         */
        public double inputOutputTask(int a, int b) {
            assert a+b>0 : "a+b should be more than 0";
            return Math.sqrt(a+b);
        }

        /**
         *
         * @param k is three-digital number
         * @return first digital
         */

        public int integerNumbersTask(int k) {
            assert k/100!=0 : "k should be three-digital number";
            return k/100;
        }

        /**
         *
         * @param number1 and number2
         * @return true, if number1 or number2 is odd number
         */
        public boolean booleanTask(int number1, int number2) {
            return number1%2!=0 || number2%2!=0;

        }


        /**
         *
         * @param a  is  double number
         * @param b  is  double number
         * @return Redistribute the values of these variables
         *         so that in a it turns out the smaller of the values,
         *         and in b the larger
         */
        public Change ifTask(double a, double b) {
           // Change obj = new Change(a,b);
            return new Change(a,b);
        }


        /**
         *
         * @param day
         * @param month are integer numbers
         * @return day and month following the specified one
         */
        public String switchTask(int day, int month) {
            assert day>0 && day <32 && month >0 && month <13 : "a+b should be more than 0";

            if(day > 28 && month==2){
                throw new RuntimeException("Wrong input");
            }
            if(day<30) {
                day++;
            }
            if(day== 31)
                    switch (month) {
                        case 4:
                        case 6:
                        case 9:
                        case 11:
                            throw new RuntimeException("Wrong input");
                        case 12:
                            day = 1; month = 1; break;
                        default:
                            day = 1; month++; break;

                    }
            if(day== 30)
                    switch (month) {
                        case 4:
                        case 6:
                        case 9:
                        case 11:
                            day = 1; month++; break;
                        default:
                            day++; break;
                    }
            String s = day + "	" + month;
            return s;
        }


        /**
         *
         * @param a and b are integer numbers, a<b
         * @return sum of the squares of all integers from a to b inclusive
         */
        public long forTask(int a, int b) {
           assert b-a>0: "a should be more than b";
            long sum=0;
            for(int i=a; i<=b; ++i)
                sum+=i*i;
            return sum;
        }
        /**
         *
         * @param n is integer number, n>1
         * @return the smallest integer k such that 3^k>n
         */

        public int whileTask(int n) {
            assert n>1:"n should be more than 1";
            int k=0;
            int dob =1;
            while(dob<=n){
                dob*=3;
                ++k;
            }
            return k;
        }
        /**
         *
         * @param array
         * @return count of all twin numbers and print them in revers order
         */
        public double[] arrayTask(double[] array) {
           double[] temp = new double[array.length];
            int k=0;
            for(int i=array.length-1; i>=0; --i) {
                if (array[i] % 2 == 0) {
                   temp[k] = array[i];
                    ++k;
                }
            }

            if(k != array.length){
                double[] res = new double[k];
                for(int i=0; i<k; i++)
                    res[i]=temp[i];
                return res;
            }

           return temp;
        }
        /**
         *
         * @param array
         * print numbers from twin rows, don`t use if
         */
        public int[][]  twoDimensionArrayTask(int[][] array) {
            int res[][] = new int[((array.length-1)/2)][array[0].length];
            int g =-1;
            for(int i=2; i<array.length; i=i+2){
                System.out.println();
                g++;
                for(int j=0; j<array[0].length; j++){
                    res[g][j] = array[i][j];
                    System.out.print(array[i][j]+"  ");
                }
            }
            return res;
        }
    public static void main(String[] args){}
        }





