package com.pltest.demo0214;

import java.util.Scanner;

/**
 * @author YueShuai
 * @date 2020-02-14
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class TestJavaClass {

    public static void main(String[] arr) {
        boolean isFindBook = false;
        Book[] bookList = {
                new Book("11", "鲁迅1"),
                new Book("12", "鲁迅2"),
                new Book("13", "鲁迅3"),
                new Book("14", "鲁迅4"),
                new Book("25", "鲁迅5"),
                new Book("26", "鲁迅6"),
        };
        System.out.println("请输入书名");
        Scanner scanner = new Scanner(System.in);
        String inputBookName = scanner.next();
        for (int i = 0; i <bookList.length;i++){
            if (bookList[i].getBookName().contains(inputBookName)){
                isFindBook = true;
                System.out.println("找到书籍 ： "+bookList[i].toString());
            }
        }
        if (!isFindBook){
            System.out.println("没有相关书籍");
        }

    }
}
