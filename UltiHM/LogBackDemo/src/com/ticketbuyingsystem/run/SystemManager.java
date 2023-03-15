package com.ticketbuyingsystem.run;

import com.ticketbuyingsystem.bean.Business;
import com.ticketbuyingsystem.bean.Customer;
import com.ticketbuyingsystem.bean.Movie;
import com.ticketbuyingsystem.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

public class SystemManager {
    /**
     *  define dockers to store the data of users with the following 2 instances:
     *  1. store multiple users instances(Business,Customer)
     *      Business Customer extends-> User
     *  2. store ALL Business and their Movie data
     *      B1 , { movie1, movie2 , ...}
     *      B2 , { ...... }
     *      Obviously we should use a "Map<Business,List<Movie>>" to store
     */
    public static final List<User> ALL_USERS = new ArrayList<>();

    public static final Map<Business,List<Movie>> ALL_MOVIES = new HashMap<>();
    /**
     * define some Aboveall Static variety: User,Scanner,sdf,logger
     */
    public static User loginUser; // AboveAll User -- only 1 user is online
    public static final Scanner SYS_SC = new Scanner(System.in);
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static final Logger LOGGER = LoggerFactory.getLogger("SystemManager.class");


     /**
     * Use a static code block to store data for test
     */
    static {
        Customer c = new Customer();
        c.setLoginName("zyf888");
        c.setPassWord("123456");
        c.setUserName("黑马刘德华");
        c.setSex('男');
        c.setMoney(10000);
        c.setPhone("110110");
        ALL_USERS.add(c);

        Customer c1 = new Customer();
        c1.setLoginName("gzl888");
        c1.setPassWord("123456");
        c1.setUserName("黑马关之琳");
        c1.setSex('女');
        c1.setMoney(2000);
        c1.setPhone("111111");
        ALL_USERS.add(c1);

        Business b = new Business();
        b.setLoginName("baozugong888");
        b.setPassWord("123456");
        b.setUserName("黑马包租公");
        b.setMoney(0);
        b.setSex('男');
        b.setPhone("110110");
        b.setAddress("火星6号2B二层");
        b.setShopName("甜甜圈国际影城");
        ALL_USERS.add(b);
        // 注意，商家一定需要加入到店铺排片信息中去
        List<Movie> movies = new ArrayList<>();
        ALL_MOVIES.put(b , movies); // b = []

        Business b2 = new Business();
        b2.setLoginName("baozupo888");
        b2.setPassWord("123456");
        b2.setUserName("黑马包租婆");
        b2.setMoney(0);
        b2.setSex('女');
        b2.setPhone("110110");
        b2.setAddress("火星8号8B八层");
        b2.setShopName("巧克力国际影城");
        ALL_USERS.add(b2);
        // 注意，商家一定需要加入到店铺排片信息中去
        List<Movie> movies3 = new ArrayList<>();
        ALL_MOVIES.put(b2 , movies3); // b2 = []
    }
    public static void main(String[] args) {
        showMain();
    }

    private static void showMain() {
        System.out.println("=======U'v entered the system========");
        System.out.println("1. Login");
        System.out.println("2. Customer User Sign up");
        System.out.println("3. Business User Sign up");
        while (true) {
            System.out.println("Please choose an option");
            String command  = SYS_SC.nextLine();
            switch (command){
                case "1":login();break;
                case "2":
                case "3":
                default:
                    System.out.println("Invalid input!");
            }
        }

    }

    private static void login() {
        while (true) {
            System.out.println("Please Enter your Login Name");
            String loginName = SYS_SC.nextLine();
            System.out.println("Please Enter your password");
            String passWord = SYS_SC.nextLine();
            // query the 'User' instance
            User u = getUserByLoginName(loginName);
            // check if it's null ,then go next
            if ( u != null) {
                //check the pwd
                if (u.getPassWord().equals(passWord)){
                    //pwd checked; check user TypeOfClass
                    loginUser = u;
                    if ( u instanceof Customer){
                        showCustomerMain();
                    }else if (u instanceof Business){
                        showBusinessMain();
                    }else { } // hold subsequent demands
                    return;
                }else {
                    System.out.println("pwd wrong!");
                }
            } else {
                System.out.println("User Not EXIST!retry:");
            }
        }
    }

    private static void showBusinessMain() {
        while (true) {
            System.out.println("============黑马电影商家界面===================");
            System.out.println(loginUser.getUserName() + (loginUser.getSex()=='男'? "先生":"女士" + "欢迎您进入系统"));
            System.out.println("1、展示详情:");
            System.out.println("2、上架电影:");
            System.out.println("3、下架电影:");
            System.out.println("4、修改电影:");
            System.out.println("5、退出:");

            System.out.println("请输入您要操作的命令：");
            String command = SYS_SC.nextLine();
            switch (command){
                case "1":
                    // 展示全部排片信息
                    showBusinessInfos();
                    break;
                case "2":
                    // 上架电影信息
                    addMovie();
                    break;
                case "3":
                    // 下架电影信息
                    deleteMovie();
                    break;
                case "4":
                    // 修改电影信息
                    updateMovie();
                    break;
                case "5":
                    System.out.println(loginUser.getUserName() +"请您下次再来啊~~~");
                    return; // 干掉方法
                default:
                    System.out.println("不存在该命令！！");
                    break;
            }
        }
    }

    private static void addMovie() {
        Business business = (Business) loginUser;
        List<Movie> movies = ALL_MOVIES.get(business);
    }

    //Show the Infos of present Business instance
    private static void showBusinessInfos() {
        System.out.println("===========Business Details============");
        LOGGER.info (loginUser.getUserName());
        Business business = (Business) loginUser; // Cast to Business,so we can use inferior attributes
        System.out.println(business);
        // according to the Key: loginUser to get the Value:List<Movie>
        List<Movie> movies = ALL_MOVIES.get(loginUser); // get value by key
        if (movies.size() > 0 ) {
            for (Movie movie:movies
                 ) {
                System.out.println(movie.getActor());
                System.out.println(movie.getName());
                // No more!
            }
        }

    }

    private static void showCustomerMain() {
        while (true) {
            System.out.println("============黑马电影客户界面===================");
            System.out.println(loginUser.getUserName() + (loginUser.getSex()=='男'? "先生":"女士" + "欢迎您进入系统" +
                    "\t余额：" + loginUser.getMoney()));
            System.out.println("请您选择要操作的功能：");
            System.out.println("1、展示全部影片信息功能:");
            System.out.println("2、根据电影名称查询电影信息:");
            System.out.println("3、评分功能:");
            System.out.println("4、购票功能:");
            System.out.println("5、退出系统:");
            System.out.println("请输入您要操作的命令：");
            String command = SYS_SC.nextLine();
            switch (command){
                case "1":
                    // 展示全部排片信息
                    showAllMovies();
                    break;
                case "2":
                    break;
                case "3":
                    // 评分功能
                    scoreMovie();
                    showAllMovies();
                    break;
                case "4":
                    // 购票功能
                    buyMovie();
                    break;
                case "5":
                    return; // 干掉方法
                default:
                    System.out.println("不存在该命令！！");
                    break;
            }
        }
    }

    public static User getUserByLoginName(String loginName){
        for(User user:ALL_USERS){
            if (user.getLoginName().equals(loginName)){
                return user;
            }
        }
        return null;
    }
}
