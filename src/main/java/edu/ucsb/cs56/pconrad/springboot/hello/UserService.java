package edu.ucsb.cs56.pconrad.springboot.hello;
import org.springframework.stereotype.Service;


@Service
public class UserService{
    private String key;

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }
}