package Mom;

import java.util.Arrays;

public class Mom {
    public static void main(String[] args) {
        String str = "1 2 3 4";
        //new Mam(str).Permutation();
        new Mom(str).PermutationFuct();
    }


    String[] s;
    public Mom(String str)
    {
        this.s = str.split(" ");
    }

    public void Permutation()
    {
        String[] strings = this.s;
        for (int i = 0; i < strings.length; i++)
        {
            for (int j = 0; j < strings.length - 1; j++)
            {
                //PrintArray(strings);
                String tmp = strings[j];
                strings[j] = strings[j + 1];
                strings[j + 1] = tmp;
            }
        }
    }

    public void PermutationFuct()
    {
        int[] index = new int[s.length];
        for (int i = 0; i < index.length; i++)
        {
            index[i] = i;
        }
        Prem(index, 0);
    }

    public void Prem(int[] array, int i)
    {
        if (i == array.length)
        {
            System.out.println(Arrays.toString(array));
        }
        else
        {
            for (int j = i; j < array.length; j++)
            {
                Swap(array, i, j);
                Prem(array, i + 1);
                Swap(array, i, j);
            }
        }
    }

    private void Swap(int[] el, int i, int j)
    {
        int s = el[i];
        el[i] = el[j];
        el[j] = s;
    }
}
