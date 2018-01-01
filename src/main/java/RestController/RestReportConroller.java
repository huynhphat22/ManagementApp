package RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.DAO.DateRevenueDAOImpl;
import Model.MODEL.ReportQuery;
import Model.MODEL.TotalReportModel;

@RestController
@RequestMapping(value="/api/report")
public class RestReportConroller {

	@Autowired
	private DateRevenueDAOImpl	dateRevenueDAO;
	
	
	@RequestMapping(value="/totalrevenue", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> getReportResult(@RequestBody ReportQuery param){
		
		ObjectMapper map = new ObjectMapper();
		List<TotalReportModel> result = dateRevenueDAO.getDateRevenue(param);
		
		if (result.isEmpty())
			return new ResponseEntity<List<TotalReportModel>>(HttpStatus.BAD_REQUEST);
		else {
			try {
				return new ResponseEntity<>(map.writeValueAsString(result), HttpStatus.OK);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}

}
