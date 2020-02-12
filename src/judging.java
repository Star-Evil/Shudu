//实现类
public class judging implements judging1 {
    public static boolean judge9(int[] answer)
    {
        int i, j;
        for (i = 0; i < 9; i++)
        {
            for (j = 0; j < 9; j++)
            {
                if (i == j)
                    continue;
                //如果有重复的数字，返回false
                if (answer[i] == answer[j])
                {
                    return false;
                }
            }
        }
        //没有重复数字，返回true
        return true;
    }
}
