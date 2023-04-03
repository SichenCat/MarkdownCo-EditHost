public class Test {
    public static void main(String[] args) {
        Star s = new Star("Ikun");
        Skill sp = StarAgentProxy.getProxy(s);

        sp.run();
        sp.dance();
    }
}
