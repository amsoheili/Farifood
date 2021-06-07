package ir.ac.kntu.Users;

public class User {

    private int id;

    private String userName;

    private String passWord;


    public User(){
    }

    public User(int id,String userName,String passWord){
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    @Override
    public String toString(){
        return "ID : " + id + "\n" +
                "User name : " + userName + "\n" +
                "Pass Word : " + passWord;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof User)){
            return false;
        }
        User u = (User)o;
        if (this.id != u.getId()){
            return false;
        }
        if (!this.userName.equals(u.getUserName())){
            return false;
        }
        if (!this.passWord.equals(u.getPassWord())){
            return false;
        }
        return true;
    }
}
