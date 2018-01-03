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
import org.springframework.transaction.annotation.Transactional;

import Model.DAO.StaffDAO;
import Model.DTO.Staff;

public class StaffDetailsService implements UserDetailsService {

	@Autowired
	private StaffDAO staffDAO;

	public StaffDAO getStaffDAO() {
		return staffDAO;
	}

	public void setStaffDAO(StaffDAO staffDAO) {
		this.staffDAO = staffDAO;
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Staff staff = staffDAO.findByUsername(username);
		
        if (staff == null) {
            throw new UsernameNotFoundException("User "+ username + "  was not found in the database");
        }
        System.out.println(staff.getUsername()); 
        // [USER,ADMIN,..]
        List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority(staff.getRole());
        grantList.add(authority);
         
        System.out.println(staff.getPassword());
        User userDetails =  new User(staff.getUsername(), //
        		staff.getPassword(), staff.getFlags(), true, true, true, grantList);
 
        return userDetails;
	}
}
