package kr.or.ddit.hello;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


/*
 * java - gui swing, awt, java fx, swt
 */

@ContextConfiguration(locations = {"classpath:/kr/or/ddit/config/spring/application-context.xml",
						"classpath:/kr/or/ddit/config/spring/root-context.xml"})
@WebAppConfiguration	// 스프링 환경을 Web기반의 application Context로 생성
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloControllerTest {
	
//	@Resource(name="helloController")
	
	// 스프링빈중에 대입 가능한 타입의 스프링 빈을 주입한다.
	// 만약 동일하 타입의 스프링 빈이 여러개 있을 경우 @Qulifier 어노테이션을 통해
	// 특정 스프링 빈의 이름을 지정할 수 있다.
	//	==> @Resource 이노테이션 하나를 사용 했을 때와 동일하다.
	@Autowired	
	// spring bean을 안적는다.
	private HelloController helloController;
	
	@Test
	public void helloControllerTest() {
		
		assertNotNull(helloController);
		
	}

}
