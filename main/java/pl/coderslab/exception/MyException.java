package pl.coderslab.exception;

public class MyException extends NumberFormatException{

    public String message="Wrong format. Year needed";

    public MyException(String message){
        this.message = message;
    }

    // Overrides Exception's getMessage()
    @Override
    public String getMessage(){
        return message;
    }

    // Testing
//    public static void main(String[] args){
//        MyException e = new MyException("Wrong format. Year needed");
//        System.out.println(e.getMessage());
//    }


}