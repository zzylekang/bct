/*
 * Copyright Bruce Liang (ldcsaa@gmail.com)
 *
 * Author	: Bruce Liang
 * Bolg		: http://www.cnblogs.com/ldcsaa
 * WeiBo	: http://weibo.com/u/1402935851
 * QQ Group	: http://qun.qq.com/#jointhegroup/gid/75375912
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zzy.dev.comm.util;

/**
 * 
 * ��/ֵ��
 *
 * @param <K>	: ��������
 * @param <V>	: ��������
 */
public class KV<K extends Object, V extends Object>
{
	private K key;
	private V value;
	
	public KV()
	{
		
	}
	
	public KV(K key, V value)
	{
		this.key = key;
		this.value = value;
	}

	public K getKey()
	{
		return key;
	}

	public void setKey(K key)
	{
		this.key = key;
	}

	public V getValue()
	{
		return value;
	}

	public void setValue(V value)
	{
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof KV)
		{
			KV<?, ?> other = (KV<?, ?>)obj;
			
			if(key == other.key)
				return true;
			if(key != null)
				return key.equals(other.key);
		}
		
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return key != null ? key.hashCode() : 0;
	}
	
	@Override
	public String toString()
	{
		return String.format("{%s = %s}", key, value);
	}
}
