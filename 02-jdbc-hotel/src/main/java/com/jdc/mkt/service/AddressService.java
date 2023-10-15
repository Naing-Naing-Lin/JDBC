package com.jdc.mkt.service;

import com.jdc.mkt.entity.Address;
import static com.jdc.mkt.utils.MyConnection.getConnection1;

import java.util.ArrayList;
import java.util.List;;

public class AddressService implements CrudOperation<Address> {

	@Override
	public int insert(Address address) {
		
		String query = "insert into address_tbl (street,township,city) values (?,?,?)";
				
		try(var con = getConnection1();
				var stmt = con.prepareStatement(query)){
			
			stmt.setString(1, address.getStreet());
			stmt.setString(2, address.getTownship());
			stmt.setString(3, address.getCity());
			
			return stmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Address address) {
		String query = "update address_tbl set street = ?, township = ?, city = ? where id = ? and active = 1";
		
		try(var con = getConnection1();
				var stmt = con.prepareStatement(query)){
			
			stmt.setString(1, address.getStreet());
			stmt.setString(2, address.getTownship());
			stmt.setString(3, address.getCity());
			stmt.setInt(4, address.getId());
			
			return stmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(Address address) {
		String query = "update address_tbl set active = ? where id = ? and active = 1";
		
		try(var con = getConnection1();
				var stmt = con.prepareStatement(query)){
			
			stmt.setBoolean(1, address.isActive());
			stmt.setInt(2, address.getId());
			
			return stmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Address> select(Address address) {
		
		StringBuilder sb = new StringBuilder("select * from address_tbl where 1=1");
		List<Object> temp = new ArrayList<Object>();
		
		if(address.getId() > 0) {
			sb.append(" and id = ?");
			temp.add(address.getId());
		}
		
		if(null != address.getStreet()) {
			sb.append(" and street = ?");
			temp.add(address.getStreet());
		}
		
		if(null != address.getTownship()) {
			sb.append(" and township = ?");
			temp.add(address.getTownship());
		}
		
		if(null != address.getCity()) {
			sb.append(" and city = ?");
			temp.add(address.getCity());
		}
		
		return getAddresses(sb.toString(), temp);
	}
	
	private List<Address> getAddresses(String sql, List<Object> temp){
		
		List<Address> addressList = new ArrayList<Address>();
		
		try(var con = getConnection1();
				var stmt = con.prepareStatement(sql.toString())){
			
			for(int i = 0; i < temp.size(); i ++) {
				stmt.setObject(i+1, temp.get(i));
			}
			
			var rs = stmt.executeQuery();
			while(rs.next()) {
				var a = new Address();
				a.setId(rs.getInt("id"));
				a.setStreet(rs.getString("street"));
				a.setTownship(rs.getString("township"));
				a.setCity(rs.getString("city"));
				a.setActive(rs.getBoolean("active"));
				
				addressList.add(a);
			}			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return addressList;
	}

}
