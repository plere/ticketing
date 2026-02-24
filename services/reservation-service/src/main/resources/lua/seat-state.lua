-- KEYS = 좌석 키들
-- ARGV[1] =  좌석 상태
-- ARGV[2] = ttl (ms)

-- 1. 예약가능한 상태인지 확인
for i = 1, #KEYS do
  if redis.call("EXISTS", KEYS[i]) == 1 then
      if redis.call("GET", KEYS[i]) == ARGV[1] then
        return false
       end
  end
end

-- 3. 전부 선점
for i = 1, #KEYS do
  redis.call("SET", KEYS[i], ARGV[1], "PX", ARGV[2])
end

return true