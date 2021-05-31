local key = KEYS[1];
local index = tonumber(ARGV[1]);
local time_window = tonumber(ARGV[2]);
local now_time = tonumber(ARGV[3]);
local far_time = redis.call('lindex', key, index);
if (not far_time)
then
    redis.call('lpush', key, now_time);
    redis.call('pexpire', key, time_window + 1000);
    return 1;
end
if (now_time - far_time > time_window)
then
    redis.call('rpop', key);
    redis.call('lpush', key, now_time);
    redis.call('pexpire', key, time_window + 1000);
    return 1;
else
    return 0;
end
