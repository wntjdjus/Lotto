package com.jutudy.lotto.web;

import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping("/random-lotto")
    public int[] hello() {

        int[] lotto = new int[7];
        boolean[] chk = new boolean[46];
        
        Random random = new Random();
        
        for(int i = 0 ; i<7;i++){
            
            while(true){
                
                int x = random.nextInt(45)+1;
                if(chk[x]){
                    continue;
                }else{
                    chk[x]=true;
                    lotto[i]=x;
                    break;
                }
            }
        }
        Arrays.sort(lotto,0,6);

        return lotto;
    }
}
