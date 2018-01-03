package RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Model.DAO.MenuDepartmentDAO;
import Model.DAO.OrderDetailDAO;
import Model.DAO.OrderFoodDAO;
import Model.DAO.StaffDAO;
import Model.DTO.MenuDepartment;
import Model.DTO.MenuDepartmentId;
import Model.DTO.OrderDetail;
import Model.DTO.OrderDetailId;
import Model.DTO.OrderFood;
import Model.MODEL.CartInfo;
import Model.MODEL.FoodInfo;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

@RestController
public class RestOrderFoodController {

	@Autowired
	StaffDAO staffDAO;

	@Autowired
	OrderFoodDAO orderFoodDAO;

	@Autowired
	OrderDetailDAO orderDetailDAO;

	@Autowired
	MenuDepartmentDAO menuDepartmentDAO;

	@RequestMapping(value = "/api/orderFood", method = RequestMethod.GET)
	public ResponseEntity<Iterable<OrderFood>> findAll() {
		try {

			return new ResponseEntity<>(this.orderFoodDAO.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/api/orderFood", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody CartInfo cartInfo) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		try {
			int departmentId = this.staffDAO.findByUsername(username).getDepartmentId();
			OrderFood orderFood = cartInfo.getOrderInfo();
			orderFood.setDepartmentId(departmentId);

			this.orderFoodDAO.save(orderFood);

			List<FoodInfo> listFoodInfo = cartInfo.getListFoodInfo();
			orderFood.setDateCreated(new Date());
			orderFood.setFlags(true);
			System.out.println("address : " + orderFood.getAddressDelivery());

			int total = 0;

			try {
				for (FoodInfo foodInfo : listFoodInfo) {
					MenuDepartment md = this.menuDepartmentDAO
							.findById(new MenuDepartmentId(departmentId, foodInfo.getFoodId()));

					OrderDetailId orderDetailId = new OrderDetailId(orderFood.getOrderId(), foodInfo.getFoodId());
					OrderDetail orderDetail = new OrderDetail(orderDetailId, foodInfo.getQuantity(), "ok", new Date(),
							true);
					this.orderDetailDAO.save(orderDetail);
					total = total + (md.getPrice() * orderDetail.getQuantity());
					System.out.println("Thanh cong");
				}
				orderFood.setTotalPrice(total);
				this.orderFoodDAO.update(orderFood);
			} catch (Exception e) {

			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/orderFood", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody OrderFood orderFood) {
		OrderFood of = this.orderFoodDAO.findById(orderFood.getOrderId());
		if (of == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			orderFood.setOrderType(of.getOrderType());
			orderFood.setDateCreated(new Date());
			this.orderFoodDAO.update(orderFood);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/api/orderFood/{id}", method = RequestMethod.GET)
	public ResponseEntity<OrderFood> findById(@PathVariable int id) {
		try {
			return new ResponseEntity<>(this.orderFoodDAO.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/api/orderFood/pagination", method = RequestMethod.POST)
	public ResponseEntity<Page> paginate(@RequestBody PageQuery pageQuery) {
		try {
			return new ResponseEntity<>(this.orderFoodDAO.paginateOrderFood(pageQuery), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/api/orderFood/department", method = RequestMethod.POST)
	public ResponseEntity<Page> paginateD(@RequestBody PageQuery pageQuery) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		System.out.println(username);
		try {
			int departmentId = this.staffDAO.findByUsername(username).getDepartmentId();
			return new ResponseEntity<>(this.orderFoodDAO.paginateOrderFoodByDepartment(pageQuery, departmentId),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/api/orderFood/switchboard", method = RequestMethod.POST)
	public ResponseEntity<Page> paginateDSW(@RequestBody PageQuery pageQuery) {
		try {
			return new ResponseEntity<>(this.orderFoodDAO.paginateOrderFoodByDepartmentSwitchBoard(pageQuery),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/api/orderFood/orderDetail/{orderId}", method = RequestMethod.GET)
	public ResponseEntity<Iterable<OrderDetail>> paginateDd(@PathVariable("orderId") int orderId) {
		try {
			return new ResponseEntity<>(this.orderDetailDAO.findByOrderId(orderId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
