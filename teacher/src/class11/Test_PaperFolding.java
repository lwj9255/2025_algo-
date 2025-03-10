package class11;

public class Test_PaperFolding {
    public static void paperFolding(int N){
        process(1,N,true);
    }

    private static void process(int i, int N, boolean down) {
        if(i>N){
            return;
        }
        process(i+1,N,true);
        System.out.println(down?"down":"up");
        process(i+1,N,false);
    }

    public static void main(String[] args) {
        paperFolding(4);
    }
}
