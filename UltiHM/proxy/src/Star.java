public class Star implements Skill{
    private String name;
    public Star(String name){
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println(name + "begin running");
    }

    @Override
    public void dance() {
        System.out.println(name + " begin dancing");
    }
}
