package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.repository.IStockRepository;

@Service
public class StockService {
	
	@Autowired
	IStockRepository stock_rep;
	
	public int getCurrent_quantity(long prod_id){
		
		return stock_rep.find_product_stock(prod_id).getProduct_curent_quantity()		;
	
	}
	
	public int Max_Quantity_to_add(long prod_id){
		System.err.println(stock_rep.find_product_stock(prod_id).getProduct_max_qunatity() - this.getCurrent_quantity(prod_id));
		return stock_rep.find_product_stock(prod_id).getProduct_max_qunatity() - this.getCurrent_quantity(prod_id);
		
		 
		
	}
	//returns -1 if total quantity > quantity max| total quantity
	public int add_Quantity_to_stock(int quantity , long prod_id){
		
		if(this.Max_Quantity_to_add( prod_id)>=quantity){
		 Stock s = stock_rep.find_product_stock(prod_id);
		 s.setProduct_curent_quantity
				(this.getCurrent_quantity(prod_id)+quantity);
		 System.err.println(s.getProduct_curent_quantity());
		 stock_rep.save(s);
		 return stock_rep.find_product_stock(prod_id).getProduct_curent_quantity();
		}
		else
		return -1	;
		
		
	}
}
