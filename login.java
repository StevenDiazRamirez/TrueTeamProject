public class login {
    public void loggingIn(int EmployeeID,String password,int attempts ){
        while (attempts < 3){
            if (EmployeeID == 15489286 && password =="Password"){
                System.out.println("Successful login");
                           }
            else{
                System.out.println("login failed");
            }
            attempts = attempts + 1;
        }

    }
}

//hfdsjkfkdshfjk