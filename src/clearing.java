import java.util.Random;

public class clearing {
    //清空数组
    public int[][] clear(int a[][])
    {
        int[][] temp = new int[9][9];
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                temp[i][j]=a[i][j];
            }
        }
        Random r = new Random();
        int a1, a2;
        for (int i = 0; i < 100; i++)
        {
            a1 = r.nextInt(9);
            a2 = r.nextInt(9);
            temp[a1][a2] = 0;
        }
        return temp;
    }
}
