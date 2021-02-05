package kr.or.ddit.mvc.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.or.ddit.user.model.UserVo;

@RequestMapping("ajax")
@Controller
public class AjaxController {
	
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	public AjaxController() {
		
		logger.debug("ajax 진입");
	}
	
	@ModelAttribute(name = "rangers")
	public List<String> rangers() {
		
	List<String> rangers = new ArrayList<String>();
		
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("jame");
		rangers.add("sally");
		rangers.add("moon");
		
		return rangers;
		
	}
	
	@RequestMapping("view")
	public String view() {
		
		logger.debug("ajax/ajaxView 진입");
		
		return "ajax/ajaxView";
	}
	
	@RequestMapping("form")
	public String form( UserVo userVo ) {
		
		logger.debug("ajax/form 진입");
		logger.debug("userVo : {} ", userVo);
		
		return "jsonView";
	}
	
	
	
	// localhost/spring/ajax/jsonView
	@RequestMapping("jsonView")
	public String jsonView(  ) {
		
		
		return "jsonView";
	}
	
	// 개발자가 직접 지정한 녀석을 반환
	@RequestMapping("jsonViewViewObj")
	public View jsonViewViewOjb() {
		
		return new MappingJackson2JsonView();
	}
	
	@RequestMapping("jsonViewMav")
	public ModelAndView jsonViewMav() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("jsonView");
		
		return mav;
		
	}
	
	
	
}
