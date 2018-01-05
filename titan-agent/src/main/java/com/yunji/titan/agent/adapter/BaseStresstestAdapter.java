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
package com.yunji.titan.agent.adapter;

import com.yunji.titan.agent.bean.bo.OutParamBO;
import com.yunji.titan.agent.stresstest.Stresstest;
import com.yunji.titan.utils.ContentType;

/**
 * 压测引擎核心接口适配器
 * 
 * @author gaoxianglong
 */
public abstract class BaseStresstestAdapter implements Stresstest {
	@Override
	public OutParamBO runGetStresstest(String url, String outParam, String param, ContentType contentType,
			String charset) {
		return null;
	}

	@Override
	public OutParamBO runPostStresstest(String url, String outParam, String param, ContentType contentType,
			String charset) {
		return null;
	}
}