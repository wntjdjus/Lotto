package com.jutudy.lotto.web;

import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.web.bind.annotation.RestController
public class RestController {
    public static class Node{
        int a;
        String b;

        public Node(int a, String b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public String getB() {
            return b;
        }

        public void setA(int a) {
            this.a = a;
        }

        public void setB(String b) {
            this.b = b;
        }
    }

    @GetMapping("/lotto")
    public Node hello() {

        Node node=new Node(1,"11111");

        return node;
    }
}
