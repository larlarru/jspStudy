package kr.or.ddit.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 
 */

//@RequestMapping("hello")
@Controller
public class HelloController {
	
	// localhost/hello/view ==> localhost/view(위의 requestMapping주석치게되면)
	// 그러면 localhost/hello/view하고 싶으면 @RequestMapping("hello/view") 이런식으로 추가한다.
	// localhost/hello/view.do
	@RequestMapping("view.do")
	public String view() {
		return "hello";
	}
	
}
