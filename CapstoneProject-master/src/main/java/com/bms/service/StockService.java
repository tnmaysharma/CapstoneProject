package com.bms.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.domain.ProductList;
import com.bms.repostiries.JpaProductList;

@Service
@SuppressWarnings("unchecked")
public class StockService {

	@Autowired
	private JpaProductList jpaProductList;

	public List<ProductList> getList() {
		return jpaProductList.findAll();
	}

	public List<ProductList> insertList(String input) throws ParseException {

		Object obj = new JSONParser().parse(input);
		JSONObject json = (JSONObject) obj;

		System.out.println("INSERT PROCESS");

		jpaProductList.insert(new ProductList((String) json.get("productId"), (String) json.get("productName"),
				(String) json.get("Quantity")));
		return jpaProductList.findAll();
	}

	public ProductList updateList(String id, String input) throws ParseException {

		ProductList productList = jpaProductList.findOne(id);

		System.out.println(productList);

		Object obj = new JSONParser().parse(input);
		JSONObject json = (JSONObject) obj;
		productList.setQuantity((String) json.get("Quantity"));

		System.out.println(productList);

		jpaProductList.save(productList);
		productList = jpaProductList.findOne(id);

		System.out.println(productList);
		
		return productList;
	}

	public JSONObject deleteList(String id) {
		jpaProductList.delete(id);
		
		JSONObject obj = new JSONObject();
		obj.put(id.toString(), "Delete Success");
		
		return obj;
	}

}
