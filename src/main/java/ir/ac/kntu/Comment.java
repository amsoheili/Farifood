package ir.ac.kntu;

public class Comment {
    private String subject;
    private String text;

    public Comment(String subject,String text){
        this.subject = subject;
        this.text = text;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString(){
        return "Subject : " + this.subject + "\n" +
                "Text : " + this.text;

    }
}
