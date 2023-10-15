package com.jdc.mkt.service;

import static com.jdc.mkt.utils.MyConnection.getConnection1;

import java.util.ArrayList;
import java.util.List;

import com.jdc.mkt.entity.Contact;
import com.jdc.mkt.utils.MyConnection;

public class ContactService implements CrudOperation<Contact> {

	@Override
	public int insert(Contact contact) {

		String query = "insert into contact_tbl (primary_contact, secondary_contact, email) values (?,?,?)";

		try (var con = MyConnection.getConnection1(); 
				var stmt = con.prepareStatement(query);) {

			stmt.setString(1, contact.getPrimaryContact());
			stmt.setString(2, contact.getSecondaryContact());
			stmt.setString(3, contact.getEmail());

			return stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int update(Contact contact) {

		String query = "update contact_tbl set primary_contact = ?, secondary_contact = ?, email = ? where id = ? and active = 1";

		try (var con = MyConnection.getConnection1(); 
				var stmt = con.prepareStatement(query);) {

			stmt.setString(1, contact.getPrimaryContact());
			stmt.setString(2, contact.getSecondaryContact());
			stmt.setString(3, contact.getEmail());
			stmt.setInt(4, contact.getId());

			return stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int delete(Contact contact) {
		
		String query = "update contact_tbl set active = ? where id = ? and active = 1";

		try (var con = MyConnection.getConnection1(); 
				var stmt = con.prepareStatement(query);) {

			stmt.setBoolean(1, contact.isActive());
			stmt.setInt(2, contact.getId());

			return stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public List<Contact> select(Contact contact) {
		
		StringBuilder sb = new StringBuilder("select * from contact_tbl where 1=1");
		List<Object> temp = new ArrayList<Object>();
		
		if(contact.getId() > 0) {
			sb.append(" and id = ?");
			temp.add(contact.getId());
		}
		
		if(null != contact.getPrimaryContact()) {
			sb.append(" and primary_contact = ?");
			temp.add(contact.getPrimaryContact());
		}
		
		if(null != contact.getSecondaryContact()) {
			sb.append(" and secondary_contact = ?");
			temp.add(contact.getSecondaryContact());
		}
		
		if(null != contact.getEmail()) {
			sb.append(" and email = ?");
			temp.add(contact.getEmail());
		}
		
		
		return getContacts(sb.toString(), temp);
	}
	
	private List<Contact> getContacts(String sql, List<Object> temp){
		
		List<Contact> contactList = new ArrayList<Contact>();
		
		try(var con = getConnection1();
				var stmt = con.prepareStatement(sql.toString())){
			
			for(int i = 0; i < temp.size(); i ++) {
				stmt.setObject(i+1, temp.get(i));
			}
			
			var rs = stmt.executeQuery();
			while(rs.next()) {
				var c = new Contact();
				c.setId(rs.getInt("id"));
				c.setPrimaryContact(rs.getString("primary_contact"));
				c.setPrimaryContact(rs.getString("secondary_contact"));
				c.setPrimaryContact(rs.getString("email"));
				c.setActive(rs.getBoolean("active"));
				
				contactList.add(c);
			}			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return contactList;
	}

}
