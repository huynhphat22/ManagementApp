package RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Model.DAO.StaffDAO;
import Model.DTO.Staff;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;
import Model.MODEL.PasswordChange;

@RestController
public class RestStaffController {

	@Autowired
	private StaffDAO staffDAO;

	@RequestMapping(value = "/api/staff", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Staff>> findAll() {
		try {
			return new ResponseEntity<>(this.staffDAO.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/api/staff", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Staff staff) {
		try {
			System.out.println(staff.getPassword());
			String password = new BCryptPasswordEncoder().encode(staff.getPassword());
			staff.setPassword(password);
			this.staffDAO.save(staff);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/staff", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Staff staff) {
		Staff staff1 = this.staffDAO.findById(staff.getStaffId());
		if (staff1 == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		try {
			staff.setPassword(staff1.getPassword());
			this.staffDAO.update(staff);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/api/staff/{id}", method = RequestMethod.GET)
	public ResponseEntity<Staff> findById(@PathVariable int id) {
		try {
			return new ResponseEntity<>(this.staffDAO.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/api/staff/reset-password/{id}", method = RequestMethod.POST)
	public ResponseEntity<Void> resetPassword(@PathVariable("id") int staffId) {
		try {
			Staff staff = this.staffDAO.findById(staffId);
			if (staff == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			String password = new BCryptPasswordEncoder().encode("123456");
			staff.setPassword(password);
			this.staffDAO.update(staff);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/api/staff/change-password", method = RequestMethod.POST)
	public ResponseEntity<Void> changePassword(@RequestBody PasswordChange passwordChange) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Staff staff = this.staffDAO.findByUsername(username);
		BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
		if (staff == null || !bpe.matches(passwordChange.getPassword(), staff.getPassword())
				|| !passwordChange.getNewPassword().equals(passwordChange.getReNewPassword())) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			String password = new BCryptPasswordEncoder().encode(passwordChange.getNewPassword());
			staff.setPassword(password);
			this.staffDAO.update(staff);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@RequestMapping(value = "/api/staff/pagination", method = RequestMethod.POST)
	public ResponseEntity<Page> paginate(@RequestBody PageQuery pageQuery) {
		try {
			return new ResponseEntity<>(this.staffDAO.paginateStaff(pageQuery), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
