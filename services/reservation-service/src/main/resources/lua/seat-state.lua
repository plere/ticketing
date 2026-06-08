-- KEYS = 좌석 키들
-- ARGV[1] =  좌석 상태
-- ARGV[2] = ttl (ms)

-- 1. 예약가능한 상태인지 확인
for i = 1, #KEYS do
  if redis.call("EXISTS", KEYS[i]) == 1 then
      if redis.call("GET", KEYS[i]) == ARGV[1] then
          redis.log(redis.LOG_WARNING, "1")
          return false
       end
  end
end

-- 2. 전부 NX로 세팅
for i = 1, #KEYS do
  local result = redis.call("SET", KEYS[i], ARGV[1], "PX", ARGV[2], "NX")
  if not result then
    -- 롤백
    for j = 1, i-1 do
      redis.call("DEL", KEYS[j])
    end
    redis.log(redis.LOG_WARNING, "3")
    return false
  end
end

redis.log(redis.LOG_WARNING, "4")
return true