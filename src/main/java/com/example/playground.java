package com.example;

class playground {
 int divisor;
 int input;
    playground(){
        divisor=0;
        input=10;
    };
    
    public static void main(String[] args) {
        System.out.println("Program to check even or odd with constructor");
            playground objectName = new playground();
            if(objectName.input%objectName.divisor==0){
                System.out.println("Even number");
            }
            else{
                System.out.println("Odd number");
            }

        }
         
        
    
        

    }

        
    