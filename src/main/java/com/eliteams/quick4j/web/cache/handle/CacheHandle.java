package com.eliteams.quick4j.web.cache.handle;

import java.lang.reflect.Method;

import com.eliteams.quick4j.web.cache.annotation.CacheWipe;
import com.eliteams.quick4j.web.cache.annotation.CacheWrite;
import com.eliteams.quick4j.web.cache.sample.CacheFace;
import com.eliteams.quick4j.web.cache.util.AspectUtil;
import com.eliteams.quick4j.web.cache.util.StringUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StopWatch;



public class CacheHandle {

	Logger logger=Logger.getLogger(this.getClass());
	
	CacheFace cacheFace;

	public CacheFace getCacheFace() {
		return cacheFace;
	}

	public void setCacheFace(CacheFace cacheFace) {
		this.cacheFace = cacheFace;
	}

	public Object CacheProcess(ProceedingJoinPoint pjp) throws Throwable {
		//监测任务执行时间
		StopWatch sw = new StopWatch(getClass().getSimpleName());
		try {
			// AOP启动监听  开始监听任务执行
			sw.start(pjp.getSignature().toShortString());
			// AOP获取方法执行信息
			
			Signature signature = pjp.getSignature();
			MethodSignature methodSignature = (MethodSignature) signature;
			Method method = methodSignature.getMethod();
			//查缓存逻辑
			////判断目标方法上是否有注解CacheWrite
			CacheWrite handle = method.getAnnotation(CacheWrite.class);
			if(handle!=null){
				//在有标注@CacheWrite的地方切入
				return CacheWrite(pjp,method);
			} 
			//清缓存逻辑  判断目标方法上是否有注解CacheWipe
			CacheWipe[] handles = method.getAnnotationsByType(CacheWipe.class);
			if(!StringUtil.isNullOrEmpty(handles)){
				return CacheWipe(pjp,method);
			}
			return pjp.proceed();
		} finally {
			sw.stop();
			//打印任务执行时间
			//System.out.println("任务执行时间："+sw.toString());
		}
	}

	private Object CacheWrite(ProceedingJoinPoint pjp,Method method) throws Throwable {
			// AOP获取方法执行信息
			if (method == null) {
				return pjp.proceed();
			}
			// 获取注解
			CacheWrite handle = method.getAnnotation(CacheWrite.class);
			if (handle == null) {
				return pjp.proceed();
			}
			// 封装缓存KEY
			Object[] args = pjp.getArgs();
			String key = handle.key();
			try {
				if (StringUtil.isNullOrEmpty(key)) {
					key = AspectUtil.getMethodCacheKey(method);
				}
				if (StringUtil.isNullOrEmpty(handle.fields())) {
					key += "_";
					key += AspectUtil.getBeanKey(args);
				}
				if (!StringUtil.isNullOrEmpty(handle.fields())) {
					key = AspectUtil.getFieldKey(method, args, key,
							handle.fields());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			Integer cacheTimer = ((handle.validTime() == 0) ? 24 * 3600
					: handle.validTime());
			try {
				Object result = cacheFace.getCache(key);
				logger.debug("获取缓存:"+key+";缓存内容:"+result);
				if (!StringUtil.isNullOrEmpty(result)) {
					return result;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			Object result = pjp.proceed();
			if (result != null) {
				try {
					cacheFace.setCache(key, result, cacheTimer);
					logger.debug("写入缓存:"+key+";缓存内容:"+result);
				} catch (Exception e) {
					logger.debug("异常"+e);
				}
			}
			return result;
	}

	/**
	 * 缓存清理
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	private Object CacheWipe(ProceedingJoinPoint pjp,Method method) throws Throwable {
			if (method == null) {
				return pjp.proceed();
			}
			Object[] paras = pjp.getArgs();
			Object result = pjp.proceed();
			CacheWipe[] handles = method.getAnnotationsByType(CacheWipe.class);
			if (StringUtil.isNullOrEmpty(handles)) {
				return result;
			}
			for (CacheWipe wipe : handles) {
				try {
					String key = wipe.key();
					if (StringUtil.isNullOrEmpty(wipe.key())) {
						key = (AspectUtil.getMethodCacheKey(method));
					}
					if (StringUtil.isNullOrEmpty(wipe.fields())) {
						key += "_";
						key += AspectUtil.getBeanKey(paras);
					}

					if (!StringUtil.isNullOrEmpty(wipe.fields())) {
						key = AspectUtil.getFieldKey(method, paras, key,
								wipe.fields());
					}
					cacheFace.delCache(key);
					logger.debug("清理缓存:"+key);
				} catch (Exception e) {
				}
			}
			return result;
	}
}
