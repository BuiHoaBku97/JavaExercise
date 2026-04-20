package draft;

public class printshape {
    static void main() {
        print(10);
    }

    static void print(int max){
        for (int i = 1; i <= max; i++) {
            System.out.println();
            for (int j = 1; j <= max + i; j++) {
                if ( i + j > max + 1 ) {
                    if ( j > max + 1){
                        System.out.print( (max + i + 1 - j ) + " ");
                    }
                    else{
                        System.out.print( (i + j - max - 1 ) + " ");
                    }
                }
                else {
                    System.out.print( "  ");
                }
            }
        }
    }
}
