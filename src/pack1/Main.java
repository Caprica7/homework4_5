package pack1;

//import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.*;

import static java.lang.Thread.*;


public class Main {



    private static void cyclePrintText(int n, String text) {
        for (int i = 0; i < n; i++) {
            System.out.println(text);
        }
    }

    private static void cycle3PrintText(String text) {
        System.out.print(text+" "+text+" "+text);
    }

    private static int findMin(int @NotNull [] arr) {
        int[] innerArr = arr.clone();
        int min = innerArr[0];
        for (int i = 0; i < innerArr.length; i++) {
            if (innerArr[i] < min) {
                min = innerArr[i];
            }
        }
      return min;
    }


    private static int findMax2(int n1, int n2) {
        if (n1>n2)
           return n1;
        else
            return n2;
    }



    //*устарело, первая попытка* по-хорошему надо прогревать JVM, вычислять среднее по n запускам метода и т.п., но нам для учебного проекта надо лишь грубое приближение
    private static long timer(Runnable method, TimeUnit timeUnit) {
        long time = System.nanoTime();
        method.run();
        time = System.nanoTime() - time;
        return TimeUnit.NANOSECONDS.convert(time, timeUnit);
    }

    //попытка более точного измерения
    private static long timerAVG(Runnable method, TimeUnit timeUnit) {
        long time;
        int count = 101;
        long[] arr = new long[count];
        for (int i = 0; i < count/5; i ++) { //прогрев JVM
            method.run();
        }

        //заполняем массив измеренными значениями выполнения метода
        for (int i = 0; i < count-1; i ++)
        {
            time = System.nanoTime();
            method.run();
            arr[i] = System.nanoTime() - time;
        }
        //возвращаем среднее значение по всем выполненным методам
        return TimeUnit.NANOSECONDS.convert((long) LongStream.of(arr).average().getAsDouble(), timeUnit);
    }


    //1. наивная рекурсивная реализация вычисления чисел Фибоначчи
    static int naiveRFib (int n)
    {
        if (n < 0) throw new AssertionError("Отрицательные значения запрещены");

        //для учебного примера воспользуемся int, если хочется больше то можно воспользоваться BigInteger
        if (n>46) throw new AssertionError("Учебная версия, используется тип int, вычисление возможно только до 46 числа Фибоначчи"); //иначе переполнение
        if (n<=1)
            return n;
        else
            return naiveRFib(n-1)+naiveRFib(n-2);
    }

    /*static int naiveRFib2(int n){
        if (n==1){
            return n;
        }
        return n * naiveRFib2(n - 1);
    } */

    //1.1 наивная рекурсивная реализация вычисления чисел Фибоначчи для больших чисел. Очень медленно, медленнее в несколько раз чем naiveRFib из п.1
    //сколько можно вычислить чисел Фибоначчи в зависимости от типа данных: int 46 чисел, long 91 число, если надо больше то используем BigInteger
    static BigInteger naiveBigRFib (BigInteger n)
    {
        if (n.compareTo(BigInteger.valueOf(0)) == -1) throw new AssertionError("Отрицательные значения запрещены");
        if ( (n.equals(BigInteger.valueOf(0))) || (n.equals(BigInteger.valueOf(1))) )
            return n;
        else
            return (naiveBigRFib(n.subtract(BigInteger.valueOf(1)))).add( (naiveBigRFib(n.subtract(BigInteger.valueOf(2)))));
    }

    // 2. В цикле - с использованием памяти, массив хранит все вычисленные нами значения.
    static int cycleFibArr(int n){
        int n2 = n+1;
        if (n2 < 0) throw new AssertionError("Отрицательные значения запрещены");
        if (n2>47) throw new AssertionError("Учебная версия, используется тип int, вычисление возможно только до 46 числа Фибоначчи"); //иначе переполнение
        int[] arr = new int[n2];
        arr[0] = 0;
        arr[1] = 1;
        if (n<=1)
            return n;
        for (int i = 2; i < n2; ++i) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n2-1];
    }

    //3. Сокращаем использование памяти, в п.2 мы хранили все вычисленные значения, но исходя из задачи нам нет смысла это делать,
    //   ведь в итоге из всего массива мы используем лишь последние 2 вычисленных числа для того, чтобы вычислить следующее число, поэтому будем хранить (и перезаписывать их) лишь последние 2 вычисленных числа.
    //   А вот если бы задача была использовать ещё каким-либо образом любые вычисленные нами значения, то тогда только алгоритм из п.2, ну может ещё структуру данных другую можно выбрать
    static int cycleFibOptMem(int n){
        //задаём начальные значения для 0 и 1
        int x = 0;
        int y = 1;
        if (n<=1)
            return n;

        for (int i = 2; i <= n; ++i) {
            int next = x + y;
            x = y;
            y = next;
        }
        return y;
    }


    public static void main(String[] args) throws IOException, InterruptedException, ParseException {

        //Задания JavaRush
        //Уровень 1
        String name1 = "Присваиваем значение";
        String name2 = "Присваиваем значение";
        String name3 = "Присваиваем значение";
        cyclePrintText(10,"Ты должен делать то, что должен");
        System.out.println("Happy New Year");
        int s=5;
        int t=6;
        int x=7;

        System.out.println("Меня зовут Амиго.");
        System.out.println("Я согласен на зарплату $10/месяц в первый год.");
        System.out.println("Я согласен на зарплату $20/месяц во второй год.");
        System.out.println("Я согласен на зарплату $30/месяц в третий год.");
        System.out.println("Я согласен на зарплату $40/месяц в четвертый год.");
        System.out.println("Я согласен на зарплату $50/месяц в пятый год.");
        System.out.println("Спасибо за щедрость, друг Риша!");

        System.out.println("Меня зовут Амиго.");
        System.out.println("Я согласен на зарплату $800/месяц в первый год.");
        System.out.println("Я согласен на зарплату $1500/месяц во второй год.");
        System.out.println("Я согласен на зарплату $2200/месяц в третий год.");
        System.out.println("Я согласен на зарплату $2700/месяц в четвертый год.");
        System.out.println("Я согласен на зарплату $3200/месяц в пятый год.");
        System.out.println("Поцелуй мой блестящий металлический зад!");

        System.out.println("Nothing personal, it's just business.");
        cyclePrintText(3,"Слава Роботам! Убить всех человеков!");

        //Уровень 2
        class Person
        {
            String name;
            int age;
        }
        class Rectangle
        {
            int x, y, width, height;
        }
        class Cat
        {
            String owner;
            Rectangle territory;
            int age;
            String name;
        }
        class Dog
        {
            String owner;
            String name;
        }

        class Woman
        {
            Person owner;
            String name;
        }
        new Cat();
        new Cat();
        new Cat();

        new Dog().name="Max";
        new Dog().name="Bella";
        new Dog().name="Jack";

        System.out.println("Мне так плохо! Хочу, чтобы все умерли!");
        System.out.println(19);

        Cat cat1;
        Cat cat2;
        Cat cat3;
        Cat cat4;
        Cat cat5;
        Cat cat6;
        Cat cat7;
        Cat cat8;
        Cat cat9;
        Cat cat10;

        new Cat();
        new Cat();
        new Cat();
        new Cat();
        new Cat();
        new Cat();
        new Cat();
        new Cat();



        new Cat().owner= new Person().name="Любитель котиков";
        new Dog().owner= new Person().name="Собаковед";
        new Woman();

        System.out.println("Минимальное значение среди двух чисел 5, 1 ="+findMin(new int[] {5, 1}));
        System.out.println("Минимальное значение среди двух чисел 5, 1 ="+findMax2(5, 1));
        System.out.println("Минимальное значение среди трёх чисел 5, 1, 20 ="+findMin(new int[] {5, 1, 20}));
        System.out.println("Минимальное значение среди четырёх чисел 10, 4, -2, 3 ="+findMin(new int[] {1, 4, -2, 3}));
        cyclePrintText(3,"выводит переданную строку на экран три раза");
        cycle3PrintText("три раза в одной строке");

        //Уровень 3
        //Date dateOne = format.parse(date1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM.dd.yyyy");
        System.out.println("date: " + new Date() );

        class Protos
        {
            String name;
        }

        class Zerg
        {
            String name;

        }
        class Terran
        {
            String name;
        }

        class d3
        {
            static void createArmy()
            {
                for (int i = 0; i < 12; i++)
                {
                    new Terran().name="Terran"+i;
                }
                for (int i = 0; i < 10; i++)
                {
                    new Zerg().name="Zerg"+i;
                }
                for (int i = 0; i < 5; i++)
                {
                    new Protos().name="Protos"+i;
                }
            }
            static void tableP()
            {
                int[] p = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                for (int q : p) {
                    for (int w : p) {
                        System.out.printf("%4d", (q * w));
                    }
                    System.out.println();
                }
            }
        }

        d3.createArmy();

        System.out.println(1*2*3*4*5*6*7*8*9*10);
        System.out.println(1+2);
        System.out.println(1+2+3);
        System.out.println(1+2+3+4);
        System.out.println(1+2+3+4+5);
        System.out.println(1+2+3+4+5+6);
        System.out.println(1+2+3+4+5+6+7);
        System.out.println(1+2+3+4+5+6+7+8);
        System.out.println(1+2+3+4+5+6+7+8+9);
        System.out.println(1+2+3+4+5+6+7+8+9+10);

        String a = "Мама";
        String b = "Мыла";
        String c = "Раму";
        System.out.println(a+b+c);
        System.out.println(a+c+b);
        System.out.println(b+a+c);
        System.out.println(b+c+a);
        System.out.println(c+a+b);
        System.out.println(c+b+a);

        d3.tableP();

        //ROYGBIV
        class Red
        {
            Red()
            {
                System.out.println("Red");
            }
        }
        class Orange
        {
            Orange()
            {
                System.out.println("Orange");
            }
        }
        class Yellow
        {
            Yellow()
            {
                System.out.println("Yellow");
            }
        }
        class Green
        {
            Green()
            {
                System.out.println("Green");
            }
        }
        class Blue
        {
            Blue()
            {
                System.out.println("Blue");
            }
        }
        class Indigo
        {
            Indigo()
            {
                System.out.println("Indigo");
            }
        }
        class Violette
        {
            Violette()
            {
                System.out.println("Violette");
            }
        }
        new Red();
        new Orange();
        new Yellow();
        new Green();
        new Blue();
        new Indigo();
        new Violette();

        System.out.println("It's Windows path: \"C:\\Program Files\\Java\\jdk1.7.0\\bin\"");
        System.out.println("It's Java string: \\\"C:\\\\Program Files\\\\Java\\\\jdk1.7.0\\\\bin\\\"");
        System.out.println("日本語");

        //дополнительные задания
        System.out.println("Рекурсивный способ naiveRFib="+timerAVG(() -> {naiveRFib(36);}, TimeUnit.NANOSECONDS));
        //ну очень долго System.out.println("naiveRFib2="+timerAVG(() -> {naiveBigRFib(BigInteger.valueOf(36));}, TimeUnit.NANOSECONDS));
        System.out.println("Через цикл и использование массива cycleFibArr="+timerAVG(() -> {cycleFibArr(36);}, TimeUnit.NANOSECONDS));
        System.out.println("Через цикл и использование 2 переменных cycleFibOptMem="+timerAVG(() -> {cycleFibOptMem(36);}, TimeUnit.NANOSECONDS));


        /*
        //Задания из 3 уровня.
        //Тут ввод с клаиватуры используется поэтому закомментировал, если надо проверить то раскомментируйте

        //Ввести с клавиатуры число и имя, вывести на экран строку:
        //«имя» захватит мир через «число» лет. Му-ха-ха!

        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String sAge = bufferedReader.readLine();
        String name = bufferedReader.readLine();

        int nAge = Integer.parseInt(sAge);

        System.out.print(name + " захватит мир через " + nAge + " лет. Му-ха-ха!");



        //Ввести с клавиатуры отдельно Имя, число1, число2. Вывести надпись: «Имя» получает «число1» через «число2» лет.

         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String name = bufferedReader.readLine();
        String money = bufferedReader.readLine();
        String age = bufferedReader.readLine();
        System.out.println(name + " получает " + money +  " через " + age +  " лет.");

        //Ввести с клавиатуры имя и вывести надпись: name зарабатывает $5,000. Ха-ха-ха!
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        System.out.println(name + " зарабатывает $5,000. Ха-ха-ха!");

        //Ввести с клавиатуры два имени и вывести надпись:
        //name1 проспонсировал name2, и она стала известной певицей.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name1 = reader.readLine();
        String name2 = reader.readLine();
        System.out.println(name1 + " проспонсировал " + name2 + ", и она стала известной певицей.");

         */

    }
}
