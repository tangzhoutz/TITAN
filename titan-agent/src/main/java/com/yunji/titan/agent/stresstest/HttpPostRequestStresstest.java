/*
 * Copyright (C) 2015-2020 yunjiweidian
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.yunji.titan.agent.stresstest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yunji.titan.agent.adapter.BaseStresstestAdapter;
import com.yunji.titan.agent.bean.bo.OutParamBO;
import com.yunji.titan.agent.config.HttpConnectionManager;
import com.yunji.titan.agent.utils.ParamUtils;
import com.yunji.titan.utils.ContentType;
import com.yunji.titan.utils.UrlEncoder;

/**
 * 执行POST请求类型压测
 * 
 * @author gaoxianglong
 */
@Service("httpPostRequestStresstest")
public class HttpPostRequestStresstest extends BaseStresstestAdapter {
	@Resource
	public HttpConnectionManager httpConnectionManager;
	private Logger log = LoggerFactory.getLogger(HttpPostRequestStresstest.class);

	@Override
	public OutParamBO runPostStresstest(String url, String outParam, String param, ContentType contentType,
			String charset) {
		OutParamBO outParamBO = new OutParamBO();
		if (!StringUtils.isEmpty(url)) {
			HttpEntity entity = null;
			CloseableHttpResponse httpResponse = null;
			String headers = null;
			/* 解析参数 */
			Map<String, String> map = ParamUtils.resolve(param);
			if (null != map && !map.isEmpty()) {
				param = map.get("param");
				headers = map.get("headers");
			}
			/* 将上一个接口的出参作为下一个接口的入参拼接 */
			if (!StringUtils.isEmpty(outParam) && contentType == ContentType.APPLICATION_JSON) {
				String value = ParamUtils.jsonCombination(param, outParam);
				/* JSON合并操作 */
				param = StringUtils.isEmpty(value) ? param : value;
				log.debug("JSON合并后的参数-->" + param);
			}
			try {
				CloseableHttpClient httpClient = httpConnectionManager.getHttpClient();
				if (null != httpClient) {
					HttpPost request = new HttpPost(UrlEncoder.encode(url));
					/* 组装参数 */
					setEntity(request, param, contentType, charset);
					/* 设置请求头 */
					ParamUtils.setHeader(request, headers, contentType, charset);
					httpResponse = httpClient.execute(request);
					entity = httpResponse.getEntity();
					/* 获取压测执行结果 */
					outParamBO = super.getResult(httpResponse, entity);
				}
			} catch (Exception e) {
				// ...
			} finally {
				try {
					if (null != entity) {
						entity.getContent().close();
					}
					if (null != httpResponse) {
						httpResponse.close();
					}
				} catch (IOException e) {
					log.error("error", e);
				}
			}
		}
		return outParamBO;
	}

	/**
	 * 组装参数
	 * 
	 * @author gaoxianglong
	 * @throws UnsupportedEncodingException
	 */
	private void setEntity(HttpPost request, String param, ContentType contentType, String charset)
			throws UnsupportedEncodingException {
		if (StringUtils.isEmpty(param)) {
			return;
		}
		HttpEntity entity = null;
		if (contentType == ContentType.APPLICATION_JSON) {
			entity = new StringEntity(param, charset);
		} else {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			/* 表单参数格式为:key1=value1,key2=value2 */
			String[] parameters = param.split("\\,");
			Arrays.asList(parameters).stream().forEach(parameter -> {
				String[] value = parameter.split("\\=");
				nameValuePairs.add(new BasicNameValuePair(value[0], value[1]));
			});
			entity = new UrlEncodedFormEntity(nameValuePairs, charset);
		}
		request.setEntity(entity);
	}
}