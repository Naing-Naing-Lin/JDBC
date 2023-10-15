package com.jdc.mkt.service;

import java.util.ArrayList;
import java.util.List;

import com.jdc.mkt.entity.dto.CustomerCountByHotel;
import com.jdc.mkt.utils.MyConnection;

public class CustomerService {

	public List<CustomerCountByHotel> getCustomers(String name){
		
		List<CustomerCountByHotel> dtoLists = new ArrayList<CustomerCountByHotel>();
		String query ="""
				select c.name as name, count(tr.customer_id) as qty from transaction_tbl tr
				join customer_tbl c on tr.customer_id = c.id
				join room_tbl r on tr.room_id = r.id
				join hotel_tbl h on r.hotel_id = h.id
				where h.name = ?
				group by c.name;
				""";
		
		try (var con = MyConnection.getConnection1();
				var stmt = con.prepareStatement(query)) {
			
			stmt.setString(1, name);
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				var c = new CustomerCountByHotel(
						rs.getString("name"), 
						rs.getLong("qty"));
				
				dtoLists.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dtoLists;
	}
}
