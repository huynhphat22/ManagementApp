package Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import Model.DAO.StaffDAO;
import Model.DTO.Staff;

@Service
public class StaffDetailsService implements UserDetailsService {

	@Autowired
	private StaffDAO staffDAO;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Staff staff = this.staffDAO.findById(9);
		if(staff == null){
			throw new UsernameNotFoundException("Email " + email + " not found");
		}
		String role = staff.getRole();
		GrantedAuthority authority = new SimpleGrantedAuthority(role);
		List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
		grantList.add(authority);
		return new User(staff.getUsername(), staff.getPassword(),staff.getFlags(), true, true, true, grantList);
	}
	
}
