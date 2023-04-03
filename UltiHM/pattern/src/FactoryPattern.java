public class FactoryPattern {
    public static Computer createComputer(String info){
        switch (info) {
            case "Huawei":
                Computer c1 = new Huawei();
                c1.setName("Huawei matebook");
                c1.setPrice(7777);
                return c1;
            case "Mac":
                Computer c2 = new Mac();
                c2.setPrice(12345);
                c2.setName("Mac pro");
                return c2;
            default:
                return null;
        }

    }
}
