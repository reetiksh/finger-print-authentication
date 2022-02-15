package easycbs.userdesig.function;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import easycbs.common.logger.BMLogger;
import easycbs.webservice.rest.AccountInfo;

import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;


public class BioAuthenticate {
	BMLogger logger = new BMLogger(BioAuthenticate.class);
static HashMap<String, String> map = new HashMap<String, String>();
	public String authenticate(String usercode){
		String res="success";
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
		    Date date = new Date();
		    
		    System.out.println(formatter.format(date));
			map.put(usercode, formatter.format(date));
			
			logger.info("authenticated:" + usercode );
			
		}
		catch(Exception e){
			e.printStackTrace() ;
			res="error";
			logger.info("authentication error:" + e );
		}
		return res;
	}

	public String getauthenticate(String usercode) {
			String res="success";
			try{

				if (map.containsKey(usercode)) { 
					String logintime = map.get(usercode);	

					SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
					Date date1=formatter.parse(logintime);
					Date date = new Date();
					Date curtime=formatter.parse(formatter.format(date));
					System.out.println(curtime);
					long difference_In_Time= curtime.getTime() - date1.getTime();
					long difference_In_Minutes= (difference_In_Time/ (1000 * 60));
					if(difference_In_Minutes>30){
						res="time out";
					}else {
						res="success";
					}
				} else{ 
					res="wait";
				}
			}
			catch(Exception e){
				e.printStackTrace() ;
				res="error";
				logger.info("authentication error:" + e );
			}
			return res;
		}

}
