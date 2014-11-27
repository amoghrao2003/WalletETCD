package com.voldy.etcd.wallet;

import java.net.URI;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.justinsb.etcd.EtcdClient;
import com.justinsb.etcd.EtcdClientException;
import com.justinsb.etcd.EtcdResult;

@RestController
public class WalletController {
	@RequestMapping(value = "/ap1/v1/counter", method = RequestMethod.GET)
	public String getCounter() {
		return fetchCounterFromEtcd();
	}
	
	public String fetchCounterFromEtcd(){
		//String counter = "";
				
		String prefix = "/unittest-" + UUID.randomUUID().toString();
		//EtcdClient client = new EtcdClient(URI.create("http://127.0.0.1:4001/"));
		
		
		EtcdClient client = new EtcdClient(URI.create("http://localhost:4001/"));
		String key = prefix + "/mykey";
		EtcdResult result = null;
		try {
			result = client.get(key);
		} catch (EtcdClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Action is :"+result.action);
		System.out.println("The value for the key myKey :"+ result.node.value);
		return result.node.value;
	}
	
}
