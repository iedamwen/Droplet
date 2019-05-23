public class recurse {
    public static void main(String[] args){
        recurse(2);
        System.out.print("//");
        recurse(2);
        System.out.println("done");
    }

    public static void recurse(int x){
        System.out.println("entering R" + x);
        if(x<=0)
            System.out.print("0");

        else if((x % 2) == 1){
            System.out.print("1");
            recurse(x-2);
        }

        else{
            System.out.print(x);
            recurse(x-1);
        }

        System.out.print("leaving R" + x);
    }
}
