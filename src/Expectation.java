import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Expectation {
    int count = 1000;
    double p;
    double p1;
    int[] N;
    public Expectation(){
        N = new int[count];
    }
    public void setP(double p){
        this.p = p;
    }
    public void setP1(double p1){
        this.p1 = p1;
    }
    public double expectedValue(){
        double x = 0;
        for(int i = 0; i < N.length; i++){
            x = x + N[i];
        }
        return x / N.length;
    }
    public void cycle1(){
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        for(double i = 0.0; i < 1.0; i = i + 0.1){
            System.out.println(i);
            if(0.9 < i && i < 1){
                i = 0.98;
            }
            setP(i);
            str1.append(i + " " + algorithm1() + "\n");
            str2.append(i + " " + theory1() + "\n");
        }
        try{
            FileWriter file1 = new FileWriter("практика1.txt");
            FileWriter file2 = new FileWriter("теория1.txt");
            file1.write(str1.toString());
            file1.close();
            file2.write(str2.toString());
            file2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public double theory1(){
        return 1 / (1 - p);
    }
    public double algorithm1(){
        double x;
        for(int i = 0; i < count; i++) {
            int n = 0;
            while (true) {
                n++;
                x = Math.random();
                if (x < (1 - p)) {
                    break;
                }
            }
            N[i] = n;
        }
        return expectedValue();
    }
    public void cycle22(){
        int n = 2;
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        for(double i = 0.0; i < 1; i = i + 0.1){
                setP(i);
                str1.append(i + " " + algorithm2(n) + "\n");
                str2.append(i + " " + theory2(n) + "\n");
                //System.out.println(i);
        }
        try{
            FileWriter file1 = new FileWriter("практика2.txt");
            FileWriter file2 = new FileWriter("теория2.txt");
            file1.write(str1.toString());
            file1.close();
            file2.write(str2.toString());
            file2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void cycle2(){
        int N = 10;
        StringBuilder str1[] = new StringBuilder[N];
        StringBuilder str2[] = new StringBuilder[N];
        for(int i = 0; i < N; i++){
            str1[i] = new StringBuilder();
            str2[i] = new StringBuilder();
        }
        for(int j = 0; j < N; j++) {
            for(double i = 0.0; i < 1.0; i = i + 0.1){
                setP(i);
                str1[j].append(i + " " + (j + 1) + " " + algorithm2(j + 1) + "\n");
                str2[j].append(i + " " + (j + 1) + " " + theory2(j + 1) + "\n");
                System.out.println(i + " " + j);
            }
        }
        try{
            FileWriter file1[] = new FileWriter[N];
            FileWriter file2[] = new FileWriter[N];
            for(int i = 0; i < N; i++){
                file1[i] = new FileWriter("2практика" + i + ".txt");
                file2[i] = new FileWriter("2теория" + i + ".txt");
                file1[i].write(str1[i].toString());
                file1[i].close();
                file2[i].write(str2[i].toString());
                file2[i].close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public double algorithm2(int n){
        double x;
        for(int i = 0; i < count; i++) {
            int k = 0;
            while (true) {
                //k++;
                x = Math.random();
                if ((x < (1 - p)) || (k == n - 1)) {
                    k++;
                    break;
                }
                k++;
            }
            N[i] = k;
        }
        return expectedValue();
    }
    public double theory2(int n){
        double x = Math.pow(p, n) - 1;
        double y = p - 1;
        return x / y;
    }
    public void cycle3(){
        StringBuilder str1[] = new StringBuilder[11]; //тк вероятностей 11
        StringBuilder str2[] = new StringBuilder[11];
        for(int i = 0; i < 11; i++){
            str1[i] = new StringBuilder();
            str2[i] = new StringBuilder();
        }
        int k = 0;
        for(double j = 0.0; j < 1.0; j = j + 0.1) {
            for(double i = 0.0; i < 1.0; i = i + 0.1){
                if(0.9 < i && i < 1){
                    i = 0.98;
                }
                if(0.9 < j && j < 1){
                    j = 0.98;
                }
                setP(i);
                setP1(j);
                str1[k].append(i + " " + j + " " + algorithm3() + "\n");
                str2[k].append(i + " " + j + " " + theory3() + "\n");
                System.out.println(i + " " + j);
            }
            k++;
        }
        try{
            FileWriter file1[] = new FileWriter[11];
            FileWriter file2[] = new FileWriter[11];
            for(int i = 0; i < 11; i++){
                file1[i] = new FileWriter("3практика" + i + ".txt");
                file2[i] = new FileWriter("3теория" + i + ".txt");
                file1[i].write(str1[i].toString());
                file1[i].close();
                file2[i].write(str2[i].toString());
                file2[i].close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public double algorithm3(){
        double x;
        double y;
        for(int i = 0; i < count; i++) {
            int n = 0;
            while (true) {
                n++;
                x = Math.random();
                y = Math.random();
                if (x < (1 - p) && y < (1 - p1)) {
                    break;
                }
            }
            N[i] = n;
        }
        return expectedValue();
    }
    public double theory3(){
        double x = (1 - p) * (1 - p1);
        double y = (1 + (p1 * p) - p1 - p) * (1 + (p1 * p) - p1 - p);
        return x / y;
    }
    public void cycle4(int n){
        StringBuilder str1[] = new StringBuilder[11]; //тк вероятностей 10
        StringBuilder str2[] = new StringBuilder[11];
        for(int i = 0; i < 11; i++){
            str1[i] = new StringBuilder();
            str2[i] = new StringBuilder();
        }
        int k = 0;
        for(double j = 0.0; j < 1.0; j = j + 0.1) {
            for(double i = 0.0; i < 1.0; i = i + 0.1){
                if(0.9 < i && i < 1){
                    i = 0.98;
                }
                if(0.9 < j && j < 1){
                    j = 0.98;
                }
                setP(i);
                setP1(j);
                str1[k].append(i + " " + j + " " + algorithm4(n) + "\n");
                str2[k].append(i + " " + j + " " + theory4(n) + "\n");
                System.out.println(i + " " + j);
            }
            k++;
        }
        try{
            FileWriter file1[] = new FileWriter[11];
            FileWriter file2[] = new FileWriter[11];
            for(int i = 0; i < 11; i++){
                file1[i] = new FileWriter("4практика" + i + ".txt");
                file2[i] = new FileWriter("4теория" + i + ".txt");
                file1[i].write(str1[i].toString());
                file1[i].close();
                file2[i].write(str2[i].toString());
                file2[i].close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public double algorithm4(int n){
        double x;
        double y;
        for(int i = 0; i < count; i++) {
            int k = 0;
            while (true) {
                x = Math.random();
                y = Math.random();
                if ((x < (1 - p) && y < (1 - p1)) || (k == n - 1)) {
                    k++;
                    break;
                }
                k++;
            }
            N[i] = k;
        }
        return expectedValue();
    }
    public double theory4(int n){
        double y = (1 - p) * (1 - p1);
        double k = (p1 - p * p1 + p);
        double i = y *( n * Math.pow(k, n + 1) - (n + 1) * Math.pow(k, n) + 1);
        double x = Math.pow((k - 1), 2);
        double q = n * Math.pow(k, n);
        return q + (i / x);
    }
    public void algorithm5(int tau, double q){
        int allTime = 0;
        StringBuilder str = new StringBuilder();
        int n = 1;
        while(n != 1001) {
            double x = Math.random();
            allTime++;
            str.append("t = " + allTime + " отправка сообщения " + n + "\n");
            allTime = allTime + tau;//убрала +1
            if (x < (1 - q)) {
                str.append("t = " + allTime + " получена положительная квитанция \n");
                n++;
            }
            else {
                str.append("t = " + allTime + " получена отрицательная квитанция \n");
            }
        }
        str.append("коэф использования канала = " + (1 - q) / (1 + tau) + "\n");
        str.append("практика коэф использования канала = " + ((double)n - 1) / ((double)allTime));
        try{
            FileWriter file = new FileWriter("задание24.txt");
            file.write(str.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void algorithm6(int tau, double q){
        int allTime = 0;
        StringBuilder str = new StringBuilder();
        int N = 1;
        int cur = 1;//квитанция
        int[] m = new int[10000000];
        for(int i = 0; i <= tau; i++) {
            m[allTime] = N;
            allTime++;
            str.append("t = " + allTime + " отправка сообщения " + m[allTime - 1] + "\n");
            N++;
        }
        //allTime++;
        while(cur != 1001) {
            double x = Math.random();
            if (x < (1 - q)) {
                str.append("t = " + allTime + " получена положительная квитанция  " + m[allTime - 1 - tau] + "\n");
                if(m[allTime - 1 - tau] == cur) {
                    cur = cur + 1;
                    if(cur == 1001){
                        break;
                    }
                }
            }
            else {
                str.append("t = " + allTime + " получена отрицательная квитанция  " + m[allTime - 1 - tau] + "\n");
                if(cur == m[allTime - tau - 1]) {
                    N = cur;
                }
            }
            allTime++;
            str.append("t = " + allTime + " отправка сообщения " + N + "\n");
            //if(N == 1000){
            //    break;
            //}
            m[allTime - 1] = N;
            N++;
            //allTime++;
        }
        str.append("коэф использования канала = " + (1 - q) / (1 + (q * tau)) + "\n");
        str.append("практика коэф использования канала = " + ((double)cur-1) / ((double)allTime - 2));
        try{
            FileWriter file = new FileWriter("задание25.txt");
            file.write(str.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public double dop72(int tau, double q, int size){//ограниченный 1 буффер
        int countCanal = tau + 1;
        int[] buffer = new int[size];
        int allTime = 0;
        StringBuilder str = new StringBuilder();
        int N = 1;
        int[] NCalal = new int[countCanal];
        int cur = 1;
        Vector<Integer> ma = new Vector<>();
        for(int i = 0; i <= tau; i++) {
            ma.add(N);
            allTime++;
            str.append("t = " + allTime + " отправка сообщения " + ma.get(allTime - 1) + " по каналу " + (i + 1) + "\n");
            NCalal[i] = N;
            N++;
        }
        int i = 0;
        while(N != 1001) {
            double x = Math.random();
            if (x < (1 - q)) {
                str.append("t = " + allTime + " получена положительная квитанция  " + ma.get(allTime - 1 - tau) + "\n");
                if (cur < ma.get(allTime - 1 - tau)) {
                    int w = empty(buffer);
                    if (w != -1) {
                        buffer[w] = ma.get(allTime - 1 - tau);
                        str.append("сохранено сообщение " + ma.get(allTime - 1 - tau) + " в буфере \n");
                        NCalal[i] = N;
                        N++;
                    }
                    else{
                        NCalal[i] = ma.get(allTime - 1 - tau);
                    }
                }
                if (ma.get(allTime - 1 - tau) == cur) {
                    str.append("вывод сообщения " + ma.get(allTime - 1 - tau) + "\n");
                    cur = cur + 1;
                    NCalal[i] = N;
                    N++;
                    if(search(buffer, cur)) {
                        while (search(buffer, cur)) {
                            str.append("вывод сообщения " + cur + " из буфера \n");
                            cur = cur + 1;
                        }
                        clear(buffer);
                    }
                }
            } else {
                str.append("t = " + allTime + " получена отрицательная квитанция  " + ma.get(allTime - 1 - tau) + "\n");
                NCalal[i] = ma.get(allTime - 1 - tau);
            }
            allTime++;
            str.append("t = " + allTime + " отправка сообщения " + NCalal[i] + " по каналу " + (i + 1) + "\n");
            ma.add(NCalal[i]);
            i = (i + 1) % countCanal;//3
        }
        str.append("коэф использования канала = " + (1 - q) + "\n");
        str.append("практика коэф использования канала = " + ((double)N - 1) / ((double)allTime));
        System.out.println("коэф использования канала = " + (1 - q));
        System.out.println("практика коэф использования канала = " + ((double)N - 1) / ((double)allTime));
        System.out.println("N = " + countN(ma, N));
        try{
            FileWriter file = new FileWriter("доп72.txt");
            file.write(str.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ((double)N - 1) / ((double)allTime);
    }
    public boolean search(int[] buffer, int value){
        for(int i = 0; i < buffer.length; i++){
            if(buffer[i] == value){
                return true;
            }
        }
        return false;
    }
    public void clear(int[] a){
        for(int i = 0; i < a.length; i++) {
            a[i] = 0;
        }
    }
    public int empty(int[] a){
        for(int i = 0; i < a.length; i++){
            if(a[i] == 0){
                return i;
            }
        }
        return -1;
    }
    public void dop7(int tau, double q){
        int countCanal = tau + 1;
        Vector<Integer>[] buffer = (Vector<Integer>[]) new Vector[countCanal];
        for(int i = 0; i < buffer.length; i++) {
            buffer[i] = new Vector<>();
        }
        int allTime = 0;
        StringBuilder str = new StringBuilder();
        int N = 1;
        int[] NCalal = new int[countCanal];
        int cur = 1;
        int[] m = new int[1000000];
        for(int i = 0; i <= tau; i++) {
            m[allTime] = N;
            allTime++;
            str.append("t = " + allTime + " отправка сообщения " + m[allTime - 1] + " по каналу " + (i + 1) + "\n");
            NCalal[i] = N;
            N++;
        }//N номер сообщения которое собираемся отправлять
        int i = 0;
        while(N != 1000) {
            double x = Math.random();
            if (x < (1 - q)) {
                str.append("t = " + allTime + " получена положительная квитанция  " + m[allTime - 1 - tau] + "\n");
                if (cur < m[allTime - 1 - tau]) {
                    buffer[i].add(m[allTime - 1 - tau]);
                    str.append("сохранено сообщение " + m[allTime - 1 - tau] + " в буфере " + (i + 1) + "\n");
                    NCalal[i] = N;
                    N++;
                }
                if (m[allTime - 1 - tau] == cur) {
                    str.append("вывод сообщения " + m[allTime - 1 - tau] + "\n");
                    cur = cur + 1;
                    NCalal[i] = N;
                    N++;
                    while (search(buffer, cur) != -1) {
                        str.append("вывод сообщения " + cur + " из буфера " + (search(buffer, cur) + 1) + "\n");
                        cur = cur + 1;
                    }
                    for(int j = 0; j < buffer.length; j++){
                        buffer[j].clear();
                    }
                }
            } else {
                str.append("t = " + allTime + " получена отрицательная квитанция  " + m[allTime - 1 - tau] + "\n");
                NCalal[i] = m[allTime - 1 - tau];
            }
            allTime++;
            str.append("t = " + allTime + " отправка сообщения " + NCalal[i] + " по каналу " + (i + 1) + "\n");
            m[allTime - 1] = NCalal[i];
            i = (i + 1) % 3;
        }
        str.append("коэф использования канала = " + (1 - q) + "\n");
        str.append("практика коэф использования канала = " + ((double)N - 1) / ((double)allTime));
        System.out.println("коэф использования канала = " + (1 - q));
        System.out.println("практика коэф использования канала = " + ((double)N - 1) / ((double)allTime));
        System.out.println("N = " + countN(m, N));
        try{
            FileWriter file = new FileWriter("доп7.txt");
            file.write(str.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int search(Vector<Integer>[] buffer, int value){
        for(int i = 0; i < buffer.length; i++){
            for(int j = 0; j < buffer[i].size(); j++){
                if(value == buffer[i].get(j)){
                    return i;
                }
            }
        }
        return -1;
    }
    public int countN(Vector<Integer> m, int N){
        int count[] = new int[N + 1];
        int sum = 0;
        int  countM = 0;
        for(int i = 0; i < m.size(); i++){
            count[m.get(i)]++;
        }
        for(int i = 2; i < count.length - 1; i++){
            sum = sum + (count[i]-1);
            if(count[i]-1 != 0){
                countM++;
            }
        }
        if(sum == 0){
            return 0;
        }
        else {
            return sum / countM;
        }
    }
    public int countN(int[] m, int N){
        int count[] = new int[N + 1];
        int sum = 0;
        int  countM = 0;
        for(int i = 0; i < m.length; i++){
            count[m[i]]++;
        }
        for(int i = 1; i < count.length - 1; i++){
            sum = sum + (count[i]-1);
            if(count[i]-1 != 0){
                countM++;
            }
        }
        if(sum == 0){
            return 0;
        }
        else {
            return sum / countM;
        }
    }
    public void graphWithReturnTauConst(int tau){
        StringBuilder str = new StringBuilder();
        for(double p = 0; p < 0.95; p = p + 0.1){
            double a = (1 - p) / (1 + (p * tau));
            str.append(p + " " + a + "\n");
        }
        try{
            FileWriter file = new FileWriter("r t = " + tau + ".txt");
            file.write(str.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void graphWithReturnPConst(double p){
        StringBuilder str = new StringBuilder();
        for(int tau = 2; tau < 7; tau++){
            double a = (1 - p) / (1 + (p * tau));
            str.append(tau + " " + a + "\n");
        }
        try{
            FileWriter file = new FileWriter("r p = " + p + ".txt");
            file.write(str.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void TConstGraph(){
        for(int tau = 2; tau < 7; tau++) {
            System.out.println("tau " + tau);
            StringBuilder str = new StringBuilder();
            for (double p = 0; p < 0.95; p = p + 0.1) {
                System.out.println("p " + p);
                double a = dop72(tau, p, 1);
                str.append(p + " " + a + "\n");
            }
            try {
                FileWriter file = new FileWriter("t = " + tau + ".txt");
                file.write(str.toString());
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void PConstGraph(){
        double[] p = new double[]{0.1, 0.3, 0.6, 0.9};
        for(int i = 0; i < p.length; i++) {
            System.out.println("p " + p[i]);
            StringBuilder str = new StringBuilder();
            for(int tau = 2; tau < 7; tau++) {
                System.out.println("tau " + tau);
                double a = dop72(tau, p[i], 1);
                str.append(tau + " " + a + "\n");
            }
            try {
                FileWriter file = new FileWriter("p = " + p[i] + ".txt");
                file.write(str.toString());
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Expectation x = new Expectation();
        //x.cycle1(); //среднее число передач при неограниченном числе повторных передач
        x.cycle2(); //среднее число передач при ограниченном числе повторных передач(надо установить n для cycle22())
        //x.cycle3();
        //x.cycle4(10);
        //x.algorithm5(2, 0.4);
        //x.algorithm6(2, 0.4);//
        //x.graphWithReturnTauConst(6);
        //x.graphWithReturnPConst(0.9);
        //x.dop7(2, 0.9);
        //x.dop72(2, 0.4, 1);
        //x.PConstGraph();
    }
}
