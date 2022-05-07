package com.jung.bungu;

import com.jung.bungu.macha.Clean;
import com.jung.bungu.macha.PojangMacha;
import com.jung.bungu.macha.bungubbang;
import com.jung.bungu.service.BunguService;
import com.jung.bungu.serviceImpl.BunguServiceImpl;
import com.jung.bungu.userVO.UserInfo;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	BunguService service = new BunguServiceImpl();
    	PojangMacha macha = new PojangMacha();
    	UserInfo vo = new UserInfo();
    	bungubbang make = new bungubbang();
    	Clean cl = new Clean();
    	
    	
    	macha.start();
    	
    	
    	
    	
    	
    	
    	
    
    }
}
