package lch.dy.util;

import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class create_code {
    Random random = new Random();

   public String create(){
       int a=random.nextInt(10);
       int b=random.nextInt(10);
       int c=random.nextInt(10);
       int d=random.nextInt(10);
       int e=a+10*b+100*c+d*1000;
       String s=String.valueOf(e);
        return s;
    }

    public static void main(String[] args) {
        System.out.println(new create_code().create());
    }
}
