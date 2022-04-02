package iron.man.lyf.ironmanredis.cache;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 
 * ClassName: RedisUtil
 * 
 * @Description: TODO
 * @date 2018年1月12日
 */
@Component
public class RedisClient {

	private static final Logger logger = LoggerFactory.getLogger(RedisClient.class);

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Resource(name = "stringRedisTemplate")
	ValueOperations<String, String> valOpsStr;

	RedisTemplate<Object, Object> redisTemplate;

	@Resource(name = "redisTemplate")
	ValueOperations<Object, Object> valOpsObj;

	/**
	 * 
	 * @Description: 序列化方式修改
	 * @param @param
	 *            redisTemplate
	 * @return void
	 * @throws @author
	 *             wangyw
	 * @date 2018年1月19日
	 */
	@Autowired(required = false)
	public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
		RedisSerializer<?> stringSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setValueSerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);
		redisTemplate.setHashValueSerializer(stringSerializer);
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 
	 * @Description: 入队列
	 * @param @param
	 *            key
	 * @param @param
	 *            value
	 * @param @param
	 *            second
	 * @param @return
	 * @return Long
	 * @throws @author
	 *             wangyw
	 * @date 2018年1月19日
	 */
	public <T> Long rpush(String key, T value, int second) {
		Long result = null;
		try {
			result = redisTemplate.opsForList().rightPush(key, JSONObject.toJSON(value));
		} catch (Exception e) {
			logger.error("redis rpush data failed , key = " + key, e);
		}
		return result;
	}

	/**
	 * 队列推送字符串
	 * @param key 队列的key
	 * @param value 队列中的值
	 * @return
	 * @author mzq
	 */
	public Long rpushForStr(String key, String value) {
		Long result = null;
		try {
			result = redisTemplate.opsForList().rightPush(key, value);
		} catch (Exception e) {
			logger.error("redis rpush data failed , key = " + key, e);
		}
		return result;
	}

	/**
	 *
	 * @param key 队列的key
	 * @return
	 * @author mzq
	 */
	public String lpopForStr(String key) {
		try {
			Object obj = redisTemplate.opsForList().leftPop(key);
			if(null == obj){
				return null;
			}
			return String.valueOf(obj);
		} catch (Exception e) {
			logger.error("redis blpop data failed, key = " + key, e);
			return null;
		}
	}
	/**
	 * 
	 * @Description: 出队列
	 * @param @param
	 *            key
	 * @param @param
	 *            waitSeconds
	 * @param @param
	 *            clazz
	 * @param @return
	 * @return T
	 * @throws @author
	 *             wangyw
	 * @date 2018年1月19日
	 */
	public <T> T blpop(String key, int waitSeconds, Class<T> clazz) {
		try {
			Object obj = redisTemplate.opsForList().leftPop(key, waitSeconds, TimeUnit.SECONDS);
			return JSONObject.parseObject(String.valueOf(obj), clazz);
		} catch (Exception e) {
			logger.error("redis blpop data failed, key = " + key, e);
			return null;
		}
	}

	/**
	 * 
	 * @Description: 队列长度查询
	 * @param @param
	 *            key
	 * @param @return
	 * @return long
	 * @throws @author
	 *             wangyw
	 * @date 2018年1月31日
	 */
	public long mqLength(String key) {
		try {
			return redisTemplate.opsForList().size(key);
		} catch (Exception e) {
			logger.error("redis query size failed, key = " + key, e);
			return 0;
		}
	}
	/**
	 * 
	 * @Description: 字符串类型数据存放
	 * @param @param
	 *            key
	 * @param @param
	 *            value
	 * @return void
	 * @throws @author
	 *             wangyw
	 * @date 2018年1月24日
	 */
	public void setStr(String key, String value) {
		try {
			redisTemplate.opsForValue().set(key, value);
		} catch (Exception e) {
			logger.error("redis setStr data failed, key = " + key, e);
		}
	}

	/**
	 * 
	 * @Description: 返回字符串类型数据
	 * @param @param
	 *            key
	 * @param @param
	 *            value
	 * @param @return
	 * @return String
	 * @throws @author
	 *             wangyw
	 * @date 2018年1月24日
	 */
	public String getStr(String key) {
		try {
			String str = (String) redisTemplate.opsForValue().get(key);
			if (str == null)
				return null;
			return str.trim();
		} catch (Exception e) {
			logger.error("redis getStr data failed, key = " + key, e);
			return null;
		}
	}


	/**
	 * key 自增+1
	 * @param key
	 * @return
	 */
	public long increment(String key) {
		return increment(key, 1);
	}


	/**
	 * key 自增 + inc
	 * @param key
	 * @param inc
	 * @return
	 */
	public long increment(String key, long inc) {
		try {

			Long increment = redisTemplate.opsForValue().increment(key, inc);
			return increment;

		} catch (Exception e) {
			logger.error("redis increment data failed, key = " + key + ", inc=" + inc, e);
		}
		return -1;
	}


	/**
	 * 
	 * @Description: 字符串类型数据存放
	 * @param @param
	 *            key
	 * @param @param
	 *            value
	 * @return void
	 * @throws @author
	 *             wangyw
	 * @date 2018年1月24日
	 */
	public void setStr(String key, String value, long times) {
		try {
			redisTemplate.opsForValue().set(key, value, times, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			logger.error("redis setStr data failed, key = " + key, e);
		}
	}

	/**
	 * 
	 * @Description: 根据Key删除
	 * @param @param
	 *            key
	 * @param @return
	 * @return String
	 * @throws @author
	 *             wangyw
	 * @date 2018年4月21日
	 */
	public String remove(String key) {
		try {
			if (key != null && !"".equals(key)) {
				redisTemplate.delete(key);
				return "0";
			}
		} catch (Exception e) {
			logger.error("redis delete data failed, key = " + key, e);
			return "-1";
		}
		return "-2";
	}

	/**
	 * 批量删除key
	 * @param keys
	 * @return
	 */
	/*public String remove(Collection keys) {
		try {
			if (CollectionUtils.isNotEmpty(keys)) {
				redisTemplate.delete(keys);
				return "0";
			}
		} catch (Exception e) {
			logger.error("redis delete data failed, key = " + keys, e);
			return "-1";
		}
		return "-2";
	}*/

	/**
	 * 
	 * @Description: 指定前缀-批量模糊删除
	 * @param @param
	 *            prefixKey
	 * @param @return
	 * @return String
	 * @throws @author
	 *             wangyw
	 * @date 2018年4月21日
	 */
	public String batchRemove(String prefixKey) {
		try {
			if (prefixKey != null && !"".equals(prefixKey)) {
				Set<Object> keys = redisTemplate.keys(prefixKey + ":*");
				redisTemplate.delete(keys);
				return "0";
			}
		} catch (Exception e) {
			logger.error("redis batch delete data failed, key = " + prefixKey, e);
			return "-1";
		}
		return "-2";
	}

	/**
	 *
	 * @Description: 指定前缀-批量模糊删除(无冒号版)
	 * @param @param
	 *            prefixKey
	 * @param @return
	 * @return String
	 * @throws @author
	 *             wangyw
	 * @date 2018年4月21日
	 */
	public String removeByPref(String prefixKey) {
		try {
			if (prefixKey != null && !"".equals(prefixKey)) {
				Set<Object> keys = redisTemplate.keys(prefixKey + "*");
				redisTemplate.delete(keys);
				return "0";
			}
		} catch (Exception e) {
			logger.error("redis batch delete data failed, key = " + prefixKey, e);
			return "-1";
		}
		return "-2";
	}
	
	public void expire(String key, long timeout) {
		redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
	}
	
	public long getExpire(String key) 
	{
		return redisTemplate.getExpire(key);
	}

    /**
     * 根据缓存前缀模糊查询所有key
     * @param prefixKey String
     * @return Set<Object>
	 * @author mawenjun
	 * @Create Date 2018-06-04 11:20:20
     */
    public Set<Object> batchGet(String prefixKey) {
        try {
            if (prefixKey != null && !"".equals(prefixKey)) {
                return redisTemplate.keys(prefixKey + ":*");
            }
        } catch (Exception e) {
            logger.error("redis batchGet failed, prefixKey = " + prefixKey + "| error:" + e.getMessage(), e);
        }
        return null;
    }

	/**
	 * 设置一个有序集合
	 * @param key String
	 * @param score double
	 * @param value Object
	 * @return boolean 存在的话为false，不存在的话为true
	 * @author mawenjun
	 * @Create Date 2018-06-04 11:22:35
	 */
	public boolean setSortedSet(String key, double score, Object value) {
        try {
		    return redisTemplate.opsForZSet().add(key, value, score);
        } catch (Exception e) {
            logger.error("redis setSortedSet failed, key = " + key + "| error:" + e.getMessage(), e);
            return false;
        }
	}

    /**
     * 增加元素的score值，并返回增加后的值
     * @param key String
     * @param score double
     * @param value Object
     * @return double
	 * @author mawenjun
	 * @Create Date 2018-06-04 11:25:10
     */
	public double incrementScore(String key, double score, Object value) {
        try {
            return redisTemplate.opsForZSet().incrementScore(key, value, score);
        } catch (Exception e) {
            logger.error("redis incrementScore failed, key = " + key + "| error:" + e.getMessage(), e);
            return 0.0;
        }
    }

	/**
	 * 根据时间范围获得有序集合
	 * @param key String
	 * @param startScore double
	 * @param endSocre double
	 * @param orderByDesc boolean
	 * @return Set<Object>
	 * @author mawenjun
	 * @Create Date 2018-06-04 11:28:40
	 */
	public Set<Object> getSortedSet(String key, double startScore, double endSocre, boolean orderByDesc) {
        try {
            if (orderByDesc) {
                return redisTemplate.opsForZSet().reverseRangeByScore(key, startScore, endSocre);
            } else {
                return redisTemplate.opsForZSet().rangeByScore(key, startScore, endSocre);
            }
        } catch (Exception e) {
            logger.error("redis getSortedSet(double) failed, key = " + key + "| error:" + e.getMessage(), e);
            return null;
        }
	}

    /**
     * 根据key获得有序集合的成员数
     * @param key String
     * @return long
	 * @author mawenjun
	 * @Create Date 2018-06-04 11:29:05
     */
    public long countSortedSet(String key) {
        try {
            return redisTemplate.opsForZSet().zCard(key);
        } catch (Exception e) {
            logger.error("redis countSortedSet failed, key = " + key + "| error:" + e.getMessage(), e);
            return 0L;
        }
    }

	/**
	 * 判断某个key是否存在true 存在 false不存在
	 * @param key String
	 * @return boolean
	 * @author mawenjun
	 * @Create Date 2018-06-04 11:29:05
	 */
	public boolean isExistsSortedSet(String key) {
		try {
			Boolean isHasKey = redisTemplate.hasKey(key);
			if (isHasKey){
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

    /**
     * 根据时间范围获得有序集合的成员数
     * @param key String
     * @param startScore double
     * @param endSocre double
     * @return long
	 * @author mawenjun
	 * @Create Date 2018-06-04 11:30:17
     */
    public long countSortedSet(String key, double startScore, double endSocre) {
        try {
            return redisTemplate.opsForZSet().count(key, startScore, endSocre);
        } catch (Exception e) {
            logger.error("redis countSortedSetSize(min,max) failed, key = " + key + "| error:" + e.getMessage(), e);
            return 0L;
        }
    }

    /**
     * 通过索引区间返回有序集合成指定区间内的成员
     * @param key String
     * @param start long 索引位置
     * @param end long 索引位置
     * @param orderByDesc boolean
     * @return Set<Object>
	 * @author mawenjun
	 * @Create Date 2018-06-04 11:31:20
     */
	public Set<Object> getSortedSet(String key, long start, long end, boolean orderByDesc) {
        try {
            if (orderByDesc) {
                return redisTemplate.opsForZSet().reverseRange(key, start, end);
            } else {
                return redisTemplate.opsForZSet().range(key, start, end);
            }
        } catch (Exception e) {
            logger.error("redis getSortedSet(long) failed, key = " + key + "| error:" + e.getMessage(), e);
            return null;
        }
	}

	/**
	 * 根据时间范围和条数获得有序集合
	 * @param key String
	 * @param startScore double
	 * @param endSocre double
     * @param offset long 跳过条数
     * @param count long 返回数据的条数
	 * @param orderByDesc boolean
	 * @return Set<Object>
	 * @author mawenjun
	 * @Create Date 2018-06-04 11:33:50
	 */
	public Set<Object> getSortedSet(String key, double startScore, double endSocre, long offset, long count, boolean orderByDesc) {
        try {
            if (orderByDesc) {
                return redisTemplate.opsForZSet().reverseRangeByScore(key, startScore, endSocre, offset, count);
            } else {
                return redisTemplate.opsForZSet().rangeByScore(key, startScore, endSocre, offset, count);
            }
        } catch (Exception e) {
            logger.error("redis getSortedSet(offset, count) failed, key = " + key + "| error:" + e.getMessage(), e);
            return null;
        }
	}

    /**
     * 根据key和元素获得该元素的排序打分
     * @param key String
     * @param object Object
     * @return String
	 * @author mawenjun
	 * @Create Date 2018-06-04 11:36:40
     */
    public String getScore(String key, Object object) {
        try {
			Double value = redisTemplate.opsForZSet().score(key, object);
			if (value == null) {
				return "0";
			}
			DecimalFormat df = new DecimalFormat("##0");
			return df.format(value);
        } catch (Exception e) {
            logger.error("redis getScore failed, key = " + key + "| error:" + e.getMessage(), e);
            return "0";
        }
    }

    /**
     * 根据key删除有序集合对应的value
     * @param key String
     * @param values Object
     * @return boolean 删除数大于1返回true，无删除数据返回false
	 * @author mawenjun
	 * @Create Date 2018-06-04 11:37:20
     */
    public boolean delSortedSet(String key, Object... values) {
        try {
            long count = redisTemplate.opsForZSet().remove(key, values);
            return count > 0;
        } catch (Exception e) {
            logger.error("redis delSortedSet failed, key = " + key + "| error:" + e.getMessage(), e);
            return false;
        }
    }

    /**
     * 移除指定索引位置的成员
     * @param key String
     * @param start long 索引位置
     * @param end long 索引位置
     * @return long 删除元素的个数
	 * @author mawenjun
	 * @Create Date 2018-06-04 11:39:30
     */
    public long delRange(String key, long start, long end) {
        try {
            return redisTemplate.opsForZSet().removeRange(key, start, end);
        } catch (Exception e) {
            logger.error("redis delRange(long) failed, key = " + key + "| error:" + e.getMessage(), e);
            return 0L;
        }
    }

    /**
     * 根据指定的score值得范围来移除成员
     * @param key String
     * @param startScore double
     * @param endSocre double
     * @return boolean 删除数大于1返回true，无删除数据返回false
	 * @author mawenjun
	 * @Create Date 2018-06-04 11:40:40
     */
    public boolean delRangeByScore(String key, double startScore, double endSocre) {
        try {
            long count = redisTemplate.opsForZSet().removeRangeByScore(key, startScore, endSocre);
            return count > 0;
        } catch (Exception e) {
            logger.error("redis delRangeByScore failed, key = " + key + "| error:" + e.getMessage(), e);
            return false;
        }
    }

    /**
     * 通过索引区间返回有序集合成指定区间内的成员
     * @param key String
     * @param start long 索引位置
     * @param end long 索引位置
     * @param orderByDesc boolean
     * @return Set<TypedTuple<Object>>
	 * @author mawenjun
	 * @Create Date 2018-06-04 11:43:30
     */
    public Set<ZSetOperations.TypedTuple<Object>> getSortedSetByRangeWithScores(String key, long start, long end, boolean orderByDesc) {
        try {
            if (orderByDesc) {
                return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
            } else {
                return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
            }
        } catch (Exception e) {
            logger.error("redis getSortedSetByRangeWithScores failed, key = " + key + "| error:" + e.getMessage(), e);
            return null;
        }
    }

    /**
     * 根据时间范围和条数获得有序集合
     * @param key String
     * @param startScore double
     * @param endSocre double
     * @param offset long 跳过条数
     * @param count long 返回数据的条数
     * @param orderByDesc boolean
     * @return Set<TypedTuple<Object>>
	 * @author mawenjun
	 * @Create Date 2018-06-04 11:45:40
     */
    public Set<ZSetOperations.TypedTuple<Object>> getSortedSetByRangeWithCountWithScores(String key, double startScore, double endSocre, long offset, long count, boolean orderByDesc) {
        try {
            if (orderByDesc) {
                return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, startScore, endSocre, offset, count);
            } else {
                return redisTemplate.opsForZSet().rangeByScoreWithScores(key, startScore, endSocre, offset, count);
            }
        } catch (Exception e) {
            logger.error("redis getSortedSetByRangeWithCountWithScores failed, key = " + key + "| error:" + e.getMessage(), e);
            return null;
        }
    }
    
    /**
     * 添加watch
     * */
    public void setWatch(String watchKey) {
    	redisTemplate.watch(watchKey);
    }
    
    /**
     * 解除watch
     * */
    public void unWatch(String watchKey) {
    	redisTemplate.unwatch();
    }
    
    /**
     * 开启事务
     * */
    @Transactional
    public List<Object> mutli(String key) {
        stringRedisTemplate.setEnableTransactionSupport(true);

        stringRedisTemplate.multi();
        
        stringRedisTemplate.opsForValue().increment(key, -1);
        
        List<Object> list = stringRedisTemplate.exec();
        
        stringRedisTemplate.setEnableTransactionSupport(false);
        
        return list;
        
    }


    
}