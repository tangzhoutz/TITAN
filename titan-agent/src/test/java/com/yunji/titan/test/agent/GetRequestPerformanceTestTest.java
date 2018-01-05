package com.yunji.titan.test.agent;

import org.junit.Test;

import com.yunji.titan.agent.config.HttpConnectionManager;
import com.yunji.titan.agent.stresstest.HttpPostRequestStresstest;

/**
 * 测试类
 *
 * @author gaoxianglong
 */
public class GetRequestPerformanceTestTest {
	public @Test void testGetRequest() {
		// HttpGetRequestStresstest g = new HttpGetRequestStresstest();
		// g.httpConnectionManager = new HttpConnectionManager();
		// g.httpConnectionManager.init();
		// System.out.println(
		// g.runGetStresstest("http://st.yunjiweidian.com/yunjibuyer/subject/getFullCoupon.json",
		// null, null));

		HttpPostRequestStresstest p = new HttpPostRequestStresstest();
		p.httpConnectionManager = new HttpConnectionManager();
		p.httpConnectionManager.init();
		// OutParamBean oBean =
		// p.runPostStresstest("http://172.16.5.106:8081/SpringMVCDemo/titan/postsecond",
		// null, null,
		// false);
		// System.out.println(oBean.getErrorCode());
	}
}